package eu.pintergabor.colorpointers.datagen;

import java.util.Optional;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.util.ModIdentifier;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ModModelGenerator {
	private final BlockStateModelGenerator generator;

	public ModModelGenerator(BlockStateModelGenerator generator) {
		this.generator = generator;
	}

	/**
	 * Create blockstates for all directions and one orientation
	 * @param map to add the blockstates
	 * @param modelId block model *.json file name
	 */
	public static void registerFlatNormal(
		BlockStateVariantMap.DoubleProperty<Direction, Integer> map,
		int orientation, Identifier modelId) {
		map.register(Direction.DOWN, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R180)
		);
		map.register(Direction.UP, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
		);
		map.register(Direction.NORTH, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R270)
			.put(VariantSettings.Y, VariantSettings.Rotation.R180)
		);
		map.register(Direction.SOUTH, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R270)
		);
		map.register(Direction.WEST, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R270)
			.put(VariantSettings.Y, VariantSettings.Rotation.R90)
		);
		map.register(Direction.EAST, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R270)
			.put(VariantSettings.Y, VariantSettings.Rotation.R270)
		);
	}

	/**
	 * Create blockstates for all directions and one orientation
	 * <p>
	 * Flip model 180 degree in Y direction
	 * @param map to add the blockstates
	 * @param modelId block model *.json file name
	 */
	public static void registerFlatFlipped(
		BlockStateVariantMap.DoubleProperty<Direction, Integer> map,
		int orientation, Identifier modelId) {
		map.register(Direction.DOWN, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R180)
			.put(VariantSettings.Y, VariantSettings.Rotation.R180)
		);
		map.register(Direction.UP, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.Y, VariantSettings.Rotation.R180)
		);
		map.register(Direction.NORTH, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R90)
		);
		map.register(Direction.SOUTH, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R90)
			.put(VariantSettings.Y, VariantSettings.Rotation.R180)
		);
		map.register(Direction.WEST, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R90)
			.put(VariantSettings.Y, VariantSettings.Rotation.R270)
		);
		map.register(Direction.EAST, orientation, BlockStateVariant.create()
			.put(VariantSettings.MODEL, modelId)
			.put(VariantSettings.X, VariantSettings.Rotation.R90)
			.put(VariantSettings.Y, VariantSettings.Rotation.R90)
		);
	}

	public static final TextureKey TEXTUREKEY_SHAFT = TextureKey.of("shaft");
	public static final TextureKey TEXTUREKEY_HEAD = TextureKey.of("head");
	public static final String PARENT = "block/template_arrow_mark";

	/**
	 * Create center model
	 */
	public Identifier createCenterModel(Block block) {
		Model model = new Model(Optional.of(new ModIdentifier(PARENT)),
			Optional.empty(), TextureKey.TEXTURE);
		return generator.createSubModel(block, "", model, identifier -> new TextureMap()
			.put(TextureKey.TEXTURE, ModelIds.getBlockModelId(block)));
	}

	/**
	 * Create shaft+head type models
	 */
	public Identifier createShaftHeadModel(Block block, String suffix) {
		Model model = new Model(Optional.of(new ModIdentifier(PARENT + suffix)),
			Optional.empty(), TEXTUREKEY_SHAFT, TEXTUREKEY_HEAD);
		return generator.createSubModel(block, suffix, model, identifier -> new TextureMap()
			.put(TEXTUREKEY_SHAFT, ModelIds.getBlockSubModelId(block, "_shaft"))
			.put(TEXTUREKEY_HEAD, ModelIds.getBlockSubModelId(block, "_head")));
	}

	/**
	 * Create models and blockstates for 6 directions and 9 orientations
	 */
	public BlockStateVariantMap createFlat9Direction(Block block) {
		// Models
		Identifier center = createCenterModel(block);
		Identifier topleft = createShaftHeadModel(block, "_top_left");
		Identifier top = createShaftHeadModel(block, "_top");
		Identifier topright = createShaftHeadModel(block, "_top_right");
		Identifier right = createShaftHeadModel(block, "_right");
		// BlockStates
		var map = BlockStateVariantMap
			.create(Properties.FACING, ArrowMarkBlock.ORIENTATION);
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
		generator.blockStateCollector.accept(VariantsBlockStateSupplier
			.create(block).coordinate(createFlat9Direction(block)));
	}
}
