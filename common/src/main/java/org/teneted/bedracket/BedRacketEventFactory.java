package org.teneted.bedracket;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.teneted.bedracket.event.block.BellResonateEvent;
import org.teneted.bedracket.event.block.CrafterCraftEvent;
import org.teneted.bedracket.event.block.NotePlayEvent;
import org.teneted.bedracket.event.block.VaultDisplayItemEvent;

import java.util.List;
import java.util.stream.Stream;

public class BedRacketEventFactory {

    public static CrafterCraftEvent callCrafterCraftEvent(BlockPos pos, Level world, ItemStack result, RecipeHolder<CraftingRecipe> holder) {
        CrafterCraftEvent event = new CrafterCraftEvent(world.getBlockState(pos).getBlock(), holder.value(), result);
        BedRacket.EVENT_BUS.callEvent(event);
        return event;
    }

    public static NotePlayEvent callNotePlayEvent(Level world, BlockPos pos, NoteBlockInstrument instrument, int note) {
        NotePlayEvent event = new NotePlayEvent(world.getBlockState(pos).getBlock(), instrument, note);
        BedRacket.EVENT_BUS.callEvent(event);
        return event;
    }

    public static VaultDisplayItemEvent callVaultDisplayItemEvent(ServerLevel level, BlockPos pos, ItemStack displayitemStack) {
        VaultDisplayItemEvent event = new VaultDisplayItemEvent(level.getBlockState(pos).getBlock(), displayitemStack);
        BedRacket.EVENT_BUS.callEvent(event);
        return event;
    }

    public static Stream<LivingEntity> handleBellResonateEvent(Level world, BlockPos position, List<LivingEntity> livingEntities) {
        Block block = world.getBlockState(position).getBlock();
        BellResonateEvent event = new BellResonateEvent(block, livingEntities);
        BedRacket.EVENT_BUS.callEvent(event);
        return event.getResonatedEntities().stream();
    }
}
