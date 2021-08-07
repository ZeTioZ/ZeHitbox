package fr.zetioz.zehitbox.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.zetioz.zehitbox.objects.Hitbox;

public class HitboxEvent extends Event
{
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    
    private Hitbox which;

    public HitboxEvent(Hitbox which)
    {
    	this.which = which;
    }
    
	@Override
	public HandlerList getHandlers()
	{
		return HANDLERS_LIST;
	}
	
    public static HandlerList getHandlerList()
    {
        return HANDLERS_LIST;
    }

	public Hitbox getHitbox()
    {
        return which;
    }
    
    public void setHitbox(Hitbox which)
    {
    	this.which = which;
    }
}
