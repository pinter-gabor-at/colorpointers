package eu.pintergabor.colorpointers.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.main.Main;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;


public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public ModBlockTagProvider(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		FabricTagBuilder tagBuilder =
			getOrCreateTagBuilder(Main.ARROW_MARK_BLOCK_TAG);
		for (int i = 0; i < Main.arrowMarks.length; i++) {
			tagBuilder.add(Main.arrowMarks[i].block);
		}
	}
}
