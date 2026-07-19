package eu.pintergabor.colorpointers.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.main.Main;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.world.item.Item;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;


public final class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public ModItemTagProvider(
		FabricPackOutput output,
		CompletableFuture<HolderLookup.Provider> completableFuture
	) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider registries) {
		final TagAppender<Item, Item> tagBuilder =
			valueLookupBuilder(Main.ARROW_MARK_ITEM_TAG);
		Arrays.stream(Main.arrowMarks)
			.map(arrowMarkVariant -> arrowMarkVariant.item)
			.forEach(tagBuilder::add);
	}
}
