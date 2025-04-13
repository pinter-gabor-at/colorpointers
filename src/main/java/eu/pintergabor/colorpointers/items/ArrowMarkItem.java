package eu.pintergabor.colorpointers.items;

import static eu.pintergabor.colorpointers.util.BlockRegion.getClickedRegion;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.util.BlockRegion;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;


public class ArrowMarkItem extends BlockItem {

	public ArrowMarkItem(Block block, Properties props) {
		super(block, props);
	}

	/**
	 * Create and place the block.
	 *
	 * @param context see {@link #useOn(UseOnContext)}.
	 * @return the same as {@link #useOn(UseOnContext)}.
	 */
	@NotNull
	private InteractionResult placeBlock(UseOnContext context) {
		final Level level = context.getLevel();
		final BlockPos pos = context.getClickedPos();
		final Player player = context.getPlayer();
		final ItemStack stack = context.getItemInHand();
		final Direction clickedFace = context.getClickedFace();
		final BlockPos markPosition = pos.relative(clickedFace);
		// The new block.
		final BlockRegion orientation = getClickedRegion(context.getClickLocation(), clickedFace);
		final BlockState blockState = getBlock().defaultBlockState()
			.setValue(ArrowMarkBlock.FACING, clickedFace)
			.setValue(ArrowMarkBlock.ORIENTATION, orientation);
		// Place it.
		if (level.setBlockAndUpdate(markPosition, blockState)) {
			if (player != null && !player.isCreative()) {
				stack.shrink(1);
			}
			level.playSound(null, markPosition,
				SoundEvents.MOSS_CARPET_BREAK, SoundSource.BLOCKS,
				0.5F, RandomSource.create().nextFloat() * 0.2F + 0.8F);
			return InteractionResult.CONSUME;
		}
		return InteractionResult.FAIL;
	}

	@Override
	@NotNull
	public InteractionResult useOn(UseOnContext context) {
		final Level level = context.getLevel();
		final BlockPos pos = context.getClickedPos();
		final BlockState clickedBlockState = level.getBlockState(pos);
		final Player player = context.getPlayer();
		final Direction clickedFace = context.getClickedFace();
		final BlockPos markPosition = pos.relative(clickedFace);
		if (level.isEmptyBlock(markPosition)) {
			if (player != null &&
				!Block.isFaceFull(clickedBlockState
					.getCollisionShape(level, pos, CollisionContext.of(player)), clickedFace)) {
				return InteractionResult.PASS;
			}
			if (level.isClientSide) {
				return InteractionResult.SUCCESS;
			}
			// Place the block on the server side.
			return placeBlock(context);
		}
		return InteractionResult.FAIL;
	}
}
