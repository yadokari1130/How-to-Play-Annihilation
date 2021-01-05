package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class GetLogsQuest extends Quest {

	private static GetLogsQuest instance = new GetLogsQuest();

	private GetLogsQuest() {
		this.name = "原木の伐採";
		this.sentence = "原木を10個伐採します。\n原木は伐採しても時間経過で復活するため、何度も伐採することが出来ます。\nまた、原木は右クリックで木材にすることが出来ます。";
		this.describe = "原木を10個伐採する";
		this.value = 10;
		this.currentValue = 0;
	}

	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.chechAmountOfItems(new ItemStack(Blocks.LOG));
		if (currentValue >= value) clear();
	}

	public static  GetLogsQuest getInstance() {
		return instance;
	}

}
