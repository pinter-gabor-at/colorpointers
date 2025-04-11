package eu.pintergabor.colorpointers;

import eu.pintergabor.colorpointers.datagen.ModBlockLootTableGenerator;
import eu.pintergabor.colorpointers.datagen.ModBlockTagProvider;
import eu.pintergabor.colorpointers.datagen.ModItemTagProvider;
import eu.pintergabor.colorpointers.datagen.ModModelProvider;
import eu.pintergabor.colorpointers.datagen.ModRecipeRunner;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public final class Datagen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeRunner::new);
		pack.addProvider(ModBlockLootTableGenerator::new);
	}
}
