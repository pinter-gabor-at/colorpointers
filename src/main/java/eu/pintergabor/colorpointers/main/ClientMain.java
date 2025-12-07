package eu.pintergabor.colorpointers.main;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;


public final class ClientMain {

	/**
	 * ArrowMarks are transparent.
	 */
	public static void init() {
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			BlockRenderLayerMap.putBlock(
				arrowMark.block, ChunkSectionLayer.CUTOUT);
		}
	}
}
