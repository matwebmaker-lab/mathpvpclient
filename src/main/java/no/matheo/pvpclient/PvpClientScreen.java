package no.matheo.pvpclient;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/**
 * Lunar-inspirert innstillingsmeny: mørk panel, kategorier, flere valg.
 */
public class PvpClientScreen extends Screen {

	private static final int SIDEBAR_WIDTH = 120;
	private static final int PANEL_PADDING = 16;
	private static final int ACCENT_COLOR = 0xFF_2D_D4_9E; // Lunar-lignende teal
	private static final int PANEL_BG = 0xE0_1A_1A_1A;
	private static final int SIDEBAR_BG = 0xF0_12_12_12;

	private static final Identifier LOGO = Identifier.of("matheo-pvp-client", "textures/gui/logo.png");

	private static final Text TITLE = Text.translatable("menu.matheo_pvp_client.title");
	private static final Text CAT_VISUALS = Text.translatable("menu.matheo_pvp_client.category.visuals");
	private static final Text CAT_HUD = Text.translatable("menu.matheo_pvp_client.category.hud");
	private static final Text AUTO_SPRINT_LABEL = Text.translatable("menu.matheo_pvp_client.auto_sprint");
	private static final Text FULLBRIGHT_LABEL = Text.translatable("menu.matheo_pvp_client.fullbright");
	private static final Text TNT_TIMER_LABEL = Text.translatable("menu.matheo_pvp_client.tnt_timer");
	private static final Text REACH_HITBOX_LABEL = Text.translatable("menu.matheo_pvp_client.reach_hitbox");
	private static final Text FPS_LABEL = Text.translatable("menu.matheo_pvp_client.fps");
	private static final Text COORDS_LABEL = Text.translatable("menu.matheo_pvp_client.coords");
	private static final Text ARMOR_LABEL = Text.translatable("menu.matheo_pvp_client.armor");

	public PvpClientScreen() {
		super(TITLE);
	}

	@Override
	protected void init() {
		super.init();
		int panelWidth = this.width - SIDEBAR_WIDTH - PANEL_PADDING * 2;
		int buttonWidth = MathHelper.clamp(panelWidth - PANEL_PADDING * 2, 180, 260);
		int centerX = SIDEBAR_WIDTH + (this.width - SIDEBAR_WIDTH) / 2;
		int spacing = 26;
		int y = 56;

		// Visuals (Auto Sprint, Fullbright, …)
		this.addDrawableChild(
				ButtonWidget.builder(getAutoSprintButtonText(), b -> {
					ModConfig.toggleAutoSprint();
					b.setMessage(getAutoSprintButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, 20).build());
		y += spacing;
		this.addDrawableChild(
				ButtonWidget.builder(getFullbrightButtonText(), b -> {
					ModConfig.toggleFullbright();
					b.setMessage(getFullbrightButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, 20).build());
		y += spacing;
		this.addDrawableChild(
				ButtonWidget.builder(getTntTimerButtonText(), b -> {
					ModConfig.toggleTntTimer();
					b.setMessage(getTntTimerButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, 20).build());
		y += spacing;
		this.addDrawableChild(
				ButtonWidget.builder(getReachHitboxButtonText(), b -> {
					ModConfig.toggleReachHitbox();
					b.setMessage(getReachHitboxButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, 20).build());
		y += spacing + 20;

		// HUD
		this.addDrawableChild(
				ButtonWidget.builder(getFpsButtonText(), b -> {
					ModConfig.toggleShowFps();
					b.setMessage(getFpsButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, 20).build());
		y += spacing;
		this.addDrawableChild(
				ButtonWidget.builder(getCoordsButtonText(), b -> {
					ModConfig.toggleShowCoords();
					b.setMessage(getCoordsButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, 20).build());
		y += spacing;
		this.addDrawableChild(
				ButtonWidget.builder(getArmorButtonText(), b -> {
					ModConfig.toggleShowArmor();
					b.setMessage(getArmorButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, 20).build());

		// Lukk
		this.addDrawableChild(
				ButtonWidget.builder(Text.translatable("menu.matheo_pvp_client.close"), b -> {
					if (client != null) client.setScreen(null);
				}).dimensions(centerX - buttonWidth / 2, this.height - 32, buttonWidth, 20).build());
	}

	private static Text getAutoSprintButtonText() {
		return toggleText(AUTO_SPRINT_LABEL, ModConfig.isAutoSprint());
	}

	private static Text getFullbrightButtonText() {
		return toggleText(FULLBRIGHT_LABEL, ModConfig.isFullbright());
	}

	private static Text getTntTimerButtonText() {
		return toggleText(TNT_TIMER_LABEL, ModConfig.isTntTimer());
	}

	private static Text getReachHitboxButtonText() {
		return toggleText(REACH_HITBOX_LABEL, ModConfig.isReachHitbox());
	}

	private static Text getFpsButtonText() {
		return toggleText(FPS_LABEL, ModConfig.isShowFps());
	}

	private static Text getCoordsButtonText() {
		return toggleText(COORDS_LABEL, ModConfig.isShowCoords());
	}

	private static Text getArmorButtonText() {
		return toggleText(ARMOR_LABEL, ModConfig.isShowArmor());
	}

	private static Text toggleText(Text label, boolean on) {
		return label.copy().append(": ")
				.append(Text.translatable(on ? "menu.matheo_pvp_client.on" : "menu.matheo_pvp_client.off"));
	}

	@Override
	public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
		// Mørk bakgrunn uten blur (unngår "Can only blur once per frame")
		context.fill(0, 0, this.width, this.height, 0xC0_08_08_0C);
		// Sidebar – Lunar-lignende mørk stripe
		context.fill(0, 0, SIDEBAR_WIDTH, this.height, SIDEBAR_BG);
		// Vertikal accentlinje
		context.fill(SIDEBAR_WIDTH, 0, SIDEBAR_WIDTH + 2, this.height, ACCENT_COLOR);
		// Hovedpanel (rundt innhold)
		int panelLeft = SIDEBAR_WIDTH + PANEL_PADDING;
		int panelTop = PANEL_PADDING * 2;
		int panelRight = this.width - PANEL_PADDING;
		int panelBottom = this.height - PANEL_PADDING;
		context.fill(panelLeft, panelTop, panelRight, panelBottom, PANEL_BG);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);

		// Logo øverst i hovedpanelet (sentrert)
		int logoSize = 100;
		int logoX = SIDEBAR_WIDTH + (this.width - SIDEBAR_WIDTH) / 2 - logoSize / 2;
		int logoY = 10;
		context.drawTexturedQuad(LOGO, logoX, logoY, logoX + logoSize, logoY + logoSize, 0f, 1f, 0f, 1f);

		// Tittel i sidebar (under logo-område i layout)
		context.drawText(this.textRenderer, this.getTitle(), 16, 20, 0xFFFFFF, false);
		// Kategorier i sidebar
		context.drawText(this.textRenderer, CAT_VISUALS, 16, 48, ACCENT_COLOR, false);
		context.drawText(this.textRenderer, CAT_HUD, 16, 168, ACCENT_COLOR, false);

		// Seksjonsoverskrifter over innhold
		int cx = SIDEBAR_WIDTH + (this.width - SIDEBAR_WIDTH) / 2;
		context.drawCenteredTextWithShadow(this.textRenderer, CAT_VISUALS, cx, 40, 0xAAAAAA);
		context.drawCenteredTextWithShadow(this.textRenderer, CAT_HUD, cx, 184, 0xAAAAAA);
	}
}
