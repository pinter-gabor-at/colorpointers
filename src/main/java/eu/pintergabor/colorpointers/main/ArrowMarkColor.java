package eu.pintergabor.colorpointers.main;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;


/**
 * Color names, primary colors, dye item tags and carpets
 */
public enum ArrowMarkColor {
	WHITE("white", 0xF9FFFE, ConventionalItemTags.WHITE_DYES, Items.WHITE_CARPET),
	ORANGE("orange", 0xF9801D, ConventionalItemTags.ORANGE_DYES, Items.ORANGE_CARPET),
	MAGENTA("magenta", 0xC74EBD, ConventionalItemTags.MAGENTA_DYES, Items.MAGENTA_CARPET),
	LIGHT_BLUE("light_blue", 0x3AB3DA, ConventionalItemTags.LIGHT_BLUE_DYES, Items.LIGHT_BLUE_CARPET),
	YELLOW("yellow", 0xFED83D, ConventionalItemTags.YELLOW_DYES, Items.YELLOW_CARPET),
	LIME("lime", 0x80C71F, ConventionalItemTags.LIME_DYES, Items.LIME_CARPET),
	PINK("pink", 0xF38BAA, ConventionalItemTags.PINK_DYES, Items.PINK_CARPET),
	GRAY("gray", 0x474F52, ConventionalItemTags.GRAY_DYES, Items.GRAY_CARPET),
	LIGHT_GRAY("light_gray", 0x9D9D97, ConventionalItemTags.LIGHT_GRAY_DYES, Items.LIGHT_GRAY_CARPET),
	CYAN("cyan", 0x169C9C, ConventionalItemTags.CYAN_DYES, Items.CYAN_CARPET),
	PURPLE("purple", 0x8932B8, ConventionalItemTags.PURPLE_DYES, Items.PURPLE_CARPET),
	BLUE("blue", 0x3C44AA, ConventionalItemTags.BLUE_DYES, Items.BLUE_CARPET),
	BROWN("brown", 0x835432, ConventionalItemTags.BROWN_DYES, Items.BROWN_CARPET),
	GREEN("green", 0x5E7C16, ConventionalItemTags.GREEN_DYES, Items.GREEN_CARPET),
	RED("red", 0xB02E26, ConventionalItemTags.RED_DYES, Items.RED_CARPET),
	BLACK("black", 0x1D1D21, ConventionalItemTags.BLACK_DYES, Items.BLACK_CARPET);

	/**
	 * Name of the color
	 */
	public final String name;

	/**
	 * Primary color of the corresponding items and blocks
	 */
	@SuppressWarnings("unused")
	public final int color;

	/**
	 * Dye used in the crafting recipes of the corresponding items
	 */
	public final TagKey<Item> dyeTagKey;

	/**
	 * Carpet used in the crafting recipes of the corresponding items
	 */
	public final Item carpet;

	/**
	 * @param name      {@link #name}
	 * @param color     {@link #color}
	 * @param dyetagkey {@link #dyeTagKey}
	 * @param carpet    {@link #carpet}
	 */
	ArrowMarkColor(String name, int color, TagKey<Item> dyetagkey, Item carpet) {
		this.name = name;
		this.color = color;
		this.dyeTagKey = dyetagkey;
		this.carpet = carpet;
	}

	@SuppressWarnings("unused")
	public String asString() {
		return this.name;
	}
}
