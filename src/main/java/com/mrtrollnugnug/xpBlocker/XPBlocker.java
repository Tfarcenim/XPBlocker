package com.mrtrollnugnug.xpBlocker;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

@Mod(XPBlocker.MODID)
public class XPBlocker {
  public static final String MODID = "xpblocker";

  public XPBlocker() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
    ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_SPEC);
  }

  private void preInit(FMLCommonSetupEvent event) {
    MinecraftForge.EVENT_BUS.register(new EventManager());
  }

  public static final ServerConfig SERVER;
  public static final ForgeConfigSpec SERVER_SPEC;

  static {
    final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
    SERVER_SPEC = specPair.getRight();
    SERVER = specPair.getLeft();
  }

  public static class ServerConfig {

    public static ForgeConfigSpec.BooleanValue smelt_xp;

    ServerConfig(ForgeConfigSpec.Builder builder) {

      builder.push("general");
      smelt_xp = builder
              .comment("Toggles whether you get XP from smelting.")
              .define("smelt_xp", false);
      builder.pop();
    }
  }
}
