package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import java.util.Arrays;

import eu.pintergabor.colorpointers.Global;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;


public final class ModModelProvider extends ModelProvider {

	public ModModelProvider(PackOutput output) {
		super(output, Global.MODID);
	}

	/**
	 * Generate block models and block states.
	 */
	private static void generateBlockStateModel(@NotNull BlockModelGenerators blockModels) {
		final ModModelGenerator generator = new ModModelGenerator(blockModels);
		Arrays.stream(arrowMarks)
			.map(arrowMark -> arrowMark.block.get())
			.forEach(generator::registerFlat9Direction);
	}

	/**
	 * Generate item models.
	 */
	private static void generateItemModels(@NotNull ItemModelGenerators itemModels) {
		Arrays.stream(arrowMarks)
			.forEach(arrowMark ->
				itemModels.generateFlatItem(arrowMark.item.get(), ModelTemplates.FLAT_ITEM));
	}

	/**
	 * Generate blockstates, block and item models.
	 */
	@Override
	protected void registerModels(
		@NotNull BlockModelGenerators blockModels,
		@NotNull ItemModelGenerators itemModels
	) {
		// Items.
		generateItemModels(itemModels);
		// Block models and block states.
		generateBlockStateModel(blockModels);
	}
}
