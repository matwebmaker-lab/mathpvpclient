package no.matheo.pvpclient;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/**
 * Lunar-stil hovedmeny ved høyre Shift: logo, tittel og MODS-knapp som åpner mods-panelet.
 */
public class PvpClientScreen extends Screen {

	private static final int ACCENT_COLOR = 0xFF_2D_D4_9E;
	private static final int PANEL_BG = 0xE0_1A_1A_1A;

	private static final Identifier LOGO = Identifier.of("matheo-pvp-client", "textures/gui/logo.png");

	private static final Text TITLE = Text.translatable("menu.matheo_pvp_client.title");
	private static final Text MODS_BUTTON = Text.translatable("menu.matheo_pvp_client.mods");
	private static final Text CLOSE_BUTTON = Text.translatable("menu.matheo_pvp_client.close");

	public PvpClientScreen() {
		super(TITLE);
	}

	@Override
	protected void init() {
		super.init();
		int centerX = this.width / 2;
		int panelWidth = 280;
		int panelTop = this.height / 2 - 100;
		int logoSize = 80;
		int logoX = centerX - logoSize / 2;
		int logoY = panelTop + 16;

		// MODS-knapp (sentrert, under logo og tittel)
		int buttonWidth = 200;
		int buttonY = panelTop + 16 + logoSize + 12 + 20 + 16;
		this.addDrawableChild(ButtonWidget.builder(MODS_BUTTON, btn -> {
			if (client != null) client.setScreen(new PvpClientModsScreen());
		}).dimensions(centerX - buttonWidth / 2, buttonY, buttonWidth, 24).build());

		// Lukk
		this.addDrawableChild(ButtonWidget.builder(CLOSE_BUTTON, btn -> {
			if (client != null) client.setScreen(null);
		}).dimensions(centerX - buttonWidth / 2, buttonY + 36, buttonWidth, 20).build());
	}

	@Override
	public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
		context.fill(0, 0, this.width, this.height, 0xC0_08_08_0C);
		int centerX = this.width / 2;
		int panelWidth = 300;
		int panelHeight = 220;
		int panelLeft = centerX - panelWidth / 2;
		int panelTop = this.height / 2 - panelHeight / 2;
		context.fill(panelLeft, panelTop, panelLeft + panelWidth, panelTop + panelHeight, PANEL_BG);
		context.fill(panelLeft, panelTop, panelLeft + panelWidth, panelTop + 2, ACCENT_COLOR);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);

		int centerX = this.width / 2;
		int panelTop = this.height / 2 - 100;
		int logoSize = 80;
		int logoX = centerX - logoSize / 2;
		int logoY = panelTop + 16;

		// Logo
		context.drawTexturedQuad(LOGO, logoX, logoY, logoX + logoSize, logoY + logoSize, 0f, 1f, 0f, 1f);
		// Tittel under logo
		context.drawCenteredTextWithShadow(this.textRenderer, TITLE, centerX, logoY + logoSize + 8, 0xFFFFFF);
	}
}
