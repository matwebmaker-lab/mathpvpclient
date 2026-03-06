package no.matheo.pvpclient;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * Ett mod-kort i Lunar-stil: ikon + navn + ENABLED (grønn) / DISABLED (rød) toggle.
 */
public class ModCardWidget extends ClickableWidget {

	private static final int CARD_RADIUS = 6;
	private static final int ICON_SIZE = 36;
	private static final int TOGGLE_WIDTH = 72;
	private static final int TOGGLE_HEIGHT = 22;
	private static final int ENABLED_COLOR = 0xFF_2E_7D_32;   // grønn
	private static final int DISABLED_COLOR = 0xFF_C6_28_28; // rød
	private static final int CARD_BG = 0xE8_28_28_28;

	private final Identifier icon;
	private final Text modName;
	private final BooleanSupplier getEnabled;
	private final Consumer<Boolean> onToggle;

	public ModCardWidget(int x, int y, int width, int height, Identifier icon, Text modName,
	                     BooleanSupplier getEnabled, Consumer<Boolean> onToggle) {
		super(x, y, width, height, modName);
		this.icon = icon;
		this.modName = modName;
		this.getEnabled = getEnabled;
		this.onToggle = onToggle;
	}

	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		var textRenderer = MinecraftClient.getInstance().textRenderer;
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		fillRoundedRect(context, x, y, w, h, CARD_BG);

		int textX = x + 12;
		if (icon != null) {
			context.drawTexturedQuad(icon, x + 6, y + (h - ICON_SIZE) / 2, x + 6 + ICON_SIZE, y + (h + ICON_SIZE) / 2, 0f, 1f, 0f, 1f);
			textX = x + 12 + ICON_SIZE + 6;
		}
		context.drawText(textRenderer, modName, textX, y + (h - 8) / 2, 0xFFFFFF, false);

		int tx = x + w - TOGGLE_WIDTH - 12;
		int ty = y + (h - TOGGLE_HEIGHT) / 2;
		boolean enabled = getEnabled.getAsBoolean();
		int color = enabled ? ENABLED_COLOR : DISABLED_COLOR;
		boolean hovered = mouseX >= tx && mouseX <= tx + TOGGLE_WIDTH && mouseY >= ty && mouseY <= ty + TOGGLE_HEIGHT;
		if (hovered) {
			color = blendAlpha(color, 0.25f);
		}
		fillRoundedRect(context, tx, ty, TOGGLE_WIDTH, TOGGLE_HEIGHT, color);
		Text label = Text.literal(enabled ? "ENABLED" : "DISABLED");
		int lw = textRenderer.getWidth(label);
		context.drawText(textRenderer, label, tx + (TOGGLE_WIDTH - lw) / 2, ty + (TOGGLE_HEIGHT - 8) / 2, 0xFFFFFF, false);
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
		appendDefaultNarrations(builder);
	}

	@Override
	public void onClick(net.minecraft.client.gui.Click click, boolean doubled) {
		double mouseX = click.x();
		double mouseY = click.y();
		int x = getX();
		int w = getWidth();
		int tx = x + w - TOGGLE_WIDTH - 12;
		int ty = getY() + (getHeight() - TOGGLE_HEIGHT) / 2;
		if (mouseX >= tx && mouseX <= tx + TOGGLE_WIDTH && mouseY >= ty && mouseY <= ty + TOGGLE_HEIGHT) {
			onToggle.accept(!getEnabled.getAsBoolean());
		}
	}

	private static void fillRoundedRect(DrawContext context, int x, int y, int w, int h, int color) {
		context.fill(x + CARD_RADIUS, y, x + w - CARD_RADIUS, y + h, color);
		context.fill(x, y + CARD_RADIUS, x + CARD_RADIUS, y + h - CARD_RADIUS, color);
		context.fill(x + w - CARD_RADIUS, y + CARD_RADIUS, x + w, y + h - CARD_RADIUS, color);
	}

	private static int blendAlpha(int argb, float factor) {
		int a = (argb >> 24) & 0xFF;
		int r = (argb >> 16) & 0xFF;
		int g = (argb >> 8) & 0xFF;
		int b = argb & 0xFF;
		r = MathHelper.clamp((int)(r + (255 - r) * factor), 0, 255);
		g = MathHelper.clamp((int)(g + (255 - g) * factor), 0, 255);
		b = MathHelper.clamp((int)(b + (255 - b) * factor), 0, 255);
		return (a << 24) | (r << 16) | (g << 8) | b;
	}
}
