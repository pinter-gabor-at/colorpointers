package eu.pintergabor.colorpointers.main;

import net.neoforged.neoforge.common.Tags;
import org.jspecify.annotations.NonNull;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;


/**
 * Color names, primary colors, dye item tags and carpets.
 */
public enum ArrowMarkColor {
	WHITE("white", 0xF9FFFE, Tags.Items.DYES_WHITE, Items.CARPET.white()),
	ORANGE("orange", 0xF9801D, Tags.Items.DYES_ORANGE, Items.CARPET.orange()),
	MAGENTA("magenta", 0xC74EBD, Tags.Items.DYES_MAGENTA, Items.CARPET.magenta()),
	LIGHT_BLUE("light_blue", 0x3AB3DA, Tags.Items.DYES_LIGHT_BLUE, Items.CARPET.lightBlue()),
	YELLOW("yellow", 0xFED83D, Tags.Items.DYES_YELLOW, Items.CARPET.yellow()),
	LIME("lime", 0x80C71F, Tags.Items.DYES_LIME, Items.CARPET.lime()),
	PINK("pink", 0xF38BAA, Tags.Items.DYES_PINK, Items.CARPET.pink()),
	GRAY("gray", 0x474F52, Tags.Items.DYES_GRAY, Items.CARPET.gray()),
	LIGHT_GRAY("light_gray", 0x9D9D97, Tags.Items.DYES_LIGHT_GRAY, Items.CARPET.lightGray()),
	CYAN("cyan", 0x169C9C, Tags.Items.DYES_CYAN, Items.CARPET.cyan()),
	PURPLE("purple", 0x8932B8, Tags.Items.DYES_PURPLE, Items.CARPET.purple()),
	BLUE("blue", 0x3C44AA, Tags.Items.DYES_BLUE, Items.CARPET.blue()),
	BROWN("brown", 0x835432, Tags.Items.DYES_BROWN, Items.CARPET.brown()),
	GREEN("green", 0x5E7C16, Tags.Items.DYES_GREEN, Items.CARPET.green()),
	RED("red", 0xB02E26, Tags.Items.DYES_RED, Items.CARPET.red()),
	BLACK("black", 0x1D1D21, Tags.Items.DYES_BLACK, Items.CARPET.black());

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
	public @NonNull String asString() {
		return this.name;
	}
}
