package eu.pintergabor.colorpointers.main;

import static eu.pintergabor.colorpointers.Global.arrowMarkBlockLumi;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;


/**
 * One ArrowMark variant.
 */
public class ArrowMarkVariant {

	/**
	 * ArrowMark block.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredBlock<Block> block;

	/**
	 * ArrowMark item.
	 * <p>
	 * Read only outside class.
	 */
	public DeferredItem<Item> item;

	private static boolean always(BlockState blockState, BlockGetter blockView, BlockPos blockPos) {
		return true;
	}

	/**
	 * Create one variant of ArrowMark.
	 */
	public ArrowMarkVariant(String name) {
		// Block.
		block = Main.BLOCKS.registerBlock(
			name,
			ArrowMarkBlock::new,
			Block.Properties
				.of()
				.replaceable()
				.noCollission()
				.noOcclusion()
				.sound(SoundType.MOSS_CARPET)
				.lightLevel(value -> arrowMarkBlockLumi)
				.hasPostProcess(ArrowMarkVariant::always)
				.emissiveRendering(ArrowMarkVariant::always)
				.pushReaction(PushReaction.DESTROY)
		);
		// Item.
		item = Main.ITEMS.registerItem(
			name, props ->
				new ArrowMarkItem(block.get(), props));
	}
}
