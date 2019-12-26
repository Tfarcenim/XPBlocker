package com.mrtrollnugnug.xpBlocker;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventManager {

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event){
		if (event.getEntity() instanceof ExperienceOrbEntity){
			if (!event.getEntity().getPersistentData().contains("XPBLOCKER_protected")){
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerUseBlock(PlayerInteractEvent.RightClickBlock event){
			if (event.getItemStack().getItem() == Items.EXPERIENCE_BOTTLE){
				event.setCanceled(true);
			}
	}
	
	@SubscribeEvent
	public void onPlayerUse(PlayerInteractEvent.RightClickItem event){
			if (event.getItemStack().getItem() == Items.EXPERIENCE_BOTTLE){
				event.setCanceled(true);
			}
	}
	
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event){
		if (event.getExpToDrop() > 0){
			event.setExpToDrop(0);
		}
	}
	
	@SubscribeEvent
	public void onSmelt(PlayerEvent.ItemSmeltedEvent event){
			int xp = (int) (event.getSmelting().getItem().getSmeltingExperience(event.getSmelting())*event.getSmelting().getMaxStackSize());
			if (XPBlocker.ServerConfig.smelt_xp.get())event.getPlayer().giveExperiencePoints(xp);
	}
}
