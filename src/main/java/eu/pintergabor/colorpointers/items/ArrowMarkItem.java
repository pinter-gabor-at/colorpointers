package eu.pintergabor.colorpointers.items;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import static eu.pintergabor.colorpointers.util.BlockRegion.getClickedRegion;

public class ArrowMarkItem extends BlockItem {
    public ArrowMarkItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        final World world = context.getWorld();
        final BlockPos pos = context.getBlockPos();
        final BlockState clickedBlockState = world.getBlockState(pos);
        final PlayerEntity player = context.getPlayer();
        final Direction clickedFace = context.getSide();
        final BlockPos markPosition = pos.offset(clickedFace);
        if (world.isAir(markPosition)) {
            if (player != null &&
                    !Block.isFaceFullSquare(clickedBlockState
                            .getCollisionShape(world, pos, ShapeContext.of(player)), clickedFace)) {
                return ActionResult.PASS;
            }
            if (world.isClient) {
                return ActionResult.SUCCESS;
            }
            return placeBlock(context);
        }
        return ActionResult.FAIL;
    }

    /**
     * Create and place the block
     *
     * @param context see {@link #useOnBlock(ItemUsageContext)}
     * @return the same as {@link #useOnBlock(ItemUsageContext)}
     */
    @NotNull
    private ActionResult placeBlock(ItemUsageContext context) {
        final World world = context.getWorld();
        final BlockPos pos = context.getBlockPos();
        final PlayerEntity player = context.getPlayer();
        final ItemStack stack = context.getStack();
        final Direction clickedFace = context.getSide();
        final BlockPos markPosition = pos.offset(clickedFace);
        // The new block
        final int orientation = getClickedRegion(context.getHitPos(), clickedFace);
        final BlockState blockState = getBlock().getDefaultState()
                .with(ArrowMarkBlock.FACING, clickedFace)
                .with(ArrowMarkBlock.ORIENTATION, orientation);
        // Place it
        if (world.setBlockState(markPosition, blockState, Block.NOTIFY_ALL)) {
            if (player != null && !player.isCreative()) {
                stack.decrement(1);
            }
            world.playSound(null, markPosition,
                    SoundEvents.BLOCK_MOSS_CARPET_BREAK, SoundCategory.BLOCKS,
                    0.5f, Random.create().nextFloat() * 0.2f + 0.8f);
            return ActionResult.CONSUME;
        }
        return ActionResult.FAIL;
    }
}
