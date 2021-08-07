package fr.zetioz.zehitbox.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import fr.zetioz.zehitbox.objects.Hitbox;

public class HitboxDamageEvent extends HitboxEvent implements Cancellable
{
	private static final HandlerList HANDLERS = new HandlerList();
	private boolean isCancelled;
	
    private double damages;
    private double finalDamages;
    
    public HitboxDamageEvent(Hitbox which, double damages, double finalDamages)
    {
    	super(which);
    	this.damages = damages;
    	this.finalDamages = finalDamages;
    }
	
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancel)
	{
		isCancelled = cancel;
	}

    @Override
    public HandlerList getHandlers()
    {
        return HANDLERS;
    }

    public static HandlerList getHandlerList()
    {
        return HANDLERS;
    }
    
    public double getDamages()
    {
    	return damages;
    }
    
    public void setDamages(int damages)
    {
    	this.damages = damages;
    }
    
	public double getFinalDamage() 
	{
		return finalDamages;
	}

	public void setTotalDamages(int totalDamages)
	{
		this.finalDamages = totalDamages;
	}
}
