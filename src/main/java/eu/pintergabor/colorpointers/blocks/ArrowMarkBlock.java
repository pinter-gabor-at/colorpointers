package eu.pintergabor.colorpointers.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Random;

import static eu.pintergabor.colorpointers.Global.margin;
import static eu.pintergabor.colorpointers.Global.thick;
import static eu.pintergabor.colorpointers.util.BlockRegion.MIDDLECENTER;

/**
 * An ArrowMarkBlock is a thin, flat block that has 6 directions and 9 orientations
 */
public class ArrowMarkBlock extends Block {
    public static final DirectionProperty FACING = Properties.FACING;
    public static final IntProperty ORIENTATION = IntProperty.of("orientation", 0, 8);

    private static final VoxelShape DOWN_AABB = Block.createCuboidShape(
            margin, 16D - thick, margin, 16D - margin, 16D, 16D - margin);
    private static final VoxelShape UP_AABB = Block.createCuboidShape(
            margin, 0D, margin, 16D - margin, thick, 16D - margin);
    private static final VoxelShape SOUTH_AABB = Block.createCuboidShape(
            margin, margin, 0D, 16D - margin, 16D - margin, thick);
    private static final VoxelShape EAST_AABB = Block.createCuboidShape(
            0D, margin, margin, thick, 16D - margin, 16D - margin);
    private static final VoxelShape WEST_AABB = Block.createCuboidShape(
            16D - thick, margin, margin, 16D, 16D - margin, 16D - margin);
    private static final VoxelShape NORTH_AABB = Block.createCuboidShape(
            margin, margin, 16D - thick, 16D - margin, 16D - margin, 16D);

    public ArrowMarkBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(ORIENTATION, MIDDLECENTER));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ORIENTATION);
        super.appendProperties(builder);
    }

    @Override
    protected void spawnBreakParticles(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        if (!world.isClient) {
            world.playSound(null, pos,
                    SoundEvents.BLOCK_MOSS_CARPET_BREAK, SoundCategory.BLOCKS,
                    0.5f, new Random().nextFloat() * 0.2f + 0.8f);
        }
    }

    /**
     * Thin, flat outline shape
     */
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case DOWN -> DOWN_AABB;
            case NORTH -> NORTH_AABB;
            case SOUTH -> SOUTH_AABB;
            case EAST -> EAST_AABB;
            case WEST -> WEST_AABB;
            default -> UP_AABB;
        };
    }

    /**
     * Unconditional pass-through
     */
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    /**
     * Unconditional can-replace
     */
    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return true;
    }

    /**
     * Can place at any full face
     */
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction facing = state.get(FACING);
        return Block.isFaceFullSquare(world.getBlockState(pos.offset(facing.getOpposite()))
                .getCollisionShape(world, pos.offset(facing)), facing);
    }

    /**
     * Break, if neighboring full face block is broken
     */
    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        boolean support = neighborPos.equals(pos.offset(state.get(FACING).getOpposite()));
        if (support) {
            if (!this.canPlaceAt(state, world, pos)) {
                return Blocks.AIR.getDefaultState();
            }
        }
        return state;
    }
}
