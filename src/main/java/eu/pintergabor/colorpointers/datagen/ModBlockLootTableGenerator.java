package eu.pintergabor.colorpointers.datagen;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import eu.pintergabor.colorpointers.main.ArrowMarkVariant;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(
            FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    /**
     * Every {@link ArrowMarkBlock} drops the corresponding {@link ArrowMarkItem}
     */
    @Override
    public void generate() {
        for (ArrowMarkVariant arrowMark : arrowMarks) {
            addDrop(arrowMark.block, arrowMark.item);
        }
    }
}
