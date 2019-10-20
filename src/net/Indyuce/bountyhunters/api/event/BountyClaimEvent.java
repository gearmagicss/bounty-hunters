package net.Indyuce.bountyhunters.api.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import net.Indyuce.bountyhunters.BountyHunters;
import net.Indyuce.bountyhunters.api.Bounty;
import net.Indyuce.bountyhunters.api.NumberFormat;
import net.Indyuce.bountyhunters.api.language.Message;
import net.Indyuce.bountyhunters.api.player.PlayerData;

public class BountyClaimEvent extends BountyEvent {
	private final Player player;

	private static final HandlerList handlers = new HandlerList();

	/*
	 * this event is called whenever a player claims a bounty by killing the
	 * bounty target
	 */
	public BountyClaimEvent(Bounty bounty, Player player) {
		super(bounty);

		this.player = player;
	}

	public Player getClaimer() {
		return player;
	}

	public void sendAllert() {

		// message to player
		String reward = new NumberFormat().format(getBounty().getReward());
		Message.BOUNTY_CLAIMED_BY_YOU.format("target", getBounty().getTarget().getName(), "reward", reward).send(player);

		// message to server
		PlayerData playerData = BountyHunters.getInstance().getPlayerDataManager().get(player);
		String title = playerData.hasTitle() ? ChatColor.LIGHT_PURPLE + "[" + playerData.getTitle().format() + ChatColor.LIGHT_PURPLE + "] " : "";
		for (Player online : Bukkit.getOnlinePlayers())
			if (online != player)
				Message.BOUNTY_CLAIMED.format("reward", new NumberFormat().format(getBounty().getReward()), "killer", title + player.getName(), "target", getBounty().getTarget().getName())
						.send(online);
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
