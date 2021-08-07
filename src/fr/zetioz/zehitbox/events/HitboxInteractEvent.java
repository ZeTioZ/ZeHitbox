package fr.zetioz.zehitbox.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.zetioz.zehitbox.objects.Hitbox;

public class HitboxInteractEvent extends PlayerEvent implements Cancellable
{
	private static final HandlerList HANDLERS_LIST = new HandlerList();
	private boolean isCancelled;
	
	private Action action;
	private ItemStack item;
	private Hitbox clickedHitbox;
	private EquipmentSlot hand;
	
	public HitboxInteractEvent(Player who, Action action, ItemStack item, Hitbox clickedHitbox)
	{
		super(who);
		this.setAction(action);
		this.item = item;
		this.clickedHitbox = clickedHitbox;
	}
	
	public HitboxInteractEvent(Player who, Action action, ItemStack item, Hitbox clickedHitbox, EquipmentSlot hand)
	{
		super(who);
		this.setAction(action);
		this.item = item;
		this.clickedHitbox = clickedHitbox;
		this.hand = hand;
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
        return HANDLERS_LIST;
    }
    
    public static HandlerList getHandlerList()
    {
        return HANDLERS_LIST;
    }

	public Action getAction()
	{
		return action;
	}

	public void setAction(Action action)
	{
		this.action = action;
	}

	public ItemStack getItem()
	{
		return item;
	}

	public void setItem(ItemStack item)
	{
		this.item = item;
	}

	public Hitbox getClickedHitbox()
	{
		return clickedHitbox;
	}

	public void setClickedHitbox(Hitbox clickedHitbox)
	{
		this.clickedHitbox = clickedHitbox;
	}

	public EquipmentSlot getHand()
	{
		return hand;
	}

	public void setHand(EquipmentSlot hand)
	{
		this.hand = hand;
	}
}
