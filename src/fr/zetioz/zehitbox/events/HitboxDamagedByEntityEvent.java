package fr.zetioz.zehitbox.events;

import org.bukkit.entity.Entity;

import fr.zetioz.zehitbox.objects.Hitbox;

public class HitboxDamagedByEntityEvent extends HitboxDamageEvent
{
	private Entity damager;
	
	public HitboxDamagedByEntityEvent(Hitbox which, Entity damager, double damages, double finalDamages)
	{
		super(which, damages, finalDamages);
		this.damager = damager;
	}

	public Entity getDamager()
	{
		return damager;
	}
}
