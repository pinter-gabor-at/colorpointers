package eu.pintergabor.colorpointers.datagen;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

import java.util.Optional;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;


public class ModModelGenerator {
	private final BlockModelGenerators generator;

	public ModModelGenerator(BlockModelGenerators generator) {
		this.generator = generator;
	}

	/**
	 * Create blockstates for all directions and one orientation.
	 *
	 * @param map     to add the blockstates.
	 * @param modelId block model *.json file name.
	 */
	public static void registerFlatNormal(
		PropertyDispatch.C2<MultiVariant, Direction, Integer> map,
		int orientation, ResourceLocation modelId) {
		map.select(Direction.DOWN, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_180));
		map.select(Direction.UP, orientation,
			BlockModelGenerators.plainVariant(modelId));
		map.select(Direction.NORTH, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_270.then(Y_ROT_180)));
		map.select(Direction.SOUTH, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_270));
		map.select(Direction.WEST, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_270.then(Y_ROT_90)));
		map.select(Direction.EAST, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_270.then(Y_ROT_270)));
	}

	/**
	 * Create blockstates for all directions and one orientation.
	 * <p>
	 * Flip model 180 degree in Y direction.
	 *
	 * @param map     to add the blockstates.
	 * @param modelId block model *.json file name.
	 */
	public static void registerFlatFlipped(
		PropertyDispatch.C2<MultiVariant, Direction, Integer> map,
		int orientation, ResourceLocation modelId) {
		map.select(Direction.DOWN, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_180.then(Y_ROT_180)));
		map.select(Direction.UP, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(Y_ROT_180));
		map.select(Direction.NORTH, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_90));
		map.select(Direction.SOUTH, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_90.then(Y_ROT_180)));
		map.select(Direction.WEST, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_90.then(Y_ROT_270)));
		map.select(Direction.EAST, orientation,
			BlockModelGenerators.plainVariant(modelId)
				.with(X_ROT_90.then(Y_ROT_90)));
	}

	public static final TextureSlot TEXTUREKEY_SHAFT = TextureSlot.create("shaft");
	public static final TextureSlot TEXTUREKEY_HEAD = TextureSlot.create("head");
	public static final String PARENT = "block/template_arrow_mark";

	/**
	 * Create center model.
	 */
	public ResourceLocation createCenterModel(Block block) {
		ModelTemplate model = new ModelTemplate(Optional.of(Global.modId(PARENT)),
			Optional.empty(), TextureSlot.TEXTURE);
		return generator.createSuffixedVariant(block, "", model,
			identifier -> new TextureMapping()
				.put(TextureSlot.TEXTURE, ModelLocationUtils.getModelLocation(block)));
	}

	/**
	 * Create shaft+head type models.
	 */
	public ResourceLocation createShaftHeadModel(Block block, String suffix) {
		ModelTemplate model = new ModelTemplate(Optional.of(Global.modId(PARENT + suffix)),
			Optional.empty(), TEXTUREKEY_SHAFT, TEXTUREKEY_HEAD);
		return generator.createSuffixedVariant(block, suffix, model, identifier -> new TextureMapping()
			.put(TEXTUREKEY_SHAFT, ModelLocationUtils.getModelLocation(block, "_shaft"))
			.put(TEXTUREKEY_HEAD, ModelLocationUtils.getModelLocation(block, "_head")));
	}

	/**
	 * Create models and blockstates for 6 directions and 9 orientations.
	 */
	public PropertyDispatch<MultiVariant> createFlat9Direction(Block block) {
		// Models.
		ResourceLocation center = createCenterModel(block);
		ResourceLocation topleft = createShaftHeadModel(block, "_top_left");
		ResourceLocation top = createShaftHeadModel(block, "_top");
		ResourceLocation topright = createShaftHeadModel(block, "_top_right");
		ResourceLocation right = createShaftHeadModel(block, "_right");
		// Block states.
		var map = PropertyDispatch
			.initial(BlockStateProperties.FACING, ArrowMarkBlock.ORIENTATION);
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
		generator.blockStateOutput.accept(MultiVariantGenerator
			.dispatch(block).with(createFlat9Direction(block)));
	}
}
