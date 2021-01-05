package yadokaris_How_to_Play_Annihilation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import yadokaris_How_to_Play_Annihilation.questgroups.BrewInvisiblePotionQuestGroup;
import yadokaris_How_to_Play_Annihilation.questgroups.BuyInvisiblePotionMaterialsQuestGroup;
import yadokaris_How_to_Play_Annihilation.questgroups.EnchantQuestGroup;
import yadokaris_How_to_Play_Annihilation.questgroups.GetInfinateResourcesQuestGroup;
import yadokaris_How_to_Play_Annihilation.questgroups.GetIronsQuestGroup;
import yadokaris_How_to_Play_Annihilation.questgroups.PrepareBowQuestGroup;
import yadokaris_How_to_Play_Annihilation.questgroups.PrepareInvisibleAttackQuestGroup;
import yadokaris_How_to_Play_Annihilation.questgroups.PrepareToolsQuestGroup;

@Mod(modid = "yadokaris_how_to_play_annihilation", name = "yadokari's How to Play Annihilation", version = "β1.0", updateJSON = "https://raw.githubusercontent.com/yadokari1130/How-to-Play-Annihilation/master/update.json")
public class H2PA {

	static String playerName;
	public static EntityPlayer player;
	private static Field overlayMessageField = null;
	static final String version = "β1.0";
	static String path;
	public static String job;
	public static QuestGroup currentGroup;
	public static Map<String, Boolean> status = new HashMap<>();
	public static List<QuestGroup> questGroups = new ArrayList<>();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		path = event.getSuggestedConfigurationFile().getParent() + "\\H2PATutorial.xml";

		Document doc = null;
		DocumentBuilder builder = null;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			try {
				doc = builder.parse(new File(path));
			}
			catch (FileNotFoundException e) {
				doc = builder.newDocument();
				e.printStackTrace();
			}
			catch (IOException | SAXException e) {
				e.printStackTrace();
			}
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Element root = doc.getDocumentElement();
		if (root != null) {
			NodeList rootList = root.getChildNodes();

			for (int i = 0; i < rootList.getLength(); i++) {
				NodeList childList = rootList.item(i).getChildNodes();
				String name = childList.item(0).getTextContent();
				boolean isClear = Boolean.parseBoolean(childList.item(1).getTextContent());

				status.put(name, isClear);
			}
		}

		//overlayMessageField = ReflectionHelper.findField(GuiIngame.class, "overlayMessage");
		overlayMessageField = ReflectionHelper.findField(GuiIngame.class, "field_73838_g");
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		ClientRegistry.registerKeyBinding(DevicePressEvent.questKey);
		MinecraftForge.EVENT_BUS.register(new DevicePressEvent());
		MinecraftForge.EVENT_BUS.register(new Rendering());
		MinecraftForge.EVENT_BUS.register(new ChatEvent());
		MinecraftForge.EVENT_BUS.register(this);

		registerQuestGroup(GetInfinateResourcesQuestGroup.getInstance());
		registerQuestGroup(GetIronsQuestGroup.getInstance());
		registerQuestGroup(PrepareToolsQuestGroup.getInstance());
		registerQuestGroup(PrepareBowQuestGroup.getInstance());
		registerQuestGroup(EnchantQuestGroup.getInstance());
		registerQuestGroup(PrepareInvisibleAttackQuestGroup.getInstance());
		registerQuestGroup(BuyInvisiblePotionMaterialsQuestGroup.getInstance());
		registerQuestGroup(BrewInvisiblePotionQuestGroup.getInstance());
	}

	/*@SubscribeEvent
	public void onDamaged(PlaySoundAtEntityEvent event) {
		System.out.println(event.getSound().getSoundName().toString());
	}

	/*@SubscribeEvent
	public void onTick(PlayerTickEvent e) {
		if (player == null) return;

		player.getHeldItemMainhand().getItem().getToolClasses(null).forEach(System.out::println);;
	}*/

	public static String getActionbar() {
		try {
			return overlayMessageField.get(Minecraft.getMinecraft().ingameGUI).toString();
		}
		catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Get Message Overlay faild");
		}
	}

	public static int chechAmountOfItems(ItemStack item) {
		if (player == null) throw new NullPointerException();
		int count = 0;
		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (itemstack.isItemEqual(item)) count += itemstack.getCount();
		}

		return count;
	}

	public static boolean checkHasItems(ItemStack item, int amount) {
		return chechAmountOfItems(item) >= amount;
	}

	public static int checkAmountOfPotionItems(ItemStack item) {
		if (player == null) throw new NullPointerException();
		int count = 0;
		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack, item)) count += itemstack.getCount();
		}

		return count;
	}

	public static boolean checkHasPotionItems(ItemStack item, int amount) {
		return checkAmountOfPotionItems(item) >= amount;
	}

	public static boolean checkHasEnchantedItem(ItemStack item) {
		if (player == null) throw new NullPointerException();
		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);
			if (itemstack.isItemEnchanted() && (item == null || itemstack.isItemEqual(item))) return true;
		}

		return false;
	}

	public static void saveXML(String name, boolean value) {
		Document doc = null;
		DocumentBuilder builder = null;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			try {
				doc = builder.parse(new File(H2PA.path));
			}
			catch (FileNotFoundException e) {
				doc = builder.newDocument();
				e.printStackTrace();
			}
			catch (IOException | SAXException e) {
				e.printStackTrace();
			}
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Element root = null;
		root = doc.getDocumentElement();
		if (root == null) {
			root = doc.createElement("quests");
			doc.appendChild(root);
		}

		Element elementQuest = doc.createElement("quest");
		root.appendChild(elementQuest);

		Element elementName = doc.createElement("name");
		elementName.appendChild(doc.createTextNode(name));
		elementQuest.appendChild(elementName);

		Element elementStatus = doc.createElement("status");
		elementStatus.appendChild(doc.createTextNode(value + ""));
		elementQuest.appendChild(elementStatus);

		try {
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty("encoding", "UTF-8");
			tf.transform(new DOMSource(doc), new StreamResult(new File(H2PA.path)));
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void registerQuestGroup(QuestGroup group) {
		questGroups.add(group);
		group.load();
	}

	public static void setCurrentQuestGroup(QuestGroup group) {
		currentGroup = group;
		group.isCurrent = true;
	}

	public static String getURL(String name) {
		return "https://twitter.com/intent/tweet?hashtags=Shotbow,Annihilation,マインクラフト,Minecraft&ref_src=twsrc%5Etfw&text=Annihilationでクエスト：+" + name + "+をクリアしました！&tw_p=tweetbutton&url=https%3A%2F%2Ftwitter.com&via=Y4D0K4R1";
	}
}
