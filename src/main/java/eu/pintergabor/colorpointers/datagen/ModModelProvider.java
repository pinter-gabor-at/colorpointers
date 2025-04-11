package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.main.ArrowMarkVariant;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

import org.jetbrains.annotations.NotNull;


public class ModModelProvider extends ModelProvider {

	public ModModelProvider(PackOutput output) {
		super(output, Global.MODID);
	}

	/**
	 * Generate block models and block states.
	 */
	private static void generateBlockStateModel(@NotNull BlockModelGenerators blockModels) {
		ModModelGenerator generator = new ModModelGenerator(blockModels);
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			generator.registerFlat9Direction(arrowMark.block.get());
		}
	}

	/**
	 * Generate item models.
	 */
	private static void generateItemModels(@NotNull ItemModelGenerators itemModels) {
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			itemModels.generateFlatItem(arrowMark.item.get(), ModelTemplates.FLAT_ITEM);
		}
	}

	/**
	 * Generate blockstates, block and item models.
	 */
	@Override
	protected void registerModels(
		@NotNull BlockModelGenerators blockModels,
		@NotNull ItemModelGenerators itemModels) {
		// Items.
		generateItemModels(itemModels);
		// Block models and block states.
		generateBlockStateModel(blockModels);
	}
}
