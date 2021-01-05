package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class BuyMaterialsQuest extends Quest{

	private static BuyMaterialsQuest instance = new BuyMaterialsQuest();

	private BuyMaterialsQuest() {
		this.name = "ポーション素材の収集";
		this.sentence = "空き瓶、ネザーウォート、発酵したクモの目、金のニンジン、レッドストーンを集めます。\nポーション素材は拠点の看板をクリックして開くショップで購入することが出来ます。\nなおレッドストーンは鉱山で採掘して取得します。";
		this.describe = "ポーション素材を収集する";
		this.value = 5;
		this.currentValue = 0;
	}

	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		int temp = 0;
		if (H2PA.checkHasItems(new ItemStack(Items.GLASS_BOTTLE), 3)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.NETHER_WART), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.FERMENTED_SPIDER_EYE), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.GOLDEN_CARROT), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.REDSTONE), 1)) temp++;
		currentValue = temp;
		if (currentValue >= value) clear();
	}

	public static BuyMaterialsQuest getInstance() {
		return instance;
	}
}
