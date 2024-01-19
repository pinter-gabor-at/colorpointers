package eu.pintergabor.colorpointers.main;

import static eu.pintergabor.colorpointers.Global.arrowMarkBlockLumi;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import eu.pintergabor.colorpointers.util.ModIdentifier;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class ArrowRegistry {
	public static ArrowMarkBlock arrowMarkBlock;
	public static ArrowMarkItem arrowMarkItem;

	public static void init() {
		arrowMarkBlock = new ArrowMarkBlock(AbstractBlock.Settings
			.create()
			.replaceable()
			.noCollision()
			.nonOpaque()
			.sounds(BlockSoundGroup.MOSS_CARPET)
			.luminance((state) -> arrowMarkBlockLumi)
			.postProcess(ArrowRegistry::always)
			.emissiveLighting(ArrowRegistry::always)
			.pistonBehavior(PistonBehavior.DESTROY));
		arrowMarkItem = new ArrowMarkItem(new FabricItemSettings(), DyeColor.WHITE, arrowMarkBlock);
		register();
	}

	private static boolean always(BlockState blockState, BlockView blockView, BlockPos blockPos) {
		return true;
	}

	@SuppressWarnings("SameParameterValue")
	private static void registerBlock(String name, Block block) {
		Registry.register(Registries.BLOCK, new ModIdentifier(name), block);
	}

	@SuppressWarnings("SameParameterValue")
	private static void registerItem(String name, Item item) {
		Registry.register(Registries.ITEM, new ModIdentifier(name), item);
	}

	public static void register() {
		registerBlock("arrow_mark", arrowMarkBlock);
		registerItem("arrow_mark", arrowMarkItem);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(
			entries -> {
				entries.add(arrowMarkItem);
			});
	}
}
