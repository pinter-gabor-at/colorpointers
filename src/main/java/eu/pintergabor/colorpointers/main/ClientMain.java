package eu.pintergabor.colorpointers.main;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import net.minecraft.client.render.RenderLayer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

public class ClientMain {

	/**
	 * ArrowMarks are transparent
	 */
	public static void init() {
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			BlockRenderLayerMap.INSTANCE.putBlock(
					arrowMark.block, RenderLayer.getCutout());
		}
	}
}
