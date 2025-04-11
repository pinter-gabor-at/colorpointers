package eu.pintergabor.colorpointers.blocks;

import static eu.pintergabor.colorpointers.Global.margin;
import static eu.pintergabor.colorpointers.Global.thickness;
import static eu.pintergabor.colorpointers.util.BlockRegion.MIDDLECENTER;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


/**
 * An ArrowMarkBlock is a thin, flat block that has 6 directions and 9 orientations.
 */
public class ArrowMarkBlock extends Block {
	public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
	public static final IntegerProperty ORIENTATION = IntegerProperty.create("orientation", 0, 8);

	private static final VoxelShape DOWN_AABB = Block.box(
		margin, 16D - thickness, margin, 16D - margin, 16D, 16D - margin);
	private static final VoxelShape UP_AABB = Block.box(
		margin, 0D, margin, 16D - margin, thickness, 16D - margin);
	private static final VoxelShape SOUTH_AABB = Block.box(
		margin, margin, 0D, 16D - margin, 16D - margin, thickness);
	private static final VoxelShape EAST_AABB = Block.box(
		0D, margin, margin, thickness, 16D - margin, 16D - margin);
	private static final VoxelShape WEST_AABB = Block.box(
		16D - thickness, margin, margin, 16D, 16D - margin, 16D - margin);
	private static final VoxelShape NORTH_AABB = Block.box(
		margin, margin, 16D - thickness, 16D - margin, 16D - margin, 16D);

	public ArrowMarkBlock(Properties props) {
		super(props);
		this.registerDefaultState(defaultBlockState()
			.setValue(FACING, Direction.NORTH)
			.setValue(ORIENTATION, MIDDLECENTER));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, ORIENTATION);
		super.createBlockStateDefinition(builder);
	}

	@Override
	protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
		if (!level.isClientSide) {
			level.playSound(null, pos,
				SoundEvents.MOSS_CARPET_BREAK, SoundSource.BLOCKS,
				0.5F, RandomSource.create().nextFloat() * 0.2F + 0.8F);
		}
	}

	/**
	 * Thin, flat outline shape.
	 */
	@Override
	@NotNull
	public VoxelShape getShape(
		BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case DOWN -> DOWN_AABB;
			case NORTH -> NORTH_AABB;
			case SOUTH -> SOUTH_AABB;
			case EAST -> EAST_AABB;
			case WEST -> WEST_AABB;
			default -> UP_AABB;
		};
	}

	/**
	 * Unconditional pass-through.
	 */
	@Override
	@NotNull
	public VoxelShape getCollisionShape(
		BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	/**
	 * Unconditional can-replace.
	 */
	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return true;
	}

	/**
	 * Can place at any full face.
	 */
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction facing = state.getValue(FACING);
		return Block.isFaceFull(level.getBlockState(pos.relative(facing.getOpposite()))
			.getCollisionShape(level, pos.relative(facing)), facing);
	}

	/**
	 * Break, if neighboring full face block is broken.
	 */
	@Override
	@NotNull
	protected BlockState updateShape(
		BlockState state, LevelReader level, ScheduledTickAccess tickView,
		BlockPos pos, Direction direction, BlockPos neighborPos,
		BlockState neighborState, RandomSource random) {
		BlockPos supportPos = pos.relative(state.getValue(FACING).getOpposite());
		boolean support = neighborPos.equals(supportPos);
		if (support) {
			if (!canSurvive(state, level, pos)) {
				return Blocks.AIR.defaultBlockState();
			}
		}
		return state;
	}
}
