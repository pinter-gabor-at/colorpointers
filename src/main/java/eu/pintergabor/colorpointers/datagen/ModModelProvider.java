package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import java.util.Arrays;

import org.jspecify.annotations.NonNull;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;


public final class ModModelProvider extends FabricModelProvider {

	public ModModelProvider(FabricPackOutput output) {
		super(output);
	}

	/**
	 * Generate block models and block states.
	 */
	@Override
	public void generateBlockStateModels(
		final @NonNull BlockModelGenerators blockStateModelGenerator
	) {
		final ModModelGenerator generator = new ModModelGenerator(blockStateModelGenerator);
		Arrays.stream(arrowMarks)
			.map(arrowMark -> arrowMark.block)
			.forEach(generator::registerFlat9Direction);
	}

	/**
	 * Generate item models.
	 */
	@Override
	public void generateItemModels(
		final @NonNull ItemModelGenerators itemModelGenerator
	) {
		Arrays.stream(arrowMarks)
			.forEach(arrowMark ->
				itemModelGenerator.generateFlatItem(arrowMark.item, ModelTemplates.FLAT_ITEM));
	}
}
