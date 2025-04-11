package eu.pintergabor.colorpointers;

import eu.pintergabor.colorpointers.main.Main;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;


/**
 * Common startup.
 */
@Mod(Global.MODID)
public final class ModCommon {

	@SuppressWarnings("unused")
	public ModCommon(IEventBus modEventBus, ModContainer modContainer, Dist dist) {
		// Create and register everything.
		Main.init(modEventBus);
	}
}
