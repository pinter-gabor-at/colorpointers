package eu.pintergabor.colorpointers.datagen;

import java.util.Optional;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;

import net.minecraft.client.data.models.BlockModelGenerators;
import static net.minecraft.client.data.models.BlockModelGenerators.*;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

//import net.minecraft.block.Block;
//import net.minecraft.client.data.BlockModelGenerators;
//import net.minecraft.client.data.PropertyDispatch;
//import net.minecraft.client.data.Model;
//import net.minecraft.client.data.ModelIds;
//import net.minecraft.client.data.TextureKey;
//import net.minecraft.client.data.TextureMap;
//import net.minecraft.client.data.VariantsBlockModelDefinitionCreator;
//import net.minecraft.client.render.model.json.WeightedVariant;
//import net.minecraft.state.property.Properties;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.Direction;
//
//import static net.minecraft.client.data.BlockModelGenerators.*;


public class ModModelGenerator {
	private final BlockModelGenerators generator;

	public ModModelGenerator(BlockModelGenerators generator) {
		this.generator = generator;
	}

	/**
	 * Create blockstates for all directions and one orientation
	 *
	 * @param map     to add the blockstates
	 * @param modelId block model *.json file name
	 */
	public static void registerFlatNormal(
		PropertyDispatch.DoubleProperty<WeightedVariant, Direction, Integer> map,
		int orientation, ResourceLocation modelId) {
		map.register(Direction.DOWN, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_180));
		map.register(Direction.UP, orientation,
			BlockModelGenerators.createWeightedVariant(modelId));
		map.register(Direction.NORTH, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_270.then(Y_ROT_180)));
		map.register(Direction.SOUTH, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_270));
		map.register(Direction.WEST, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_270.then(Y_ROT_90)));
		map.register(Direction.EAST, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_270.then(Y_ROT_270)));
	}

	/**
	 * Create blockstates for all directions and one orientation
	 * <p>
	 * Flip model 180 degree in Y direction
	 *
	 * @param map     to add the blockstates
	 * @param modelId block model *.json file name
	 */
	public static void registerFlatFlipped(
		PropertyDispatch.DoubleProperty<WeightedVariant, Direction, Integer> map,
		int orientation, ResourceLocation modelId) {
		map.register(Direction.DOWN, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_180.then(Y_ROT_180)));
		map.register(Direction.UP, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(Y_ROT_180));
		map.register(Direction.NORTH, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_90));
		map.register(Direction.SOUTH, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_90.then(Y_ROT_180)));
		map.register(Direction.WEST, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_90.then(Y_ROT_270)));
		map.register(Direction.EAST, orientation,
			BlockModelGenerators.createWeightedVariant(modelId)
				.apply(X_ROT_90.then(Y_ROT_90)));
	}

	public static final TextureKey TEXTUREKEY_SHAFT = TextureKey.of("shaft");
	public static final TextureKey TEXTUREKEY_HEAD = TextureKey.of("head");
	public static final String PARENT = "block/template_arrow_mark";

	/**
	 * Create center model
	 */
	public ResourceLocation createCenterModel(Block block) {
		Model model = new Model(Optional.of(Global.ModResourceLocation(PARENT)),
			Optional.empty(), TextureKey.TEXTURE);
		return generator.createSubModel(block, "", model, identifier -> new TextureMap()
			.put(TextureKey.TEXTURE, ModelIds.getBlockModelId(block)));
	}

	/**
	 * Create shaft+head type models
	 */
	public ResourceLocation createShaftHeadModel(Block block, String suffix) {
		Model model = new Model(Optional.of(Global.ModResourceLocation(PARENT + suffix)),
			Optional.empty(), TEXTUREKEY_SHAFT, TEXTUREKEY_HEAD);
		return generator.createSubModel(block, suffix, model, identifier -> new TextureMap()
			.put(TEXTUREKEY_SHAFT, ModelIds.getBlockSubModelId(block, "_shaft"))
			.put(TEXTUREKEY_HEAD, ModelIds.getBlockSubModelId(block, "_head")));
	}

	/**
	 * Create models and blockstates for 6 directions and 9 orientations
	 */
	public PropertyDispatch<WeightedVariant> createFlat9Direction(Block block) {
		// Models
		ResourceLocation center = createCenterModel(block);
		ResourceLocation topleft = createShaftHeadModel(block, "_top_left");
		ResourceLocation top = createShaftHeadModel(block, "_top");
		ResourceLocation topright = createShaftHeadModel(block, "_top_right");
		ResourceLocation right = createShaftHeadModel(block, "_right");
		// BlockStates
		var map = PropertyDispatch
			.models(Properties.FACING, ArrowMarkBlock.ORIENTATION);
		registerFlatNormal(map, 0, topleft);
		registerFlatNormal(map, 1, top);
		registerFlatNormal(map, 2, topright);
		registerFlatFlipped(map, 3, right);
		registerFlatNormal(map, 4, center);
		registerFlatNormal(map, 5, right);
		registerFlatFlipped(map, 6, topright);
		registerFlatFlipped(map, 7, top);
		registerFlatFlipped(map, 8, topleft);
		return map;
	}

	/**
	 * Generate models and blockstates for a thin, flat model that has 6 directions and 9 orientations
	 */
	public void registerFlat9Direction(Block block) {
		generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator
			.of(block).with(createFlat9Direction(block)));
	}
}
