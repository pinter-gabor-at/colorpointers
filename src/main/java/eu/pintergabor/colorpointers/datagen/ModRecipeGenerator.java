package eu.pintergabor.colorpointers.datagen;

import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import eu.pintergabor.colorpointers.main.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(
            FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        for (int i = 0; i < Main.arrowMarks.length; i++) {
            generateArrowMarkItemRecipe(exporter,
                    Main.arrowMarks[i].item,
                    Main.arrowMarkColors[i].carpet);
            paintArrowMarkItemRecipe(exporter,
                    Main.arrowMarks[i].item,
                    Main.arrowMarkColors[i].dyeTagKey);
        }
    }

    /**
     * Generate primary {@link ArrowMarkItem} recipes
     *
     * @param arrowItem The recipe for this item
     * @param carpet    Same color carpet item
     */
    private void generateArrowMarkItemRecipe(
            RecipeExporter exporter, Item arrowItem, Item carpet) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, arrowItem, 2)
                .pattern(" /")
                .pattern("C ")
                .input('/', Items.ARROW)
                .input('C', carpet)
                .criterion(hasItem(Items.ARROW),
                        conditionsFromItem(Items.ARROW))
                .offerTo(exporter, new Identifier(getRecipeName(arrowItem)));
    }

    /**
     * Generate {@link ArrowMarkItem} repaint recipes
     *
     * @param arrowItem The recipe for this item
     * @param dye       Same color DyeTag
     */
    private void paintArrowMarkItemRecipe(
            RecipeExporter exporter, Item arrowItem, TagKey<Item> dye) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, arrowItem)
                .input(Main.ARROW_MARK_ITEM_TAG)
                .input(dye)
                .criterion(hasItem(Items.ARROW),
                        conditionsFromItem(Items.ARROW))
                .offerTo(exporter, new Identifier(getRecipeName(arrowItem) + "_dye"));
    }
}
