package no.matheo.pvpclient;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;

import java.util.List;

@SuppressWarnings("deprecation") // HudRenderCallback deprecated i 1.21; HudElementRegistry er erstatning
public class TntTimerRenderer {

	private static final int RANGE = 64;
	private static final int X = 6;
	private static final int Y_START = 6;
	private static final int LINE_HEIGHT = 10;
	private static final int COLOR = 0xFFDD00; // gul/oransje

	public static void register() {
		HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
			if (!ModConfig.isTntTimer()) return;

			MinecraftClient client = MinecraftClient.getInstance();
			if (client.world == null || client.player == null) return;

			Box box = client.player.getBoundingBox().expand(RANGE);
			List<TntEntity> tntList = client.world.getEntitiesByType(
					EntityType.TNT,
					box,
					e -> true
			);

			if (tntList.isEmpty()) return;

			int y = Y_START;
			for (TntEntity tnt : tntList) {
				double seconds = tnt.getFuse() / 20.0;
				String line = String.format("TNT: %.1fs", seconds);
				drawContext.drawText(client.textRenderer, Text.literal(line), X, y, COLOR, true);
				y += LINE_HEIGHT;
			}
		});
	}
}
