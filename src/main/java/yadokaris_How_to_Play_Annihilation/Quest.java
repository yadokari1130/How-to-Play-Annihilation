package yadokaris_How_to_Play_Annihilation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

public class Quest {

	protected String name;
	public boolean doClear = false;
	protected String sentence;
	protected String describe;
	protected int currentValue, value;
	protected QuestGroup parent;

	public String getName() {
		return name;
	}

	public String getSentence() {
		return sentence;
	}

	public String getDescribe() {
		return describe;
	}

	public void clear() {
		doClear = true;
		H2PA.saveXML(name, true);

		if (parent.doClear()) {
			SoundHandler handler = Minecraft.getMinecraft().getSoundHandler();
			handler.playSound(PositionedSoundRecord.getRecord(new SoundEvent(new ResourceLocation("minecraft", "entity.player.levelup")), 0.5F, 0.3F));
			Style color = new Style().setColor(TextFormatting.GREEN);
			ClickEvent linkClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, H2PA.getURL(parent.getName()));
			Style clickableStyle = new Style().setClickEvent(linkClickEvent).setColor(TextFormatting.AQUA);
			H2PA.player.sendMessage(new TextComponentString("クエストクリア： " + parent.getName()).setStyle(color));
			H2PA.player.sendMessage(new TextComponentString("クリックしてツイート").setStyle(clickableStyle));
			H2PA.currentGroup = null;
			parent.unregisterEvent();
		}
	}

	public String getDisplayDescribe() {
		StringBuilder sb = new StringBuilder();
		sb.append(describe);
		sb.append(" ");
		sb.append(currentValue);
		sb.append("/");
		sb.append(value);

		return sb.toString();
	}

	public int getColor() {
		return doClear ? 0x00FF00 : 0xFF0000;
	}
}
