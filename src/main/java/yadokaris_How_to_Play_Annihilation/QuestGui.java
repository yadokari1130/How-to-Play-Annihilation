package yadokaris_How_to_Play_Annihilation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class QuestGui extends GuiScreen {

	private final Quest quest;

	public QuestGui(Quest quest) {
		this.quest = quest;
	}

	@Override
	public void initGui() {
		this.buttonList.clear();

		this.buttonList.add(new GuiButton(0, this.width / 2 - this.width / 8, this.height / 5 * 4, this.width / 4, 20, "戻る"));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
		this.drawCenteredString(this.fontRenderer, quest.name, (int)(this.width / 2 / 1.5), 20, quest.getColor());
		GlStateManager.popMatrix();
		int showy = 60;
		for (String s : quest.sentence.split("\n")) {
			showy += 10;
			this.drawCenteredString(this.fontRenderer, s, this.width / 2, showy, 0xFFFFFF);
		}
		showy += 40;
		this.drawCenteredString(this.fontRenderer, quest.getDisplayDescribe(), this.width / 2, showy, quest.getColor());

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	 protected void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			Minecraft.getMinecraft().displayGuiScreen(new QuestGroupGui(quest.parent));
		}
	}
}
