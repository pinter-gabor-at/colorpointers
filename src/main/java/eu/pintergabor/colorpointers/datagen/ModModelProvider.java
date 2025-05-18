package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import java.util.Arrays;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;


public final class ModModelProvider extends FabricModelProvider {

	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	/**
	 * Generate block models and block states.
	 */
	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		final ModModelGenerator generator = new ModModelGenerator(blockStateModelGenerator);
		Arrays.stream(arrowMarks)
			.map(arrowMark -> arrowMark.block)
			.forEach(generator::registerFlat9Direction);
	}

	/**
	 * Generate item models.
	 */
	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		Arrays.stream(arrowMarks)
			.forEach(arrowMark ->
				itemModelGenerator.generateFlatItem(arrowMark.item, ModelTemplates.FLAT_ITEM));
	}
}
