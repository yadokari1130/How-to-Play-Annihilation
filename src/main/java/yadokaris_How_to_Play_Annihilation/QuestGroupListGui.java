package yadokaris_How_to_Play_Annihilation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class QuestGroupListGui extends GuiScreen {

	@Override
	public void initGui() {
		this.buttonList.clear();

		int count = 0;
		int showy = 65;
		for (int i = 0; i < H2PA.questGroups.size(); i++) {
			QuestGroup group = H2PA.questGroups.get(i);
			if (!group.canStart() || group.doClear()) continue;
			count++;
			this.buttonList.add(new GuiButton(i, this.width / 2 + 70, showy, 50, 20, "詳細"));
			showy += 40;
		}

		this.buttonList.add(new GuiButton(H2PA.questGroups.size(), this.width / 2 - this.width / 8, this.height / 5 * 4, this.width / 4, 20, "クエスト中止"));
		if (H2PA.currentGroup == null) buttonList.get(count).enabled = false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
		this.drawCenteredString(this.fontRenderer, "クエスト一覧", (int)(this.width / 2 / 1.5), 20, 0xFFFFFF);
		GlStateManager.popMatrix();
		int showy = 70;
		for (int i = 0; i < H2PA.questGroups.size(); i++) {
			QuestGroup group = H2PA.questGroups.get(i);
			if (!group.canStart() || group.doClear()) continue;
			this.drawString(this.fontRenderer, group.name, this.width / 2 - 100, showy, group.getColor());
			showy += 40;
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	 protected void actionPerformed(GuiButton button) {
		if (button.id == H2PA.questGroups.size()) {
			SoundHandler handler = Minecraft.getMinecraft().getSoundHandler();
			handler.playSound(PositionedSoundRecord.getRecord(new SoundEvent(new ResourceLocation("minecraft", "block.fire.extinguish")), 1F, 0.25F));
			Style color = new Style().setColor(TextFormatting.YELLOW);
			H2PA.player.sendMessage(new TextComponentString("クエスト中止： " + H2PA.currentGroup.getName()).setStyle(color));
			H2PA.currentGroup.unregisterEvent();
			H2PA.currentGroup = null;
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		else Minecraft.getMinecraft().displayGuiScreen(new QuestGroupGui(H2PA.questGroups.get(button.id)));
	}
}
