package yadokaris_How_to_Play_Annihilation.quests;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import yadokaris_How_to_Play_Annihilation.H2PA;
import yadokaris_How_to_Play_Annihilation.Quest;

public class GetXPQuest extends Quest {

	private static GetXPQuest instance = new GetXPQuest();

	private GetXPQuest() {
		this.name = "レベルアップ";
		this.sentence = "20レベルまで上げます。\nAnnihilationでは鉱石や砂利、木、スイカなどを破壊すると経験値を得ることが出来ます。\n集めた経験値は拠点に設置されているエンチャント台で使うことが出来ます。";
		this.describe = "20レベルまで上げる";
		this.value = 20;
		this.currentValue = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onUnpdate(PlayerTickEvent e) {
		if (doClear || H2PA.player == null) return;
		currentValue = H2PA.player.experienceLevel;
		if (currentValue >= value) clear();
	}

	public static GetXPQuest getInstance() {
		return instance;
	}
}
