package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class EnchantToolsQuest extends Quest {

	private static EnchantToolsQuest instance = new EnchantToolsQuest();

	private EnchantToolsQuest() {
		this.name = "剣の強化";
		this.sentence = "鉄の剣にエンチャントをします。\nエンチャントをすることで攻撃力を上げることが出来ます。\n低レベルエンチャントでも役立つことは多いので積極的にエンチャントしていきましょう。\nなお、Annihilationのエンチャントではラピスラズリは必要ありません。";
		this.describe = "鉄の剣にエンチャントをする";
		this.value = 1;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		int temp = 0;
		if (H2PA.checkHasEnchantedItem(new ItemStack(Items.IRON_SWORD))) temp++;
		currentValue = temp;
		if (currentValue >= value) clear();
	}

	public static EnchantToolsQuest getInstance() {
		return instance;
	}
}
