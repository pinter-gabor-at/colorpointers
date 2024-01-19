package eu.pintergabor.colorpointers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Global {

	// Used for logging and registration
	public static final String MODID = "colorpointers";

	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	// Parameters, some of which one day might be player configurable

	// ArrowMarkBlock hitbox margin: 0 … 2
	public static final double margin = 2D;
	// ArrowMarkBlock hitbox thickness: 0.001 … 2
	public static final double thick = 0.001D;

	// ArrowMarkBlock luminance
	public static final int arrowMarkBlockLumi = 1;
}
