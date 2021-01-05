package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class CreateArmorsQuest extends Quest{

	private static CreateArmorsQuest instance = new CreateArmorsQuest();

	private CreateArmorsQuest() {
		this.name = "防具の作成";
		this.sentence = "鉄防具を全て作ります。\n防具は被ダメージを減ら	すことが出来る重要なアイテムです。";
		this.describe = "鉄防具一式を作る";
		this.value = 4;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUnpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		int temp = 0;
		if (H2PA.checkHasItems(new ItemStack(Items.IRON_HELMET), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.IRON_CHESTPLATE), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.IRON_LEGGINGS), 1)) temp++;
		if (H2PA.checkHasItems(new ItemStack(Items.IRON_BOOTS), 1)) temp++;
		currentValue = temp;
		if (currentValue >= value) clear();
	}

	public static CreateArmorsQuest getInstance() {
		return instance;
	}
}
