package eu.pintergabor.colorpointers.datagen;

import java.util.concurrent.CompletableFuture;

import eu.pintergabor.colorpointers.Global;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;


public class ModRecipeRunner extends FabricRecipeProvider {

	public ModRecipeRunner(
		FabricDataOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	@NotNull
	protected RecipeProvider createRecipeProvider(
		HolderLookup.Provider registryLookup, RecipeOutput output) {
		return new ModRecipeGenerator(registryLookup, output);
	}

	@Override
	@NotNull
	public String getName() {
		return Global.MODID + " recipes";
	}
}
