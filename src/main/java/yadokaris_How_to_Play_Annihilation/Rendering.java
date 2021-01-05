package yadokaris_How_to_Play_Annihilation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Rendering {

	private static int currentTick;

	@SubscribeEvent
	public void onRender(TickEvent.RenderTickEvent event) {
		if (Minecraft.getMinecraft().currentScreen == null) {
			FontRenderer font = Minecraft.getMinecraft().fontRenderer;
			if (H2PA.currentGroup != null) {
				float showy = -8;
				showy += 10;
				font.drawStringWithShadow(H2PA.currentGroup.name, 2, showy, 0x00BFFF);
				for (Quest quest : H2PA.currentGroup.quests) {
					showy += 10;
					font.drawStringWithShadow(quest.getDisplayDescribe(), 2, showy, quest.getColor());
				}
			}
		}
		currentTick++;
	}
}
