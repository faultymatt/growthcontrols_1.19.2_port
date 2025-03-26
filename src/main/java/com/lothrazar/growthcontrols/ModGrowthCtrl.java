package com.lothrazar.growthcontrols;

import com.lothrazar.growthcontrols.config.ConfigHandler;
import com.lothrazar.growthcontrols.item.ItemGrow;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModGrowthCtrl.MODID)
public class ModGrowthCtrl {

  public static final String MODID = "growthcontrols";
  public static ConfigHandler CONFIG;
  public static final Logger LOGGER = LogManager.getLogger();

  public ModGrowthCtrl() {
    MinecraftForge.EVENT_BUS.register(this);
    MinecraftForge.EVENT_BUS.register(new GrowEvents());
    CONFIG = new ConfigHandler(ConfigHandler.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + ".toml"));
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    ItemGrow.register(eventBus);
    MinecraftForge.EVENT_BUS.register(new CropTooltipHandler());
  }
}
