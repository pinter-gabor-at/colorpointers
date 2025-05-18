package eu.pintergabor.colorpointers.datagen;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import eu.pintergabor.colorpointers.main.Main;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;


public final class ModRecipeGenerator extends RecipeProvider {

	public ModRecipeGenerator(
		HolderLookup.Provider registries, RecipeOutput output
	) {
		super(registries, output);
	}

	/**
	 * Generate primary {@link ArrowMarkItem} recipes.
	 *
	 * @param arrowItem The recipe for this item.
	 * @param carpet    Same color carpet item.
	 */
	private void generateArrowMarkItemRecipe(Item arrowItem, Item carpet) {
		shaped(RecipeCategory.MISC, arrowItem, 2)
			.pattern(" /")
			.pattern("C ")
			.define('/', Items.ARROW)
			.define('C', carpet)
			.unlockedBy(getHasName(Items.ARROW), has(Items.ARROW))
			.save(output);
	}

	/**
	 * Generate {@link ArrowMarkItem} repaint recipes.
	 *
	 * @param arrowItem The recipe for this item.
	 * @param dye       Same color DyeTag.
	 */
	private void paintArrowMarkItemRecipe(Item arrowItem, TagKey<Item> dye) {
		shapeless(RecipeCategory.MISC, arrowItem)
			.requires(Main.ARROW_MARK_ITEM_TAG)
			.requires(dye)
			.unlockedBy(getHasName(Items.ARROW), has(Items.ARROW))
			.save(output, Global.modName(getSimpleRecipeName(arrowItem) + "_dye"));
	}

	@Override
	public void buildRecipes() {
		for (int i = 0; i < Main.arrowMarks.length; i++) {
			generateArrowMarkItemRecipe(
				Main.arrowMarks[i].item,
				Main.arrowMarkColors[i].carpet);
			paintArrowMarkItemRecipe(
				Main.arrowMarks[i].item,
				Main.arrowMarkColors[i].dyeTagKey);
		}
	}
}
