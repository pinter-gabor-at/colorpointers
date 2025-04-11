package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import eu.pintergabor.colorpointers.main.ArrowMarkVariant;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;


public class ModModelProvider extends FabricModelProvider {

	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	/**
	 * Generate block models and block states.
	 */
	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		ModModelGenerator generator = new ModModelGenerator(blockStateModelGenerator);
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			generator.registerFlat9Direction(arrowMark.block);
		}
	}

	/**
	 * Generate item models.
	 */
	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			itemModelGenerator.generateFlatItem(arrowMark.item, ModelTemplates.FLAT_ITEM);
		}
	}
}
