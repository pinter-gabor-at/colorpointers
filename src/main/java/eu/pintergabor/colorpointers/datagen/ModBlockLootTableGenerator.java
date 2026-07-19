package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import org.jspecify.annotations.NonNull;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;


public final class ModBlockLootTableGenerator extends FabricBlockLootSubProvider {

	public ModBlockLootTableGenerator(
		final @NonNull FabricPackOutput dataOutput,
		final @NonNull CompletableFuture<HolderLookup.Provider> registryLookup
	) {
		super(dataOutput, registryLookup);
	}

	/**
	 * Every {@link ArrowMarkBlock} drops the corresponding {@link ArrowMarkItem}.
	 */
	@Override
	public void generate() {
		Arrays.stream(arrowMarks)
			.forEach(arrowMark -> dropOther(arrowMark.block, arrowMark.item));
	}
}
