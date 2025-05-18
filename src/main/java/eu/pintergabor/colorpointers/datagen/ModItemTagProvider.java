package eu.pintergabor.colorpointers.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.main.Main;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;


public final class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public ModItemTagProvider(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		final FabricTagBuilder tagBuilder =
			getOrCreateTagBuilder(Main.ARROW_MARK_ITEM_TAG);
		Arrays.stream(Main.arrowMarks)
			.map(arrowMarkVariant -> arrowMarkVariant.item)
			.forEach(tagBuilder::add);
	}
}
