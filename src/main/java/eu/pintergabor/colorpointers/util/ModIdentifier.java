package eu.pintergabor.colorpointers.util;

import eu.pintergabor.colorpointers.Global;

import net.minecraft.util.Identifier;

public class ModIdentifier extends Identifier {

	/**
	 * Create a mod specific identifier
	 * @param path Name, as in lang/*.json files without "*.modid." prefix
	 */
	public ModIdentifier(String path) {
		super(Global.MODID, path);
	}
}
