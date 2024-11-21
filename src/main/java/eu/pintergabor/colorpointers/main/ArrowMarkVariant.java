package eu.pintergabor.colorpointers.main;

import eu.pintergabor.colorpointers.Global;
import eu.pintergabor.colorpointers.blocks.ArrowMarkBlock;
import eu.pintergabor.colorpointers.items.ArrowMarkItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import static eu.pintergabor.colorpointers.Global.arrowMarkBlockLumi;

/**
 * One ArrowMark variant
 */
public class ArrowMarkVariant {

    /**
     * ArrowMark block
     * <p>
     * Read only outside class.
     */
    public ArrowMarkBlock block;

    /**
     * ArrowMark item
     * <p>
     * Read only outside class.
     */
    public ArrowMarkItem item;

    /**
     * Create one variant of ArrowMark
     */
    public ArrowMarkVariant(String name) {
        // Block
        block = (ArrowMarkBlock) Blocks.register(
                RegistryKey.of(RegistryKeys.BLOCK, Global.ModIdentifier(name)),
                ArrowMarkBlock::new,
                Block.Settings
                        .create()
                        .replaceable()
                        .noCollision()
                        .nonOpaque()
                        .sounds(BlockSoundGroup.MOSS_CARPET)
                        .luminance(value -> arrowMarkBlockLumi)
                        .postProcess(ArrowMarkVariant::always)
                        .emissiveLighting(ArrowMarkVariant::always)
                        .pistonBehavior(PistonBehavior.DESTROY)
        );
        // Item
        item = (ArrowMarkItem) Items.register(
                block,
                ArrowMarkItem::new);
        // Item groups
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(
                entries -> entries.add(item));
    }

    private static boolean always(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return true;
    }
}
