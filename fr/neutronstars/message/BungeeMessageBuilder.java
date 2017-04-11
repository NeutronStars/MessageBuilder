package fr.neutronstars.message;

import java.util.Collection;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public final class BungeeMessageBuilder extends MessageBuilder<BungeeMessageBuilder>{

	public BungeeMessageBuilder(MessageBuilder<?> messageBuilder) {
		super(messageBuilder);
	}
	
	public BungeeMessageBuilder(String text) {
		super(text);
	}

	/**
	 * Send to player
	 * @param player
	 */
	public void send(ProxiedPlayer proxiedPlayer){
		proxiedPlayer.sendMessage(build());
	}
	
	/**
	 * Send to a player List.
	 * @param players
	 */
	public void send(Collection<? extends ProxiedPlayer> proxiedPlayers){
		BaseComponent[] bc = build();
		for(ProxiedPlayer proxiedPlayer : proxiedPlayers) proxiedPlayer.sendMessage(bc);
	}
	
	/**
	 * Send to a player List.
	 * @param players
	 */
	public void send(ProxiedPlayer... proxiedPlayers){
		BaseComponent[] bc = build();
		for(ProxiedPlayer proxiedPlayer : proxiedPlayers) proxiedPlayer.sendMessage(bc);
	}
	
	/**
	 * Send to all players online.
	 * @param players
	 */
	public void sendAll(){
		send(BungeeCord.getInstance().getPlayers());
	}
	
	/**
	 * Create a new instance of the class.
	 * @return this
	 */
	public BungeeMessageBuilder clone() {
		return new BungeeMessageBuilder(this);
	}
}
