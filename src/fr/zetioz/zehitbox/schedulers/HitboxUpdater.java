package fr.zetioz.zehitbox.schedulers;

import java.util.HashSet;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import fr.zetioz.zehitbox.listeners.PlayerInteractEventListener;
import fr.zetioz.zehitbox.objects.BlockHitbox;
import fr.zetioz.zehitbox.objects.EntityHitbox;
import fr.zetioz.zehitbox.objects.Hitbox;

public class HitboxUpdater extends BukkitRunnable
{
	@Override
	public void run()
	{
		@SuppressWarnings("unchecked")
		HashSet<Hitbox> clone = (HashSet<Hitbox>) PlayerInteractEventListener.hitboxes.clone();
		for(Iterator<Hitbox> hitboxIt = clone.iterator(); hitboxIt.hasNext();)
		{
			Hitbox hitboxToUpdate = hitboxIt.next();
			if(hitboxToUpdate instanceof EntityHitbox)
			{
				if(!((EntityHitbox) hitboxToUpdate).getAttachedEntity().isDead())
				{
					hitboxToUpdate.teleport(((EntityHitbox) hitboxToUpdate).getAttachedEntity().getLocation()).update();
				}
				else
				{
					Bukkit.getLogger().info("Entity dead");
					hitboxToUpdate.despawn();
					PlayerInteractEventListener.hitboxes.remove(hitboxToUpdate);
				}
			}
			else if(hitboxToUpdate instanceof BlockHitbox)
			{
				if(((BlockHitbox) hitboxToUpdate).getAttachedBlock().getType() == Material.AIR)
				{
					hitboxToUpdate.despawn();
					PlayerInteractEventListener.hitboxes.remove(hitboxToUpdate);
				}
			}
		}
	}
}
