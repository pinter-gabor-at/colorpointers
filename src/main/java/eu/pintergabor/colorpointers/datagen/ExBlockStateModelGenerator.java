package eu.pintergabor.colorpointers.datagen;

import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ExBlockStateModelGenerator {
	private final BlockStateModelGenerator generator;

	public ExBlockStateModelGenerator(BlockStateModelGenerator generator) {
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

	/**
	 * Create blockstates for 6 directions and 9 orientation
	 */
	public static BlockStateVariantMap createFlat9Direction(Block arrowMarkBlock) {
		var map = BlockStateVariantMap
			.create(Properties.FACING, ArrowMarkBlock.ORIENTATION);
		registerFlatNormal(map, 0, ModelIds.getBlockSubModelId(arrowMarkBlock, "_top_left"));
		registerFlatNormal(map, 1, ModelIds.getBlockSubModelId(arrowMarkBlock, "_top"));
		registerFlatNormal(map, 2, ModelIds.getBlockSubModelId(arrowMarkBlock, "_top_right"));
		registerFlatFlipped(map, 3, ModelIds.getBlockSubModelId(arrowMarkBlock, "_right"));
		registerFlatNormal(map, 4, ModelIds.getBlockModelId(arrowMarkBlock));
		registerFlatNormal(map, 5, ModelIds.getBlockSubModelId(arrowMarkBlock, "_right"));
		registerFlatFlipped(map, 6, ModelIds.getBlockSubModelId(arrowMarkBlock, "_top_right"));
		registerFlatFlipped(map, 7, ModelIds.getBlockSubModelId(arrowMarkBlock, "_top"));
		registerFlatFlipped(map, 8, ModelIds.getBlockSubModelId(arrowMarkBlock, "_top_left"));
		return map;
	}

	/**
	 * Generate blockstates for a thin, flat model that has 6 directions and 9 orientation
	 */
	public void registerFlat9Direction(Block arrowMarkBlock) {
		generator.blockStateCollector.accept(VariantsBlockStateSupplier
			.create(arrowMarkBlock, BlockStateVariant.create())
			.coordinate(createFlat9Direction(arrowMarkBlock)));
	}
}
