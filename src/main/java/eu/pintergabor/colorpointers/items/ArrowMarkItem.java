package eu.pintergabor.colorpointers.items;

import static eu.pintergabor.colorpointers.util.BlockRegion.getClickedRegion;

import java.util.Random;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;

import eu.pintergabor.colorpointers.main.ArrowMarkColor;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ArrowMarkItem extends Item {
	protected ArrowMarkBlock block;
	protected ArrowMarkColor color;

	public ArrowMarkItem(Settings settings, ArrowMarkColor color) {
		super(settings);
		this.color = color;
	}

	public ArrowMarkItem setBlock(ArrowMarkBlock block) {
		this.block = block;
		return this;
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		final World world = context.getWorld();
		final BlockPos pos = context.getBlockPos();
		final BlockState clickedBlockState = world.getBlockState(pos);
		final PlayerEntity player = context.getPlayer();
		final ItemStack stack = context.getStack();
		final Direction clickedFace = context.getSide();
		final BlockPos markPosition = pos.offset(clickedFace);
		if (world.isAir(markPosition) ||
			world.getBlockState(markPosition).getBlock() instanceof ArrowMarkBlock) {
			if (player != null &&
				!Block.isFaceFullSquare(clickedBlockState
					.getCollisionShape(world, pos, ShapeContext.of(player)), clickedFace)) {
				return ActionResult.PASS;
			} else if ((!world.isAir(markPosition) &&
				world.getBlockState(markPosition).getBlock() instanceof ArrowMarkBlock) ||
				stack.getItem() != this) {
				return ActionResult.PASS;
			}

			if (world.isClient) {
				return ActionResult.SUCCESS;
			}

			// The new block
			final int orientation = getClickedRegion(context.getHitPos(), clickedFace);
			BlockState blockState = block.getDefaultState()
				.with(ArrowMarkBlock.FACING, clickedFace)
				.with(ArrowMarkBlock.ORIENTATION, orientation);

			// Place it
			if (world.setBlockState(markPosition, blockState, Block.NOTIFY_ALL)) {
				if (player != null &&
					!player.isCreative()) {
					stack.decrement(1);
				}
				world.playSound(null, markPosition,
					SoundEvents.BLOCK_MOSS_CARPET_BREAK, SoundCategory.BLOCKS,
					0.5f, new Random().nextFloat() * 0.2f + 0.8f);
				return ActionResult.CONSUME;
			}
		}
		return ActionResult.FAIL;
	}
}
