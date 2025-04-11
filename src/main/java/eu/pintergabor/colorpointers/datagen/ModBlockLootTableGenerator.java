package eu.pintergabor.colorpointers.datagen;

import static eu.pintergabor.colorpointers.main.Main.arrowMarks;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import eu.pintergabor.colorpointers.main.ArrowMarkVariant;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;


public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {

	public ModBlockLootTableGenerator(
		FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	/**
	 * Every {@link ArrowMarkBlock} drops the corresponding {@link ArrowMarkItem}.
	 */
	@Override
	public void generate() {
		for (ArrowMarkVariant arrowMark : arrowMarks) {
			dropOther(arrowMark.block, arrowMark.item);
		}
	}
}
