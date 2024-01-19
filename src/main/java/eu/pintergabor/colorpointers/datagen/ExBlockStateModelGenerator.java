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
import net.minecraft.util.math.Direction;

public class ExBlockStateModelGenerator {
	private final BlockStateModelGenerator generator;

	public ExBlockStateModelGenerator(BlockStateModelGenerator generator) {
		this.generator = generator;
	}

	public static BlockStateVariantMap createFlat9Direction(Block arrowMarkBlock) {
		var map = BlockStateVariantMap
			.create(Properties.FACING, ArrowMarkBlock.ORIENTATION);
		for (int i = 0; i < 9; i++) {
			if (i == 4) {
				map.register(Direction.DOWN, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_center"))
					.put(VariantSettings.X, VariantSettings.Rotation.R180)
				);
				map.register(Direction.UP, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_center"))
				);
				map.register(Direction.NORTH, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_center"))
					.put(VariantSettings.X, VariantSettings.Rotation.R90)
				);
				map.register(Direction.SOUTH, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_center"))
					.put(VariantSettings.X, VariantSettings.Rotation.R90)
					.put(VariantSettings.Y, VariantSettings.Rotation.R180)
				);
				map.register(Direction.WEST, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_center"))
					.put(VariantSettings.X, VariantSettings.Rotation.R90)
					.put(VariantSettings.Y, VariantSettings.Rotation.R270)
				);
				map.register(Direction.EAST, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_center"))
					.put(VariantSettings.X, VariantSettings.Rotation.R90)
					.put(VariantSettings.Y, VariantSettings.Rotation.R90)
				);
			} else if (i == 7) {
				map.register(Direction.DOWN, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R180)
					.put(VariantSettings.Y, VariantSettings.Rotation.R180)
				);
				map.register(Direction.UP, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.Y, VariantSettings.Rotation.R180)
				);
				map.register(Direction.NORTH, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R90)
				);
				map.register(Direction.SOUTH, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R90)
					.put(VariantSettings.Y, VariantSettings.Rotation.R180)
				);
				map.register(Direction.WEST, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R90)
					.put(VariantSettings.Y, VariantSettings.Rotation.R270)
				);
				map.register(Direction.EAST, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R90)
					.put(VariantSettings.Y, VariantSettings.Rotation.R90)
				);
			} else {
				map.register(Direction.DOWN, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R180)
				);
				map.register(Direction.UP, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
				);
				map.register(Direction.NORTH, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R270)
					.put(VariantSettings.Y, VariantSettings.Rotation.R180)
				);
				map.register(Direction.SOUTH, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R270)
				);
				map.register(Direction.WEST, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R270)
					.put(VariantSettings.Y, VariantSettings.Rotation.R90)
				);
				map.register(Direction.EAST, i, BlockStateVariant.create()
					.put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(arrowMarkBlock, "_straight"))
					.put(VariantSettings.X, VariantSettings.Rotation.R270)
					.put(VariantSettings.Y, VariantSettings.Rotation.R270)
				);
			}
		}
		return map;
	}

	public void registerFlat9Direction(Block arrowMarkBlock) {
		generator.blockStateCollector.accept(VariantsBlockStateSupplier
			.create(arrowMarkBlock, BlockStateVariant.create())
			.coordinate(createFlat9Direction(arrowMarkBlock)));
	}
}
