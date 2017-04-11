package fr.neutronstars.message;

import java.util.List;

import com.google.common.collect.Lists;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
* @author NeutronStars
*/

public class MessageBuilder {

	private final List<BaseComponent> baseComponents = Lists.newArrayList();
	private TextComponent textComponent;
	
	public MessageBuilder(MessageBuilder messageBuilder){
		this.baseComponents.addAll(messageBuilder.baseComponents);
		this.textComponent = (TextComponent) messageBuilder.textComponent.duplicate();
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
		return this.next("\n"+text);
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
		return this.setHover(new TextComponent(text));
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
		BaseComponent[] result = this.baseComponents.toArray(new BaseComponent[this.baseComponents.size() + 1]);
		result[this.baseComponents.size()] = this.textComponent;
		return result;
	}
	
	/**
	 * Transform to BukkitMessageBuilder
	 * return {@link BukkitMessageBuilder}
	 */
	public BukkitMessageBuilder toBukkitMessageBuilder(){
		return new BukkitMessageBuilder(this);
	}
	
	/**
	 * Transform to BungeeMessageBuilder
	 * return {@link BungeeMessageBuilder}
	 */
	public BungeeMessageBuilder toBungeeMessageBuilder(){
		return new BungeeMessageBuilder(this);
	}
	
	/**
	 * Create a new instance of the class.
	 * @return this
	 */
	public MessageBuilder clone(){
		return new MessageBuilder(this);
	}
}
