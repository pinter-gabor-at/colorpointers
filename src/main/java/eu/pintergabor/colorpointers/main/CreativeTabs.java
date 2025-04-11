package eu.pintergabor.colorpointers.main;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;


public final class CreativeTabs {

	/**
	 * Add items to creative tabs.
	 */
	public static void init(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey()==CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			for (int i = 0; i < Main.arrowMarkColors.length; i++) {
				event.accept(Main.arrowMarks[i].item);
			}
		}
	}
}
