package eu.pintergabor.colorpointers.datagen;

import eu.pintergabor.colorpointers.main.ArrowMarkVariant;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * Generate block models and block states
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        ModModelGenerator generator = new ModModelGenerator(blockStateModelGenerator);
        for (ArrowMarkVariant arrowMark : arrowMarks) {
            generator.registerFlat9Direction(arrowMark.block);
        }
    }

    /**
     * Generate item models
     */
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (ArrowMarkVariant arrowMark : arrowMarks) {
            itemModelGenerator.register(arrowMark.item, Models.GENERATED);
        }
    }
}
