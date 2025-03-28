package eu.pintergabor.colorpointers.datagen;

import java.util.Optional;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;

import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.BlockStateVariantMap;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.ModelIds;
import net.minecraft.client.data.TextureKey;
import net.minecraft.client.data.TextureMap;
import net.minecraft.client.data.VariantsBlockModelDefinitionCreator;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import static net.minecraft.client.data.BlockStateModelGenerator.*;


public class ModModelGenerator {
	private final BlockStateModelGenerator generator;

	public ModModelGenerator(BlockStateModelGenerator generator) {
		this.generator = generator;
	}

	/**
	 * Create blockstates for all directions and one orientation
	 *
	 * @param map     to add the blockstates
	 * @param modelId block model *.json file name
	 */
	public static void registerFlatNormal(
		BlockStateVariantMap.DoubleProperty<WeightedVariant, Direction, Integer> map,
		int orientation, Identifier modelId) {
		map.register(Direction.DOWN, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_180));
		map.register(Direction.UP, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId));
		map.register(Direction.NORTH, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_270.then(ROTATE_Y_180)));
		map.register(Direction.SOUTH, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_270));
		map.register(Direction.WEST, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_270.then(ROTATE_Y_90)));
		map.register(Direction.EAST, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_270.then(ROTATE_Y_270)));
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
		BlockStateVariantMap.DoubleProperty<WeightedVariant, Direction, Integer> map,
		int orientation, Identifier modelId) {
		map.register(Direction.DOWN, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_180.then(ROTATE_Y_180)));
		map.register(Direction.UP, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_Y_180));
		map.register(Direction.NORTH, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_90));
		map.register(Direction.SOUTH, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_90.then(ROTATE_Y_180)));
		map.register(Direction.WEST, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_90.then(ROTATE_Y_270)));
		map.register(Direction.EAST, orientation,
			BlockStateModelGenerator.createWeightedVariant(modelId)
				.apply(ROTATE_X_90.then(ROTATE_Y_90)));
	}

	public static final TextureKey TEXTUREKEY_SHAFT = TextureKey.of("shaft");
	public static final TextureKey TEXTUREKEY_HEAD = TextureKey.of("head");
	public static final String PARENT = "block/template_arrow_mark";

	/**
	 * Create center model
	 */
	public Identifier createCenterModel(Block block) {
		Model model = new Model(Optional.of(Global.ModIdentifier(PARENT)),
			Optional.empty(), TextureKey.TEXTURE);
		return generator.createSubModel(block, "", model, identifier -> new TextureMap()
			.put(TextureKey.TEXTURE, ModelIds.getBlockModelId(block)));
	}

	/**
	 * Create shaft+head type models
	 */
	public Identifier createShaftHeadModel(Block block, String suffix) {
		Model model = new Model(Optional.of(Global.ModIdentifier(PARENT + suffix)),
			Optional.empty(), TEXTUREKEY_SHAFT, TEXTUREKEY_HEAD);
		return generator.createSubModel(block, suffix, model, identifier -> new TextureMap()
			.put(TEXTUREKEY_SHAFT, ModelIds.getBlockSubModelId(block, "_shaft"))
			.put(TEXTUREKEY_HEAD, ModelIds.getBlockSubModelId(block, "_head")));
	}

	/**
	 * Create models and blockstates for 6 directions and 9 orientations
	 */
	public BlockStateVariantMap<WeightedVariant> createFlat9Direction(Block block) {
		// Models
		Identifier center = createCenterModel(block);
		Identifier topleft = createShaftHeadModel(block, "_top_left");
		Identifier top = createShaftHeadModel(block, "_top");
		Identifier topright = createShaftHeadModel(block, "_top_right");
		Identifier right = createShaftHeadModel(block, "_right");
		// BlockStates
		var map = BlockStateVariantMap
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
