package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class BrewInvisiblePotionQuest extends Quest {

	private static BrewInvisiblePotionQuest instance = new BrewInvisiblePotionQuest();

	private BrewInvisiblePotionQuest() {
		this.name = "透明ポーションの醸造";
		this.sentence = "透明ポーションを醸造します。\nネザーウォート、金のニンジン、発酵したクモの目、レッドストーンの順に入れます。\nなお、透明ポーションでは防具やアイテムまで消すことは出来ないので注意しましょう。";
		this.describe = "透明ポーションを醸造する";
		this.value = 3;
		this.currentValue = 0;
	}

	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.checkAmountOfPotionItems(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionType.getPotionTypeForName("long_invisibility")));
		if (currentValue >= value) clear();
	}

	public static  BrewInvisiblePotionQuest getInstance() {
		return instance;
	}

}
