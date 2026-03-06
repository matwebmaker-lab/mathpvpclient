package no.matheo.pvpclient;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

/**
 * Skjerm for å flytte HUD-elementer (FPS, XYZ, TNT, Armor). Dra og slipp for å endre posisjon.
 */
public class HudLayoutScreen extends Screen {

	private static final int ELEMENT_W = 120;
	private static final int ELEMENT_H = 20;

	private static final Text TITLE = Text.translatable("menu.matheo_pvp_client.hud_layout_title");
	private static final Text DONE = Text.translatable("menu.matheo_pvp_client.done");

	public HudLayoutScreen() {
		super(TITLE);
	}

	@Override
	protected void init() {
		super.init();
		int maxX = Math.max(0, width - ELEMENT_W);
		int maxY = Math.max(0, height - ELEMENT_H);

		int fpsX = MathHelper.clamp(ModConfig.getFpsX(), 0, maxX);
		int fpsY = MathHelper.clamp(ModConfig.getFpsY(), 0, maxY);
		int coordsX = MathHelper.clamp(ModConfig.getCoordsX(), 0, maxX);
		int coordsY = MathHelper.clamp(ModConfig.getCoordsY(), 0, maxY);
		int tntX = MathHelper.clamp(ModConfig.getTntX(), 0, maxX);
		int tntY = MathHelper.clamp(ModConfig.getTntY(), 0, maxY);
		int armorX = MathHelper.clamp(ModConfig.getArmorX(), 0, maxX);
		int armorY = MathHelper.clamp(ModConfig.getArmorY(), 0, maxY);

		this.addDrawableChild(new DraggableHudWidget(fpsX, fpsY, ELEMENT_W, ELEMENT_H, Text.literal("FPS"), pos -> {
			ModConfig.setFpsX(pos[0]);
			ModConfig.setFpsY(pos[1]);
		}));
		this.addDrawableChild(new DraggableHudWidget(coordsX, coordsY, ELEMENT_W, ELEMENT_H, Text.literal("XYZ"), pos -> {
			ModConfig.setCoordsX(pos[0]);
			ModConfig.setCoordsY(pos[1]);
		}));
		this.addDrawableChild(new DraggableHudWidget(tntX, tntY, ELEMENT_W, ELEMENT_H, Text.literal("TNT"), pos -> {
			ModConfig.setTntX(pos[0]);
			ModConfig.setTntY(pos[1]);
		}));
		this.addDrawableChild(new DraggableHudWidget(armorX, armorY, ELEMENT_W, ELEMENT_H, Text.literal("Armor"), pos -> {
			ModConfig.setArmorX(pos[0]);
			ModConfig.setArmorY(pos[1]);
		}));

		this.addDrawableChild(ButtonWidget.builder(DONE, btn -> {
			if (client != null) client.setScreen(new PvpClientModsScreen());
		}).dimensions(width / 2 - 60, height - 28, 120, 20).build());
	}

	@Override
	public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
		context.fill(0, 0, width, height, 0xE0_08_08_0C);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		context.drawCenteredTextWithShadow(textRenderer, Text.translatable("menu.matheo_pvp_client.hud_layout_hint"), width / 2, 12, 0xAAAAAA);
	}
}
