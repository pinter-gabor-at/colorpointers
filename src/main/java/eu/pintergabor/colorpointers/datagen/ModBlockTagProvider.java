package eu.pintergabor.colorpointers.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.main.Main;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;


public final class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public ModBlockTagProvider(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		final FabricTagBuilder tagBuilder =
			getOrCreateTagBuilder(Main.ARROW_MARK_BLOCK_TAG);
		Arrays.stream(Main.arrowMarks)
			.map(arrowMarkVariant -> arrowMarkVariant.block)
			.forEach(tagBuilder::add);
	}
}
