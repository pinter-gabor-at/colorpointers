package eu.pintergabor.colorpointers.main;

import static eu.pintergabor.colorpointers.Global.arrowMarkBlockLumi;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;


/**
 * One ArrowMark variant.
 */
public class ArrowMarkVariant {

	/**
	 * ArrowMark block.
	 * <p>
	 * Read only outside class.
	 */
	public ArrowMarkBlock block;

	/**
	 * ArrowMark item.
	 * <p>
	 * Read only outside class.
	 */
	public ArrowMarkItem item;

	private static boolean always(
		BlockState state, BlockGetter blockView, BlockPos pos
	) {
		return true;
	}

	/**
	 * Create one variant of ArrowMark.
	 */
	public ArrowMarkVariant(String name) {
		// Block.
		block = (ArrowMarkBlock) Blocks.register(
			ResourceKey.create(Registries.BLOCK, Global.modId(name)),
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
		item = (ArrowMarkItem) Items.registerBlock(
			block,
			ArrowMarkItem::new);
		// Item groups.
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(
			entries -> entries.accept(item));
	}
}
