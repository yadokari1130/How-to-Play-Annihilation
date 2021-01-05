package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class GetGoldOresQuest extends Quest {

	private static GetGoldOresQuest instance = new GetGoldOresQuest();

	private GetGoldOresQuest() {
		this.name = "金の採掘";
		this.sentence = "金鉱石を30個採掘します。\nマップ内には各チームに1つ金鉱山が存在します。\n鉱石は採掘しても時間経過で復活するため、何度も採掘することが出来ます。\nしかし金鉱山を狙ってくる敵もいるので注意しましょう。";
		this.describe = "金鉱石を30個集める";
		this.value = 30;
		this.currentValue = 0;
	}

	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.chechAmountOfItems(new ItemStack(Blocks.GOLD_ORE));
		if (currentValue >= value) clear();
	}

	public static  GetGoldOresQuest getInstance() {
		return instance;
	}

}
