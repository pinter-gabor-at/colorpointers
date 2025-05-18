package eu.pintergabor.colorpointers.main;

import java.util.Arrays;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;


public final class CreativeTabs {

	/**
	 * Add items to creative tabs.
	 */
	public static void init(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			Arrays.stream(Main.arrowMarks)
				.map(arrowMarkVariant -> arrowMarkVariant.item)
				.forEach(event::accept);
		}
	}
}
