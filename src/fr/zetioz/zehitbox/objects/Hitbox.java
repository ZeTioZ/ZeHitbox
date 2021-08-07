package fr.zetioz.zehitbox.objects;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import fr.zetioz.zehitbox.listeners.PlayerInteractEventListener;

public class Hitbox
{
	private Location location;
	private int lenght;
	private int height;
	private int width;
	private BoundingBox boundingBox;
	
	public Hitbox(final Location location, final int lenght, final int height, final int width)
	{	
		this.location = location;
		this.lenght = lenght;
		this.height = height;
		this.width = width;
	}
	
	/**
	 * Gets the middle of the hitbox
	 * 
	 * @return the middle location of the hitbox
	*/
	public Location getLocation() {
		return location;
	}

    /**
     * Sets the middle location of the hitbox
     *
     * @param location : Location of the middle of the hitbox
     */
	public Hitbox setLocation(final Location location)
	{
		this.location = location;
		return this;
	}

	public int getLenght()
	{
		return lenght;
	}

	public Hitbox setLenght(final int lenght) 
	{
		this.lenght = lenght;
		return this;
	}

	public int getHeight()
	{
		return height;
	}
	
	public Hitbox setHeight(final int height)
	{
		this.height = height;
		return this;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public Hitbox setWidth(final int width)
	{
		this.width = width;
		return this;
	}
	
	public BoundingBox getBoundingBox()
	{
		return boundingBox;
	}
	
	
	public void setBoundingBox(BoundingBox boundingBox)
	{
		this.boundingBox = boundingBox;
	}

	public Hitbox spawn()
	{
		boundingBox = BoundingBox.of(getLocation(), width/2D, height/2D, lenght/2D);
		PlayerInteractEventListener.hitboxes.add(this);
		return this;
	}
	
	public Hitbox despawn()
	{
		boundingBox.resize(0, 0, 0, 0, 0, 0);
		PlayerInteractEventListener.hitboxes.remove(this);
		return this;
	}
	
	public Hitbox update()
	{
		despawn();
		spawn();
		return this;
	}
	
	public Hitbox teleport(final Location location)
	{
		//Vector translationVector = this.location.toVector().subtract(location.toVector());
		setLocation(location);
		
		//boundingBox.shift(new Vector().zero().add(translationVector.normalize()));
		return this;
	}
}
