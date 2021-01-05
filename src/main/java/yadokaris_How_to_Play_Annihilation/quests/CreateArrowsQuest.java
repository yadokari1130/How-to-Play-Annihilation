package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class CreateArrowsQuest extends Quest {

	private static CreateArrowsQuest instance = new CreateArrowsQuest();

	private CreateArrowsQuest() {
		this.name = "矢の収集";
		this.sentence = "矢を64本収集します。\nAnnihilationではクラフト以外に砂利を破壊することでも取得できます。\nまた火打石と棒のみで矢を3本クラフト出来る特殊レシピが存在します。";
		this.describe = "矢を64本収集する";
		this.value = 64;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.chechAmountOfItems(new ItemStack(Items.ARROW));
		if (currentValue >= value) clear();
	}

	public static CreateArrowsQuest getInstance() {
		return instance;
	}

}