package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class CreateBowQuest extends Quest{

	private static CreateBowQuest instance = new CreateBowQuest();

	private CreateBowQuest() {
		this.name = "ツールの作成";
		this.sentence = "弓を作ります。\n弓は遠距離から攻撃することが出来るため、MID戦などで有利です。\nレベルが溜まっていればエンチャントするのもいいでしょう。";
		this.describe = "弓を作る";
		this.value = 1;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		int temp = 0;
		if (H2PA.checkHasItems(new ItemStack(Items.BOW), 1)) temp++;
		currentValue = temp;
		if (currentValue >= value) clear();
	}

	public static CreateBowQuest getInstance() {
		return instance;
	}
}
