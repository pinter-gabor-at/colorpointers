package eu.pintergabor.colorpointers.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.main.Main;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModItemTagProvider extends TagsProvider<Item> {

	@SuppressWarnings("unused")
	public ModItemTagProvider(
		final PackOutput output,
		final CompletableFuture<HolderLookup.Provider> lookupProvider,
		final CompletableFuture<TagsProvider.TagLookup<Block>> blockTagProvider
	) {
		super(output, Registries.ITEM, lookupProvider, Global.MODID);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		Arrays.stream(Main.arrowMarks)
			.forEach(mark ->
				tag(Main.ARROW_MARK_ITEM_TAG).add(mark.item.getKey()));
	}
}
