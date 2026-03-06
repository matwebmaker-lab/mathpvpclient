package no.matheo.pvpclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class MatheoPvpClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		KeyBindings.register();
		PvpClientHud.register(); // TNT-timer, FPS, koordinater (HudElementRegistry for 1.21)
		ReachHitboxRenderer.register();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.options == null) return;

			// Fullbright: sett gamma høyt (uten mixin – 1.21.11)
			if (ModConfig.isFullbright()) {
				client.options.getGamma().setValue(16.0);
			} else {
				client.options.getGamma().setValue(1.0);
			}

			if (client.player == null) return;

			// Høyre Shift: åpne meny
			while (KeyBindings.OPEN_MENU.wasPressed()) {
				client.setScreen(new PvpClientScreen());
			}

			// Auto sprint (når W holdes, ikke sneak/vann/item)
			if (ModConfig.isAutoSprint()) {
				boolean forward = client.options.forwardKey.isPressed();
				if (forward && !client.player.isSneaking()
						&& !client.player.horizontalCollision
						&& client.player.isOnGround()
						&& !client.player.isTouchingWater()
						&& !client.player.isUsingItem()) {
					client.player.setSprinting(true);
				}
			}
		});
	}
}
