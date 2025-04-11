package eu.pintergabor.colorpointers;

import eu.pintergabor.colorpointers.main.ClientMain;

import net.fabricmc.api.ClientModInitializer;


public class ModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientMain.init();
	}
}
