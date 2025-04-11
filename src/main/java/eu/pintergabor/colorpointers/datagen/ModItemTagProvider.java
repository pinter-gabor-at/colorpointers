package eu.pintergabor.colorpointers.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.main.Main;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;


public class ModItemTagProvider extends ItemTagsProvider {

	public ModItemTagProvider(
		PackOutput output,
		CompletableFuture<HolderLookup.Provider> lookupProvider,
		CompletableFuture<TagsProvider.TagLookup<Block>> blockTagProvider) {
		super(output, lookupProvider, blockTagProvider, Global.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		for (int i = 0; i < Main.arrowMarks.length; i++) {
			tag(Main.ARROW_MARK_ITEM_TAG).add(Main.arrowMarks[i].item.get());
		}
	}
}
