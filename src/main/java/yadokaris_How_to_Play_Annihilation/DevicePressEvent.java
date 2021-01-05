package yadokaris_How_to_Play_Annihilation;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DevicePressEvent {

	public static KeyBinding questKey = new KeyBinding("yadokaris_h2pa.key.Quest", Keyboard.KEY_K, "How to Play Annihilation");

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void KeyHandlingEvent(KeyInputEvent event) {
		if (questKey.isPressed()) Minecraft.getMinecraft().displayGuiScreen(new QuestGroupListGui());
	}
}
