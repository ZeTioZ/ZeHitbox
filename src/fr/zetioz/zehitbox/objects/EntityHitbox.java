package fr.zetioz.zehitbox.objects;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EntityHitbox extends Hitbox
{
	private Entity attachedEntity;
	
	public EntityHitbox(Entity attachedEntity, Location location, int lenght, int height, int width)
	{
		super(location, lenght, height, width);
		this.attachedEntity = attachedEntity;
	}

	public Entity getAttachedEntity()
	{
		return attachedEntity;
	}

	public void setAttachedBlock(Entity attachedEntity)
	{
		this.attachedEntity = attachedEntity;
	}

}
