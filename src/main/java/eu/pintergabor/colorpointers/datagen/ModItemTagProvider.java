package eu.pintergabor.colorpointers.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.main.Main;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.NotNull;


public final class ModItemTagProvider extends ItemTagsProvider {

	public ModItemTagProvider(
		PackOutput output,
		CompletableFuture<HolderLookup.Provider> lookupProvider,
		CompletableFuture<TagsProvider.TagLookup<Block>> blockTagProvider
	) {
		super(output, lookupProvider, blockTagProvider, Global.MODID);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		Arrays.stream(Main.arrowMarks)
			.forEach(mark ->
				tag(Main.ARROW_MARK_ITEM_TAG).add(mark.item.get()));
	}
}
