package eu.pintergabor.colorpointers.datagen;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

import java.util.Optional;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.util.BlockRegion;
import org.jspecify.annotations.NonNull;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;


public final class ModModelGenerator {
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
		PropertyDispatch.@NonNull C2<MultiVariant, Direction, BlockRegion> map,
		final @NonNull BlockRegion orientation,
		final @NonNull Identifier modelId
	) {
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
		PropertyDispatch.@NonNull C2<MultiVariant, Direction, BlockRegion> map,
		final @NonNull BlockRegion orientation,
		final @NonNull Identifier modelId
	) {
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
	public @NonNull Identifier createCenterModel(final @NonNull Block block) {
		final ModelTemplate model = new ModelTemplate(Optional.of(Global.modId(PARENT)),
			Optional.empty(), TextureSlot.TEXTURE);
		final Material material = TextureMapping.getBlockTexture(block);
		return model.create(
			block,
			new TextureMapping().put(TextureSlot.TEXTURE, material),
			generator.modelOutput
		);
	}

	/**
	 * Create shaft+head type models.
	 */
	public @NonNull Identifier createShaftHeadModel(
		final @NonNull Block block,
		final @NonNull String suffix
	) {
		final ModelTemplate model = new ModelTemplate(Optional.of(Global.modId(PARENT + suffix)),
			Optional.empty(), TEXTUREKEY_SHAFT, TEXTUREKEY_HEAD);
		final Material shaftmaterial = TextureMapping.getBlockTexture(block, "_shaft");
		final Material headmaterial = TextureMapping.getBlockTexture(block, "_head");
		return model.createWithSuffix(
			block, suffix,
			new TextureMapping()
				.put(TEXTUREKEY_SHAFT, shaftmaterial)
				.put(TEXTUREKEY_HEAD, headmaterial),
			generator.modelOutput
		);
	}

	/**
	 * Create models and blockstates for 6 directions and 9 orientations.
	 */
	public @NonNull PropertyDispatch<MultiVariant> createFlat9Direction(final @NonNull Block block) {
		// Models.
		final Identifier center = createCenterModel(block);
		final Identifier topleft = createShaftHeadModel(block, "_top_left");
		final Identifier top = createShaftHeadModel(block, "_top");
		final Identifier topright = createShaftHeadModel(block, "_top_right");
		final Identifier right = createShaftHeadModel(block, "_right");
		// Block states.
		final var map = PropertyDispatch
			.initial(BlockStateProperties.FACING, ArrowMarkBlock.ORIENTATION);
		registerFlatNormal(map, BlockRegion.TOPLEFT, topleft);
		registerFlatNormal(map, BlockRegion.TOPCENTER, top);
		registerFlatNormal(map, BlockRegion.TOPRIGHT, topright);
		registerFlatFlipped(map, BlockRegion.MIDDLELEFT, right);
		registerFlatNormal(map, BlockRegion.MIDDLECENTER, center);
		registerFlatNormal(map, BlockRegion.MIDDLERIGHT, right);
		registerFlatFlipped(map, BlockRegion.BOTTOMLEFT, topright);
		registerFlatFlipped(map, BlockRegion.BOTTOMCENTER, top);
		registerFlatFlipped(map, BlockRegion.BOTTOMRIGHT, topleft);
		return map;
	}

	/**
	 * Generate models and blockstates for a thin,
	 * flat model that has 6 directions and 9 orientations.
	 */
	public void registerFlat9Direction(Block block) {
		generator.blockStateOutput.accept(MultiVariantGenerator
			.dispatch(block).with(createFlat9Direction(block)));
	}
}
