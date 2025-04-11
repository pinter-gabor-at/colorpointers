package eu.pintergabor.colorpointers;

import eu.pintergabor.colorpointers.datagen.ModBlockLootTableGenerator;
import eu.pintergabor.colorpointers.datagen.ModBlockTagProvider;
import eu.pintergabor.colorpointers.datagen.ModItemTagProvider;
import eu.pintergabor.colorpointers.datagen.ModModelProvider;
import eu.pintergabor.colorpointers.datagen.ModRecipeRunner;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;


public final class DataGen {

	public static void init(GatherDataEvent.Client event) {
		// Create recipes.
		event.createProvider(ModRecipeRunner::new);
		// Create models.
		event.createProvider(ModModelProvider::new);
		// Create tags.
		event.createBlockAndItemTags(ModBlockTagProvider::new, ModItemTagProvider::new);
		// Create loot tables.
		event.createProvider((output, lookupProvider) ->
			new LootTableProvider(output, Set.of(), List.of(
				new LootTableProvider.SubProviderEntry(
					ModBlockLootTableGenerator::new,
					LootContextParamSets.BLOCK)), lookupProvider));
	}
}
