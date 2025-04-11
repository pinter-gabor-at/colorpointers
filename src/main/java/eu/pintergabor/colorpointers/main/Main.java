package eu.pintergabor.colorpointers.main;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.ModCommon;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public final class Main {
	public static final DeferredRegister.Items ITEMS =
		DeferredRegister.createItems(Global.MODID);
	public static final DeferredRegister.Blocks BLOCKS =
		DeferredRegister.createBlocks(Global.MODID);

	/**
	 * All {@link ArrowMarkColor} enum values in an array.
	 * <p>
	 * Read-only outside its class.
	 */
	public static ArrowMarkColor[] arrowMarkColors;

	/**
	 * All arrow mark variants.
	 * <p>
	 * Read-only outside its class.
	 */
	public static ArrowMarkVariant[] arrowMarks;

	/**
	 * A tag for all arrow mark variants.
	 * <p>
	 * Used to set up mineable rules. Read-only outside its class.
	 */
	public static TagKey<Block> ARROW_MARK_BLOCK_TAG;

	/**
	 * A tag for all arrow mark items.
	 * <p>
	 * Used in coloring recipes. Read-only outside its class.
	 */
	public static TagKey<Item> ARROW_MARK_ITEM_TAG;

	/**
	 * Initialize tags.
	 */
	private static void initTags() {
		ARROW_MARK_BLOCK_TAG = TagKey.create(Registries.BLOCK, Global.modId("block_tag"));
		ARROW_MARK_ITEM_TAG = TagKey.create(Registries.ITEM, Global.modId("item_tag"));
	}

	/**
	 * Called from {@link ModCommon}.
	 */
	public static void init(IEventBus modEventBus) {
		// Tags.
		initTags();
		// Items and blocks.
		arrowMarkColors = ArrowMarkColor.values();
		arrowMarks = new ArrowMarkVariant[arrowMarkColors.length];
		for (int i = 0; i < arrowMarkColors.length; i++) {
			arrowMarks[i] = new ArrowMarkVariant(
				arrowMarkColors[i].name + "_arrow_mark");
		}
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
	}
}
