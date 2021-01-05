package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class CreateToolsQuest extends Quest{

	private static CreateToolsQuest instance = new CreateToolsQuest();

	private CreateToolsQuest() {
		this.name = "ツールの作成";
		this.sentence = "剣、ツルハシ、オノ、シャベル、ハサミを作ります。\nAnnihilationでは適正ツール以外ではブロックを壊すことは出来ません。\n閉じ込めトラップ(スワップトラップ)対策にもハサミを含めたツールは作っておきましょう。";
		this.describe = "鉄ツール一式を作る";
		this.value = 5;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		int temp = 0;
		if (H2PA.checkHasItems(new ItemStack(Items.IRON_SWORD), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.IRON_PICKAXE), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.IRON_AXE), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.IRON_SHOVEL), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.SHEARS), 1)) temp++;
		currentValue = temp;
		if (currentValue >= value) clear();
	}

	public static CreateToolsQuest getInstance() {
		return instance;
	}
}
