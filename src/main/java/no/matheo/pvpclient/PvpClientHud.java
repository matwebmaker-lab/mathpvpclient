package no.matheo.pvpclient;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;

import java.util.List;

/**
 * HUD-tegning for 1.21: TNT-timer, FPS, koordinater.
 * Bruker HudElementRegistry (HudRenderCallback fungerer ikke lenger i 1.21).
 */
public class PvpClientHud {

	private static final Identifier HUD_ID = Identifier.of("matheo-pvp-client", "overlay");
	private static final int RANGE = 64;
	private static final int X = 6;
	private static final int LINE_HEIGHT = 10;
	private static final int TNT_COLOR = 0xFFDD00;
	private static final int TEXT_COLOR = 0xFFFFFF;

	public static void register() {
		HudElementRegistry.attachElementAfter(
				VanillaHudElements.MISC_OVERLAYS,
				HUD_ID,
				PvpClientHud::render
		);
	}

	private static void render(DrawContext context, RenderTickCounter tickCounter) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null || client.world == null) return;

		int y = 6;

		// FPS
		if (ModConfig.isShowFps()) {
			int fps = client.getCurrentFps();
			context.drawText(client.textRenderer, Text.literal("FPS: " + fps), X, y, TEXT_COLOR, true);
			y += LINE_HEIGHT;
		}

		// Koordinater
		if (ModConfig.isShowCoords()) {
			int px = MathHelper.floor(client.player.getX());
			int py = MathHelper.floor(client.player.getY());
			int pz = MathHelper.floor(client.player.getZ());
			context.drawText(client.textRenderer, Text.literal(String.format("XYZ: %d %d %d", px, py, pz)), X, y, TEXT_COLOR, true);
			y += LINE_HEIGHT;
		}

		// TNT-timer
		if (ModConfig.isTntTimer()) {
			Box box = client.player.getBoundingBox().expand(RANGE);
			List<TntEntity> tntList = client.world.getEntitiesByType(EntityType.TNT, box, e -> true);
			for (TntEntity tnt : tntList) {
				double seconds = tnt.getFuse() / 20.0;
				context.drawText(client.textRenderer, Text.literal(String.format("TNT: %.1fs", seconds)), X, y, TNT_COLOR, true);
				y += LINE_HEIGHT;
			}
		}
	}
}
