package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class EnchantArmorsQuest extends Quest{

	private static EnchantArmorsQuest instance = new EnchantArmorsQuest();

	private EnchantArmorsQuest() {
		this.name = "剣の強化";
		this.sentence = "鉄防具にエンチャントをします。\nエンチャントをすることでさらに被ダメージを下げることが出来ます。\n低レベルエンチャントでも役立つことは多いので積極的にエンチャントしていきましょう。\nなお、Annihilationのエンチャントではラピスラズリは必要ありません。";
		this.describe = "鉄防具にエンチャントをする";
		this.value = 4;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		int temp = 0;
		if (H2PA.checkHasEnchantedItem(new ItemStack(Items.IRON_HELMET))) temp++;
		if (H2PA.checkHasEnchantedItem(new ItemStack(Items.IRON_CHESTPLATE))) temp++;
		if (H2PA.checkHasEnchantedItem(new ItemStack(Items.IRON_LEGGINGS))) temp++;
		if (H2PA.checkHasEnchantedItem(new ItemStack(Items.IRON_BOOTS))) temp++;
		currentValue = temp;
		if (currentValue >= value) clear();
	}

	public static EnchantArmorsQuest getInstance() {
		return instance;
	}
}
