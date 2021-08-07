package fr.zetioz.zehitbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.zetioz.zehitbox.listeners.PlayerInteractEventListener;
import fr.zetioz.zehitbox.objects.BlockHitbox;
import fr.zetioz.zehitbox.objects.Hitbox;
import fr.zetioz.zehitbox.schedulers.HitboxUpdater;
import fr.zetioz.zehitbox.utils.FilesManagerUtils;

public class ZeHitBoxMain extends JavaPlugin implements CommandExecutor
{
	private static ZeHitBoxMain instance;
	private FilesManagerUtils filesManager;
	private List<Hitbox> hitboxes;
	
	@Override
	public void onEnable()
	{
		instance = this;
		filesManager = new FilesManagerUtils(this);
		
		filesManager.createSimpleYaml("database");
		
		hitboxes = new ArrayList<>();
		new HitboxUpdater().runTaskTimer(this, 0L, 1L);
		
		getCommand("zehitbox").setExecutor(this);
		registerEvents(this, new PlayerInteractEventListener());
	}
	
	@Override
	public void onDisable()
	{
		instance = null;
	}
	
	public static ZeHitBoxMain getInstance()
	{
		return instance;
	}
	
	public FilesManagerUtils getFilesManager()
	{
		return filesManager;
	}
	
	private void registerEvents(ZeHitBoxMain plugin, Listener... listeners)
	{
		for(Listener listener : listeners)
		{
			Bukkit.getPluginManager().registerEvents(listener, plugin);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("zehitbox"))
		{
			Player player = (Player) sender;
			
			if(args.length == 1)
			{
				if(args[0].equals("spawn"))
				{
					Block testBlock = player.getWorld().getBlockAt(player.getLocation());
					testBlock.setType(Material.CHEST);
					PlayerInteractEventListener.hitboxes.add(new BlockHitbox(testBlock, testBlock.getLocation(), 3, 3, 3).spawn());
					Bukkit.getLogger().info("Hitbox spwaned...");
				}
				else if(args[0].equals("despawn"))
				{
					Iterator<Hitbox> hitboxIt = PlayerInteractEventListener.hitboxes.iterator();
					if(hitboxIt.hasNext())
					{
						Hitbox next = hitboxIt.next();
						next.despawn();
						PlayerInteractEventListener.hitboxes.remove(next);
						Bukkit.getLogger().info("Hitbox removed...");
					}
				}
				else if(args[0].equals("teleport"))
				{
					final Location location = player.getLocation();
					hitboxes.get(0).teleport(location);
				}
			}
		}
		return false;
	}
}
