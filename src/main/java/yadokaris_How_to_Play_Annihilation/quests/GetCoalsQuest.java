package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class GetCoalsQuest extends Quest {

	private static GetCoalsQuest instance = new GetCoalsQuest();

	private GetCoalsQuest() {
		this.name = "石炭の採掘";
		this.sentence = "石炭を4個採掘します。\nマップ内には各チーム1～2つ石炭鉱山が存在します。";
		this.describe = "石炭を4個集める";
		this.value = 4;
		this.currentValue = 0;
	}

	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.chechAmountOfItems(new ItemStack(Items.COAL));
		if (currentValue >= value) clear();
	}

	public static  GetCoalsQuest getInstance() {
		return instance;
	}

}
