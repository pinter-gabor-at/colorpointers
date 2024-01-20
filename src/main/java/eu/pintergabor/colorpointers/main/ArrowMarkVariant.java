package eu.pintergabor.colorpointers.main;


import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SignItem;
import net.minecraft.sound.BlockSoundGroup;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;

/**
 * One IronSign variant
 */
public class ArrowMarkVariant {

	/**
	 * Standing sign block
	 * <p>
	 * Read only outside class.
	 */
	public Block block;

	/**
	 * Sign item
	 * <p>
	 * Read only outside class.
	 */
	public Item item;

	/**
	 * Create one variant of IronSign
	 * @param name The name of the IronSign
	 */
	public ArrowMarkVariant(String name) {
		// WoodType is not really any type of wood, but a definition
		// of the location of the texture files, and the definition of sounds.
		// Sign entity: resources/assets/<MODID>/textures/entity/signs/<name>.png
		// WallSign entity is the hard-coded part of the Sign entity.
		// Sign and WallSign GUIs are the same, and they are hard-coded part of the Sign entity.
		// HangingSign entity is the hard-coded part of the HangingWallSign entity.
		// HangingWallSign entity: resources/assets/<MODID>/textures/entity/signs/hanging/<name>.png
		// HangingSign GUI: resources/assets/<MODID>/textures/gui/hanging_signs/<name>.png
		woodType = (new WoodTypeBuilder())
			.soundGroup(BlockSoundGroup.METAL)
			.hangingSignSoundGroup(BlockSoundGroup.METAL)
			.register(
				new ModIdentifier(name), BlockSetType.IRON);
		// Blocks
		final FabricBlockSettings settings = FabricBlockSettings.create()
			.solid().noCollision().strength(0.5f, 6.0f).requiresTool();
		block = registerBlock(name,
			new IronSignBlock(woodType, settings));
		wallBlock = registerBlock("wall_" + name,
			new IronWallSignBlock(woodType, settings));
		hangingBlock = registerBlock("hanging_" + name,
			new IronHangingSignBlock(woodType, settings));
		hangingWallBlock = registerBlock("hanging_wall_" + name,
			new IronWallHangingSignBlock(woodType, settings));
		// Items
		item = registerItem(name,
			new SignItem(
				new FabricItemSettings().maxCount(64),
				block, wallBlock));
		hangingItem = registerItem("hanging_" + name,
			new HangingSignItem(
				hangingBlock, hangingWallBlock,
				new FabricItemSettings().maxCount(64)));
		// Item groups
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(
			entries -> {
				entries.add(item);
				entries.add(hangingItem);
			});
	}
}
