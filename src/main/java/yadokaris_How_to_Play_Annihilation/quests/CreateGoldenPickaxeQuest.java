package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class CreateGoldenPickaxeQuest extends Quest {

	private static CreateGoldenPickaxeQuest instance = new CreateGoldenPickaxeQuest();

	private CreateGoldenPickaxeQuest() {
		this.name = "金のピッケルの作成";
		this.sentence = "金のピッケルを作ります。\n耐久値は少ないですが、全ツールの中で最も早く採掘することが出来ます。\nある程度のネクサスダメージを与えるのに適しているでしょう。\n持って行かない分はチェストを隠してその中に保管しておくといいでしょう。";
		this.describe = "金のピッケルを3つ作る";
		this.value = 3;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.chechAmountOfItems(new ItemStack(Items.GOLDEN_PICKAXE));
		if (currentValue >= value) clear();
	}

	public static CreateGoldenPickaxeQuest getInstance() {
		return instance;
	}

}