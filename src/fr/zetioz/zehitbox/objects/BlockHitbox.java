package fr.zetioz.zehitbox.objects;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockHitbox extends Hitbox
{
	private Block attachedBlock;
	
	public BlockHitbox(Block attachedBlock, Location location, int lenght, int height, int width)
	{
		super(location, lenght, height, width);
		this.attachedBlock = attachedBlock;
	}

	public Block getAttachedBlock()
	{
		return attachedBlock;
	}

	public void setAttachedBlock(Block attachedBlock)
	{
		this.attachedBlock = attachedBlock;
	}

}
