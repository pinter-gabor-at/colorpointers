package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import java.util.Arrays;
import java.util.Set;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import eu.pintergabor.colorpointers.main.Main;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;


public final class ModBlockLootTableGenerator extends BlockLootSubProvider {

	public ModBlockLootTableGenerator(HolderLookup.Provider lookupProvider) {
		super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
	}

	/**
	 * NeoForge requires it.
	 */
	@Override
	@NotNull
	protected Iterable<Block> getKnownBlocks() {
		return Main.BLOCKS.getEntries()
			.stream()
			.map(e -> (Block) e.get())
			.toList();
	}

	/**
	 * Every {@link ArrowMarkBlock} drops the corresponding {@link ArrowMarkItem}.
	 */
	@Override
	public void generate() {
		Arrays.stream(arrowMarks)
			.forEach(mark ->
				dropOther(mark.block.get(), mark.item.get()));
	}
}
