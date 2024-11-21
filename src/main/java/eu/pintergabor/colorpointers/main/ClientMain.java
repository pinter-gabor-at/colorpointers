package eu.pintergabor.colorpointers.main;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

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
