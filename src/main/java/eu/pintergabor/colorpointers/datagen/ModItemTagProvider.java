package eu.pintergabor.colorpointers.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.main.Main;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;


public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public ModItemTagProvider(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		FabricTagBuilder tagBuilder =
			getOrCreateTagBuilder(Main.ARROW_MARK_ITEM_TAG);
		for (int i = 0; i < Main.arrowMarks.length; i++) {
			tagBuilder.add(Main.arrowMarks[i].item);
		}
	}
}
