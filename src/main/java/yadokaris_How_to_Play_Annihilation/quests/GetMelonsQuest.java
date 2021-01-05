package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class GetMelonsQuest extends Quest {

	private static GetMelonsQuest instance = new GetMelonsQuest();

	private GetMelonsQuest() {
		this.name = "スイカの取得";
		this.sentence = "スイカを30個取得します。\nスイカは破壊しても時間経過で復活するため、何度も破壊することが出来ます。\n小麦は専用の職業で無いと復活しないため、スイカがメインの食料となります。";
		this.describe = "スイカを30個取得する";
		this.value = 30;
		this.currentValue = 0;
	}

	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.chechAmountOfItems(new ItemStack(Items.MELON));
		if (currentValue >= value) clear();
	}

	public static  GetMelonsQuest getInstance() {
		return instance;
	}

}
