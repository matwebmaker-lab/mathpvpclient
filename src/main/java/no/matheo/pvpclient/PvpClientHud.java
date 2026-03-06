package no.matheo.pvpclient;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
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
	private static final int LINE_HEIGHT = 10;
	private static final int TNT_COLOR = 0xFFDD00;
	private static final int TEXT_COLOR = 0xFFFFFF;

	public static void register() {
		// addLast så vi ikke arver hideGui – HUD vises når spilleren er i verden
		HudElementRegistry.addLast(HUD_ID, PvpClientHud::render);
	}

	private static void render(DrawContext context, RenderTickCounter tickCounter) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null || client.world == null) return;

		// FPS (flyttbar posisjon)
		if (ModConfig.isShowFps()) {
			int fps = client.getCurrentFps();
			context.drawText(client.textRenderer, Text.literal("FPS: " + fps), ModConfig.getFpsX(), ModConfig.getFpsY(), TEXT_COLOR, true);
		}

		// Koordinater (flyttbar posisjon)
		if (ModConfig.isShowCoords()) {
			int px = MathHelper.floor(client.player.getX());
			int py = MathHelper.floor(client.player.getY());
			int pz = MathHelper.floor(client.player.getZ());
			context.drawText(client.textRenderer, Text.literal(String.format("XYZ: %d %d %d", px, py, pz)), ModConfig.getCoordsX(), ModConfig.getCoordsY(), TEXT_COLOR, true);
		}

		// TNT-timer (første flyttbar; flere TNT under hverandre)
		if (ModConfig.isTntTimer()) {
			Box box = client.player.getBoundingBox().expand(RANGE);
			List<TntEntity> tntList = client.world.getEntitiesByType(EntityType.TNT, box, e -> true);
			int ty = ModConfig.getTntY();
			for (TntEntity tnt : tntList) {
				double seconds = tnt.getFuse() / 20.0;
				context.drawText(client.textRenderer, Text.literal(String.format("TNT: %.1fs", seconds)), ModConfig.getTntX(), ty, TNT_COLOR, true);
				ty += LINE_HEIGHT;
			}
		}

		// Armor (flyttbar posisjon)
		if (ModConfig.isShowArmor()) {
			int armor = client.player.getArmor();
			context.drawText(client.textRenderer, Text.literal("Armor: " + armor), ModConfig.getArmorX(), ModConfig.getArmorY(), TEXT_COLOR, true);
		}
	}
}
