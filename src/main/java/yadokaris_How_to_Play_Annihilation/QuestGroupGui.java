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

public class QuestGroupGui extends GuiScreen{

	private final QuestGroup group;

	public QuestGroupGui(QuestGroup group) {
		this.group = group;
	}

	@Override
	public void initGui() {
		this.buttonList.clear();

		int i = 0;
		for (i = 0; i < group.quests.size(); i++) {
			this.buttonList.add(new GuiButton(i, this.width / 2 + 100, 70 + 40*i, 50, 20, "詳細"));
		}

		this.buttonList.add(new GuiButton(i++, this.width / 2 / 2 - 20, this.height / 10 * 9, this.width / 4, 20, "開始"));
		if (H2PA.currentGroup != null) buttonList.get(i - 1).enabled = false;
		this.buttonList.add(new GuiButton(i++, this.width / 2 + 20, this.height / 10 * 9, this.width / 4, 20, "戻る"));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
		this.drawCenteredString(this.fontRenderer, group.name, (int)(this.width / 2 / 1.5), 20, 0x00BFFF);
		GlStateManager.popMatrix();
		int showy = 70;
		for (int i = 0; i < group.quests.size(); i++) {
			Quest quest = group.quests.get(i);
			this.drawString(this.fontRenderer, quest.name, this.width / 2 - 150, showy, quest.getColor());
			this.drawString(this.fontRenderer, quest.getDisplayDescribe(), this.width / 2 - 150, showy + 10, quest.getColor());
			showy += 40;
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	 protected void actionPerformed(GuiButton button) {
		if (button.id == group.quests.size()) {
			SoundHandler handler = Minecraft.getMinecraft().getSoundHandler();
			handler.playSound(PositionedSoundRecord.getRecord(new SoundEvent(new ResourceLocation("minecraft", "block.note.bell")), 0.7F, 1F));
			Style color = new Style().setColor(TextFormatting.AQUA);
			H2PA.player.sendMessage(new TextComponentString("クエスト開始： " + group.name).setStyle(color));
			H2PA.currentGroup = group;
			group.registerEvent();
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		else if (button.id == group.quests.size() + 1) Minecraft.getMinecraft().displayGuiScreen(new QuestGroupListGui());
		else Minecraft.getMinecraft().displayGuiScreen(new QuestGui(group.quests.get(button.id)));
	}
}
