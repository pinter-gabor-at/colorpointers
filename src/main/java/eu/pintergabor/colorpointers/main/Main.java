package eu.pintergabor.colorpointers.main;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.Mod;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public final class Main {

	/**
	 * All {@link ArrowMarkColor} enum values in an array
	 * <p>
	 * Read-only outside its class.
	 */
	public static ArrowMarkColor[] arrowMarkColors;

	/**
	 * Colored Iron Signs
	 * <p>
	 * Read-only outside its class.
	 */
	public static ArrowMarkVariant[] arrowMarks;

	/**
	 * All sign and hanging sign blocks
	 * <p>
	 * Used to set up mineable rules. Read-only outside its class.
	 */
	public static TagKey<Block> ARROW_MARK_BLOCK_TAG;

	/**
	 * All sign items
	 * <p>
	 * Used in coloring recipes. Read-only outside its class.
	 */
	public static TagKey<Item> ARROW_MARK_ITEM_TAG;

	/**
	 * Called from {@link Mod#onInitialize()}
	 */
	public static void init() {
		// Tags
		ARROW_MARK_BLOCK_TAG = TagKey.create(Registries.BLOCK, Global.modId("block_tag"));
		ARROW_MARK_ITEM_TAG = TagKey.create(Registries.ITEM, Global.modId("item_tag"));
		// Items and blocks
		arrowMarkColors = ArrowMarkColor.values();
		arrowMarks = new ArrowMarkVariant[arrowMarkColors.length];
		for (int i = 0; i < arrowMarkColors.length; i++) {
			arrowMarks[i] = new ArrowMarkVariant(
				arrowMarkColors[i].name + "_arrow_mark");
		}
	}
}
