package net.Indyuce.bountyhunters.api.event;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import net.Indyuce.bountyhunters.api.language.Message;

public class HunterTargetEvent extends Event implements Cancellable {
	private final Player player;
	private final OfflinePlayer target;

	private boolean cancelled = false;

	private static final HandlerList handlers = new HandlerList();

	/*
	 * this event is called when a bounty reward changes, for instance when a
	 * player performs /addbounty <player> <reward> when there's already a
	 * bounty on the player, or when the auto bounty system adds a specific
	 * amount to a player'sbounty.
	 */
	public HunterTargetEvent(Player player, OfflinePlayer target) {
		this.player = player;
		this.target = target;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean bool) {
		cancelled = bool;
	}

	public Player getPlayer() {
		return player;
	}

	public OfflinePlayer getTarget() {
		return target;
	}

	public void sendAllert(Player target) {
		Message.NEW_HUNTER_ALERT.format("hunter", player.getName()).send(target);
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
