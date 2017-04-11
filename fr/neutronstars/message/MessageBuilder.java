package fr.neutronstars.message;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
* @author NeutronStars
*/

public final class MessageBuilder {

	private final List<BaseComponent> baseComponents = Lists.newArrayList();
	private TextComponent textComponent;
	
	public MessageBuilder(MessageBuilder messageBuilder){
		this.baseComponents.addAll(messageBuilder.baseComponents);
		this.textComponent = messageBuilder.textComponent;
	}
	
	public MessageBuilder(String text){
		textComponent = new TextComponent(text);
	}
	
	/**
	 * Add text after the last text.
	 * @param text
	 * @return the class
	 */
	public MessageBuilder next(String text){
		baseComponents.add(textComponent);
		textComponent = new TextComponent(text);
		return this;
	}
	
	/**
	 * Add text in new line after the last text.
	 * @param text
	 * @return the class.
	 */
	public MessageBuilder nextln(String text){
		baseComponents.add(textComponent);
		textComponent = new TextComponent("\n"+text);
		return this;
	}
	
	/**
	 * Add a event click to the text. (URL, command, suggest command, file and change page in the book.)
	 * @param action
	 * @param value
	 * @return the class.
	 */
	public MessageBuilder click(Action action, String value){
		textComponent.setClickEvent(new ClickEvent(action, value));
		return this;
	}
	
	/**
	 * Add a event click to the text. (URL, command, suggest command, file and change page in the book.)
	 * @param index
	 * @return the class.
	 */
	public MessageBuilder click(int index){
		if(baseComponents.size() > index && index > -1)
		textComponent.setClickEvent(baseComponents.get(index).getClickEvent());
		return this;
	}
	
	/**
	 * Add a text above the text.
	 * @param text
	 * @return the class.
	 */
	public MessageBuilder setHover(String text){
		textComponent.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(text)}));
		return this;
	}
	
	/**
	 * Add a text above the text.
	 * @param text
	 * @return the class.
	 */
	public MessageBuilder setHover(TextComponent text){
		textComponent.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{text}));
		return this;
	}
	
	/**
	 * Add a text above the text.
	 * @param index
	 * @return the class.
	 */
	public MessageBuilder setHover(int index){
		if(baseComponents.size() > index && index > -1)
			textComponent.setHoverEvent(baseComponents.get(index).getHoverEvent());
		return this;
	}
	
	/**
	 * Built to prepare to send it to the player.
	 * @return BaseComponent[]
	 */
	public BaseComponent[] build(){
		BaseComponent[] result = (BaseComponent[])this.baseComponents.toArray(new BaseComponent[this.baseComponents.size() + 1]);
		result[this.baseComponents.size()] = this.textComponent;
		return result;
	}
	
	/**
	 * Send to player
	 * @param player
	 */
	public void send(Player player){
		player.spigot().sendMessage(build());
	}
	
	/**
	 * Send to a player List.
	 * @param players
	 */
	public void send(Collection<? extends Player> players){
		BaseComponent[] bc = build();
		players.forEach(player->player.spigot().sendMessage(bc));
	}
	
	/**
	 * Send to a player List.
	 * @param players
	 */
	public void send(Player... players){
		BaseComponent[] bc = build();
		for(Player player : players) player.spigot().sendMessage(bc);
	}
	
	/**
	 * Send to all players online.
	 * @param players
	 */
	public void sendAll(){
		send(Bukkit.getOnlinePlayers());
	}
	
	/**
	 * Create a new instance of the class.
	 * @return this
	 */
	public MessageBuilder clone(){
		return new MessageBuilder(this);
	}
}
