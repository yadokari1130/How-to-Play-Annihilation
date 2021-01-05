package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class GetBowMaterialsQuest extends Quest {

	private static GetBowMaterialsQuest instance = new GetBowMaterialsQuest();

	private GetBowMaterialsQuest() {
		this.name = "弓矢の素材の収集";
		this.sentence = "火打石を16個、羽を16個、糸を3本収集します。\nAnnihilationでは砂利を破壊することで糸、火打石、羽、矢をランダムで手に入れることが出来ます。";
		this.describe = "火打石を16個、羽を16個、糸を3本収集する";
		this.value = 3;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		int temp = 0;
		if (H2PA.checkHasItems(new ItemStack(Items.FLINT), 16)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.FEATHER), 16)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.STRING), 3)) temp++;
		currentValue = temp;
		if (currentValue >= value) clear();
	}

	public static GetBowMaterialsQuest getInstance() {
		return instance;
	}

}