package yadokaris_How_to_Play_Annihilation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChatEvent {

	private final Set<String> JOBS = new HashSet<String>(Arrays.asList("Acrobat", "Alchemist", "Archer", "Assassin",
			"Bard", "Berserker", "BloodMage", "Builder", "Civilian", "Dasher", "Defender", "Enchanter", "Engineer",
			"Farmer", "Handyman", "Healer", "Hunter", "IceMan", "Immobilizer", "Lumberjack", "Mercenary", "Miner",
			"Ninja", "Pyro", "RiftWalker", "RobinHood", "Scorpio", "Scout", "Sniper", "Spider", "Spy", "Succubus",
			"Swapper", "Thor", "Tinkerer", "Transporter", "Vampire", "Warrior", "Wizard"));
	static final Map<String, Integer> TEAMS = new HashMap<String, Integer>() {
		{
			put("Red", 0xFF0000);
			put("Green", 0x00FF00);
			put("Blue", 0x03B6FC);
			put("Yellow", 0xFFFF00);
		}
	};
	private final List<String> RANKS = new ArrayList<String>(Arrays.asList("Annihilator", "GrandMaster-III",
			"GrandMaster-II", "GrandMaster-I", "Master-III", "Master-II", "Master-I", "Gold-III", "Gold-II", "Gold-I",
			"Silver-III", "Silver-II", "Silver-I", "Novice-III", "Novice-II", "Novice-I"));
	private static boolean isJoin, isAnnounced;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent event) {
		if (Minecraft.getMinecraft().player != null) {
			EntityPlayer player = Minecraft.getMinecraft().player;
			H2PA.player = player;
			H2PA.playerName = H2PA.player.getName();

			new Thread(() -> {
				String update = null;
				try {
					update = IOUtils.toString(new URL("https://raw.githubusercontent.com/yadokari1130/How-to-Play-Annihilation/master/update.json"), "UTF-8");
				}
				catch (IOException e) {
					e.printStackTrace();
				}

				Gson gson = new Gson();
				Map map = gson.fromJson(update, Map.class);

				String latest = ((Map<String, String>) map.get("promos")).get("1.12.2-latest");

				if (!isAnnounced && !latest.equals(H2PA.version)) {
					new Thread(() -> {
						try {
							Thread.sleep(5000);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
						ClickEvent linkClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, (String)map.get("homepage"));
						Style clickableStyle = new Style().setClickEvent(linkClickEvent).setColor(TextFormatting.AQUA);
						Style color = new Style().setColor(TextFormatting.GREEN);
						player.sendMessage(new TextComponentTranslation("yadokaris_h2pa.update.message1").setStyle(color));
						player.sendMessage(new TextComponentTranslation("yadokaris_h2pa.update.message2").setStyle(clickableStyle));
						player.sendMessage(new TextComponentTranslation("yadokaris_h2pa.update.infomation"));
						player.sendMessage(new TextComponentString("----------------------------------------------------------------------"));
						player.sendMessage(new TextComponentString(((Map<String, String>) map.get("1.12.2")).get(latest)));
						player.sendMessage(new TextComponentString("----------------------------------------------------------------------"));
					}).start();
					isAnnounced = true;
				}
			}).start();
		}
	}
}
