package eu.pintergabor.colorpointers.datagen;

import eu.pintergabor.colorpointers.main.ArrowRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		ExBlockStateModelGenerator exGenerator = new ExBlockStateModelGenerator(blockStateModelGenerator);
		exGenerator.registerFlat9Direction(ArrowRegistry.arrowMarkBlock);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ArrowRegistry.arrowMarkItem, Models.GENERATED);
	}
}
