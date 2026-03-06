package no.matheo.pvpclient;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

@SuppressWarnings("deprecation")
public class HudOverlayRenderer {

	private static final int FPS_X = 4;
	private static final int FPS_Y = 4;
	private static final int COORDS_X = 4;
	private static final int COORDS_Y = 14;
	private static final int COLOR = 0xFFFFFF;

	public static void register() {
		HudRenderCallback.EVENT.register((context, tickDelta) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			if (client.player == null) return;

			int y = 4;
			if (ModConfig.isShowFps()) {
				int fps = client.getCurrentFps();
				context.drawText(client.textRenderer, Text.literal("FPS: " + fps), FPS_X, y, COLOR, true);
				y += 10;
			}
			if (ModConfig.isShowCoords()) {
				int x = MathHelper.floor(client.player.getX());
				int py = MathHelper.floor(client.player.getY());
				int z = MathHelper.floor(client.player.getZ());
				context.drawText(client.textRenderer, Text.literal(String.format("XYZ: %d %d %d", x, py, z)), COORDS_X, y, COLOR, true);
			}
		});
	}
}
