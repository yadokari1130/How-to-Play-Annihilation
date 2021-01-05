package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class BuyBrewingStandQuest extends Quest{

	private static BuyBrewingStandQuest instance = new BuyBrewingStandQuest();

	private BuyBrewingStandQuest() {
		this.name = "醸造台の購入";
		this.sentence = "醸造台を1つ購入します。\nこの醸造台は味方は開くことが出来ません。\n味方にポーションが取られることはないので、安全な場所で使用しましょう。\nなお、Annihilationの醸造ではブレイズパウダーは必要ありません。";
		this.describe = "醸造台を1つ購入する";
		this.value = 1;
		this.currentValue = 0;
	}

	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.chechAmountOfItems(new ItemStack(Items.BREWING_STAND));
		if (currentValue >= value) clear();
	}

	public static BuyBrewingStandQuest getInstance() {
		return instance;
	}
}
