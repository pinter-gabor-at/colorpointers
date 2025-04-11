package eu.pintergabor.colorpointers;

import eu.pintergabor.colorpointers.main.Main;

import net.fabricmc.api.ModInitializer;


public class Mod implements ModInitializer {

	@Override
	public void onInitialize() {
		Main.init();
	}
}
