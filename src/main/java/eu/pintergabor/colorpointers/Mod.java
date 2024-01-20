package eu.pintergabor.colorpointers;

import eu.pintergabor.colorpointers.main.ArrowRegistry;
import eu.pintergabor.colorpointers.main.Main;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
    public static final Logger LOGGER =
			LoggerFactory.getLogger("colorpointers");

	@Override
	public void onInitialize() {
		Main.init();
		ArrowRegistry.init();
	}
}
