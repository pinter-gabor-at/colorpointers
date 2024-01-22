package eu.pintergabor.colorpointers.main;


import static eu.pintergabor.colorpointers.Global.arrowMarkBlockLumi;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import eu.pintergabor.colorpointers.util.ModIdentifier;

import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

/**
 * One ArrowMark variant
 */
public class ArrowMarkVariant {

	/**
	 * ArrowMark block
	 * <p>
	 * Read only outside class.
	 */
	public ArrowMarkBlock block;

	/**
	 * ArrowMark item
	 * <p>
	 * Read only outside class.
	 */
	public ArrowMarkItem item;

	/**
	 * Create one variant of ArrowMark
	 */
	public ArrowMarkVariant(String name) {
		// Block
		block = new ArrowMarkBlock(FabricBlockSettings
			.create()
			.replaceable()
			.noCollision()
			.nonOpaque()
			.sounds(BlockSoundGroup.MOSS_CARPET)
			.luminance(arrowMarkBlockLumi)
			.postProcess(ArrowMarkVariant::always)
			.emissiveLighting(ArrowMarkVariant::always)
			.pistonBehavior(PistonBehavior.DESTROY)
		);
		Registry.register(Registries.BLOCK, new ModIdentifier(name), block);
		// Item
		item = new ArrowMarkItem(block, new FabricItemSettings());
		Registry.register(Registries.ITEM, new ModIdentifier(name), item);
		// Item groups
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(
			entries -> {
				entries.add(item);
			});
	}

	private static boolean always(BlockState blockState, BlockView blockView, BlockPos blockPos) {
		return true;
	}
}
