package eu.pintergabor.colorpointers.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.main.Main;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;


public class ModBlockTagProvider extends BlockTagsProvider {

	public ModBlockTagProvider(
		PackOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture, Global.MODID);
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider arg) {
		for (int i = 0; i < Main.arrowMarks.length; i++) {
			tag(Main.ARROW_MARK_BLOCK_TAG).add(Main.arrowMarks[i].block.get());
		}
	}
}
