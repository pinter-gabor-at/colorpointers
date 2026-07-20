package eu.pintergabor.colorpointers.main;

import static eu.pintergabor.colorpointers.Global.arrowMarkBlockLumi;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
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
	public final DeferredBlock<Block> block;

	/**
	 * ArrowMark item.
	 * <p>
	 * Read only outside class.
	 */
	public final DeferredItem<Item> item;

	/**
	 * Create one variant of ArrowMark.
	 */
	public ArrowMarkVariant(String name) {
		// Block.
		block = Main.BLOCKS.registerBlock(
			name,
			ArrowMarkBlock::new,
			() -> Block.Properties
				.of()
				.replaceable()
				.noCollision()
				.noOcclusion()
				.sound(SoundType.MOSS_CARPET)
				.lightLevel(_ -> arrowMarkBlockLumi)
				.emissiveRendering(_ -> true)
				.pushReaction(PushReaction.DESTROY));
		// Item.
		item = Main.ITEMS.registerItem(
			name, props ->
				new ArrowMarkItem(block.get(), props));
	}
}
