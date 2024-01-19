package eu.pintergabor.colorpointers.main;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static eu.pintergabor.colorpointers.main.ArrowRegistry.arrowMarkBlock;

public class ClientArrowRegistry {

	public static void registerClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(arrowMarkBlock, RenderLayer.getCutout());
	}
}
