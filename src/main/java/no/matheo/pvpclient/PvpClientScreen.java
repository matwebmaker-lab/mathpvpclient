package no.matheo.pvpclient;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PvpClientScreen extends Screen {

	private static final Text TITLE = Text.translatable("menu.matheo_pvp_client.title");
	private static final Text FULLBRIGHT_LABEL = Text.translatable("menu.matheo_pvp_client.fullbright");
	private static final Text TNT_TIMER_LABEL = Text.translatable("menu.matheo_pvp_client.tnt_timer");
	private static final Text REACH_HITBOX_LABEL = Text.translatable("menu.matheo_pvp_client.reach_hitbox");

	public PvpClientScreen() {
		super(TITLE);
	}

	@Override
	protected void init() {
		super.init();
		int centerX = this.width / 2;
		int buttonWidth = 200;
		int buttonHeight = 20;
		int y = 40;
		int spacing = 28;

		// Fullbright av/på
		this.addDrawableChild(
				ButtonWidget.builder(getFullbrightButtonText(), button -> {
					ModConfig.toggleFullbright();
					button.setMessage(getFullbrightButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, buttonHeight).build());
		y += spacing;

		// TNT-timer av/på
		this.addDrawableChild(
				ButtonWidget.builder(getTntTimerButtonText(), button -> {
					ModConfig.toggleTntTimer();
					button.setMessage(getTntTimerButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, buttonHeight).build());
		y += spacing;

		// Rød hitbox innenfor slåavstand
		this.addDrawableChild(
				ButtonWidget.builder(getReachHitboxButtonText(), button -> {
					ModConfig.toggleReachHitbox();
					button.setMessage(getReachHitboxButtonText());
				}).dimensions(centerX - buttonWidth / 2, y, buttonWidth, buttonHeight).build());
		y += spacing;

		// Lukk-knapp
		this.addDrawableChild(
				ButtonWidget.builder(Text.translatable("menu.matheo_pvp_client.close"), b -> {
					if (client != null) client.setScreen(null);
				}).dimensions(centerX - buttonWidth / 2, this.height - 28, buttonWidth, buttonHeight).build());
	}

	private static Text getFullbrightButtonText() {
		return FULLBRIGHT_LABEL.copy().append(": ")
				.append(ModConfig.isFullbright()
						? Text.translatable("menu.matheo_pvp_client.on")
						: Text.translatable("menu.matheo_pvp_client.off"));
	}

	private static Text getTntTimerButtonText() {
		return TNT_TIMER_LABEL.copy().append(": ")
				.append(ModConfig.isTntTimer()
						? Text.translatable("menu.matheo_pvp_client.on")
						: Text.translatable("menu.matheo_pvp_client.off"));
	}

	private static Text getReachHitboxButtonText() {
		return REACH_HITBOX_LABEL.copy().append(": ")
				.append(ModConfig.isReachHitbox()
						? Text.translatable("menu.matheo_pvp_client.on")
						: Text.translatable("menu.matheo_pvp_client.off"));
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		this.renderBackground(context, mouseX, mouseY, delta);
		context.drawCenteredTextWithShadow(this.textRenderer, this.getTitle(), this.width / 2, 16, 0xFFFFFF);
		super.render(context, mouseX, mouseY, delta);
	}
}
