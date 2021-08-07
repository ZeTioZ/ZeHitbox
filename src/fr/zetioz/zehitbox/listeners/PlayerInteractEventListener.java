package fr.zetioz.zehitbox.listeners;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Container;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.zetioz.zehitbox.events.HitboxDamagedByEntityEvent;
import fr.zetioz.zehitbox.events.HitboxInteractEvent;
import fr.zetioz.zehitbox.objects.BlockHitbox;
import fr.zetioz.zehitbox.objects.EntityHitbox;
import fr.zetioz.zehitbox.objects.Hitbox;

public class PlayerInteractEventListener implements Listener
{
	public static HashSet<Hitbox> hitboxes = new HashSet<>();
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e)
	{
		final Player player = e.getPlayer();
		final Location eyesLocation = player.getEyeLocation();
		final Action action = e.getAction();
		final EquipmentSlot equipmentSlot = e.getHand();
		final ItemStack item = e.getItem();
		for(Hitbox hitboxToCheck : hitboxes)
		{
			if(hitboxToCheck.getLocation().getWorld() == eyesLocation.getWorld()
				&& hitboxToCheck.getBoundingBox().rayTrace(eyesLocation.toVector(), eyesLocation.getDirection(), 5D) != null)
			{
				if(action == Action.LEFT_CLICK_AIR
					|| action == Action.LEFT_CLICK_BLOCK)
				{
					if(hitboxToCheck instanceof EntityHitbox)
					{
						EntityHitbox entityHitboxToCheck = (EntityHitbox) hitboxToCheck;
						int damages = item == null ? 1 : item.getType().name().endsWith("SWORD") ? 7 : 1;
						
						HitboxDamagedByEntityEvent hitboxDamagedByEntityEvent = new HitboxDamagedByEntityEvent(hitboxToCheck, player, damages, damages);
						Bukkit.getPluginManager().callEvent(hitboxDamagedByEntityEvent);
						if(!hitboxDamagedByEntityEvent.isCancelled()
							&& entityHitboxToCheck.getAttachedEntity() instanceof LivingEntity)
						{
							((LivingEntity) entityHitboxToCheck.getAttachedEntity()).damage(damages);
						}
					}
					else if(hitboxToCheck instanceof BlockHitbox)
					{
//						BlockHitbox blockHitboxToCheck = (BlockHitbox) hitboxToCheck;
						
						HitboxInteractEvent hitboxInteractEvent = new HitboxInteractEvent(player, action, item, hitboxToCheck, equipmentSlot);
						Bukkit.getPluginManager().callEvent(hitboxInteractEvent);
//						if(!hitboxInteractEvent.isCancelled()
//							&& blockHitboxToCheck.getAttachedBlock().getState() instanceof Container)
//						{
//							player.openInventory(((Container) blockHitboxToCheck.getAttachedBlock().getState()).getInventory());
//						}
					}
				}
				else if(action == Action.RIGHT_CLICK_AIR
						|| action == Action.RIGHT_CLICK_BLOCK)
				{
					HitboxInteractEvent hitboxInteractEvent = new HitboxInteractEvent(player, action, item, hitboxToCheck, equipmentSlot);
					Bukkit.getPluginManager().callEvent(hitboxInteractEvent);
					if(hitboxToCheck instanceof BlockHitbox)
					{
						BlockHitbox blockHitboxToCheck = (BlockHitbox) hitboxToCheck;
						if(!hitboxInteractEvent.isCancelled()
							&& blockHitboxToCheck.getAttachedBlock().getState() instanceof Container)
						{
							player.openInventory(((Container) blockHitboxToCheck.getAttachedBlock().getState()).getInventory());
						}
					}
				}
			}
		}
	}
}
