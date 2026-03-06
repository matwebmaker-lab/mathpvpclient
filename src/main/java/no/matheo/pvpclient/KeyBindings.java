package no.matheo.pvpclient;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

	public static KeyBinding OPEN_MENU;

	public static void register() {
		OPEN_MENU = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.matheo_pvp_client.open_menu",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				KeyBinding.Category.MISC
		));
	}
}
