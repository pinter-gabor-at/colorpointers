package eu.pintergabor.colorpointers.main;

import static eu.pintergabor.colorpointers.Global.arrowMarkBlockLumi;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.PushReaction;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;


/**
 * One ArrowMark variant.
 */
public class ArrowMarkVariant {

	/**
	 * ArrowMark block.
	 * <p>
	 * Read only outside class.
	 */
	public final ArrowMarkBlock block;

	/**
	 * ArrowMark block id.
	 * <p>
	 * Read only outside class.
	 */
	public final ResourceKey<Block> blockId;

	/**
	 * ArrowMark item.
	 * <p>
	 * Read only outside class.
	 */
	public final ArrowMarkItem item;

	/**
	 * ArrowMark item id.
	 * <p>
	 * Read only outside class.
	 */
	public final ResourceKey<Item> itemId;

	/**
	 * Create one variant of ArrowMark.
	 */
	public ArrowMarkVariant(String name) {
		// Block.
		blockId = ResourceKey.create(Registries.BLOCK, Global.modId(name));
		block = (ArrowMarkBlock) Blocks.register(
			blockId,
			ArrowMarkBlock::new,
			Block.Properties
				.of()
				.replaceable()
				.noCollision()
				.noOcclusion()
				.sound(SoundType.MOSS_CARPET)
				.lightLevel(_ -> arrowMarkBlockLumi)
				.emissiveRendering(_ -> true)
				.pushReaction(PushReaction.DESTROY)
		);
		// Item.
		itemId = ResourceKey.create(Registries.ITEM, Global.modId(name));
		item = (ArrowMarkItem) Items.registerItem(
			itemId,
			props -> new ArrowMarkItem(block, props));
		// Item groups.
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(
			entries -> entries.accept(item));
	}
}
