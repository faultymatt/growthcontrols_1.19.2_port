package com.lothrazar.growthcontrols;

import com.lothrazar.growthcontrols.config.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = "growthcontrols", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CropTooltipHandler {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        if (!Screen.hasShiftDown() && !Minecraft.getInstance().options.advancedItemTooltips) {
            return;
        }

        if (stack.getItem() instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();

            if (!ConfigHandler.INSTANCE.isConfiguredBlock(block)) {
                return;
            }

            List<String> biomes = ConfigHandler.INSTANCE.getBiomesCombinedAllowNull(block);

            if (biomes != null && !biomes.isEmpty()) {
                String biomeList = String.join(", ", biomes);
                event.getToolTip().add(Component.literal("§8Grows in: " + biomeList));
            }
        }
    }
}