package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import eu.pintergabor.colorpointers.main.ArrowMarkVariant;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		ExBlockStateModelGenerator exGenerator = new ExBlockStateModelGenerator(blockStateModelGenerator);
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			exGenerator.registerFlat9Direction(arrowMark.block);
		}
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			itemModelGenerator.register(arrowMark.item, Models.GENERATED);
		}
	}
}
