package eu.pintergabor.colorpointers.main;

import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;


/**
 * Color names, primary colors, dye item tags and carpets.
 */
public enum ArrowMarkColor {
	WHITE("white", 0xF9FFFE, Tags.Items.DYES_WHITE, Items.WHITE_CARPET),
	ORANGE("orange", 0xF9801D, Tags.Items.DYES_ORANGE, Items.ORANGE_CARPET),
	MAGENTA("magenta", 0xC74EBD, Tags.Items.DYES_MAGENTA, Items.MAGENTA_CARPET),
	LIGHT_BLUE("light_blue", 0x3AB3DA, Tags.Items.DYES_LIGHT_BLUE, Items.LIGHT_BLUE_CARPET),
	YELLOW("yellow", 0xFED83D, Tags.Items.DYES_YELLOW, Items.YELLOW_CARPET),
	LIME("lime", 0x80C71F, Tags.Items.DYES_LIME, Items.LIME_CARPET),
	PINK("pink", 0xF38BAA, Tags.Items.DYES_PINK, Items.PINK_CARPET),
	GRAY("gray", 0x474F52, Tags.Items.DYES_GRAY, Items.GRAY_CARPET),
	LIGHT_GRAY("light_gray", 0x9D9D97, Tags.Items.DYES_LIGHT_GRAY, Items.LIGHT_GRAY_CARPET),
	CYAN("cyan", 0x169C9C, Tags.Items.DYES_CYAN, Items.CYAN_CARPET),
	PURPLE("purple", 0x8932B8, Tags.Items.DYES_PURPLE, Items.PURPLE_CARPET),
	BLUE("blue", 0x3C44AA, Tags.Items.DYES_BLUE, Items.BLUE_CARPET),
	BROWN("brown", 0x835432, Tags.Items.DYES_BROWN, Items.BROWN_CARPET),
	GREEN("green", 0x5E7C16, Tags.Items.DYES_GREEN, Items.GREEN_CARPET),
	RED("red", 0xB02E26, Tags.Items.DYES_RED, Items.RED_CARPET),
	BLACK("black", 0x1D1D21, Tags.Items.DYES_BLACK, Items.BLACK_CARPET);

	/**
	 * Name of the color.
	 */
	public final String name;

	/**
	 * Primary color of the corresponding items and blocks.
	 */
	@SuppressWarnings("unused")
	public final int color;

	/**
	 * Dye used in the crafting recipes of the corresponding items.
	 */
	public final TagKey<Item> dyeTagKey;

	/**
	 * Carpet used in the crafting recipes of the corresponding items.
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
	@NotNull
	public String asString() {
		return this.name;
	}
}
