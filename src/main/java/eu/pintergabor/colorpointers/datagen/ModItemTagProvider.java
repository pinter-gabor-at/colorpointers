package eu.pintergabor.colorpointers.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.main.Main;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.world.item.Item;

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
		final TagAppender<Item, Item> tagBuilder =
			valueLookupBuilder(Main.ARROW_MARK_ITEM_TAG);
		Arrays.stream(Main.arrowMarks)
			.map(arrowMarkVariant -> arrowMarkVariant.item)
			.forEach(tagBuilder::add);
	}
}
