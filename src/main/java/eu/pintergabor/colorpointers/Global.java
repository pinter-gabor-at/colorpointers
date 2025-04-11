package eu.pintergabor.colorpointers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.ResourceLocation;


public final class Global {

	private Global() {
		// Static class.
	}

	/**
	 * Used for logging and registration.
	 */
	public static final String MODID = "colorpointers";

	/**
	 * This logger is used to write text to the console and the log file.
	 */
	@SuppressWarnings("unused")
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	/**
	 * Create a mod specific name.
	 *
	 * @param path Name without {@link #MODID}.
	 */
	@SuppressWarnings("unused")
	public static String modName(String path) {
		return MODID + ":" + path;
	}

	// ArrowMarkBlock hitbox margin: 0 ... 2
	public static final double margin = 1D;
	// ArrowMarkBlock hitbox thickness: 0.001 ... 2
	public static final double thickness = 0.001D;

	// ArrowMarkBlock luminance.
	public static final int arrowMarkBlockLumi = 1;

	/**
	 * Create a mod specific identifier.
	 *
	 * @param path Name without {@link #MODID}.
	 */
	@SuppressWarnings("unused")
	public static ResourceLocation modId(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}
}
