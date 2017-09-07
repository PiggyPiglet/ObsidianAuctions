package me.virustotal.floauction.listeners;

import me.virustotal.floauction.utility.CArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.flobi.floAuction.AuctionConfig;
import com.flobi.floAuction.AuctionParticipant;
import com.flobi.floAuction.AuctionScope;
import com.flobi.floAuction.FloAuction;
import com.garbagemule.MobArena.events.ArenaPlayerJoinEvent;

public class MobArenaListener implements Listener {
	
	@EventHandler
	public void onMAPlayerJoin(ArenaPlayerJoinEvent event) 
	{
		if (event.isCancelled()) 
		{
			return;
		}
		Player player = event.getPlayer();
		if (player == null)
		{
			return;
		}
		String playerName = player.getName();
		if (!AuctionConfig.getBoolean("allow-arenas", AuctionScope.getPlayerScope(player)) && AuctionParticipant.isParticipating(playerName)) 
		{
			FloAuction.getMessageManager().sendPlayerMessage(new CArrayList<String>(new String[] {"arena-warning"}), playerName, (AuctionScope) null);
			event.setCancelled(true);
		}
	}
}