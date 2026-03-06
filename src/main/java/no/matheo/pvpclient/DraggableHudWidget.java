package no.matheo.pvpclient;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.function.Consumer;

/**
 * Et HUD-element som kan dras; lagrer ny posisjon ved slipp.
 */
public class DraggableHudWidget extends ClickableWidget {

	private final Consumer<int[]> onPositionChanged;
	private double dragOffsetX, dragOffsetY;
	private boolean dragging;

	public DraggableHudWidget(int x, int y, int width, int height, Text message, Consumer<int[]> onPositionChanged) {
		super(x, y, width, height, message);
		this.onPositionChanged = onPositionChanged;
	}

	@Override
	protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		context.fill(x, y, x + w, y + h, 0xE0_20_20_20);
		int border = 0xFF_2D_D4_9E;
		context.fill(x, y, x + w, y + 1, border);
		context.fill(x, y + h - 1, x + w, y + h, border);
		context.fill(x, y, x + 1, y + h, border);
		context.fill(x + w - 1, y, x + w, y + h, border);
		context.drawText(MinecraftClient.getInstance().textRenderer, getMessage(), x + 4, y + (h - 8) / 2, 0xFFFFFF, false);
	}

	@Override
	protected void appendClickableNarrations(NarrationMessageBuilder builder) {
		appendDefaultNarrations(builder);
	}

	@Override
	public boolean mouseClicked(Click click, boolean doubled) {
		if (!click.isLeft()) return false;
		dragOffsetX = click.x() - getX();
		dragOffsetY = click.y() - getY();
		dragging = true;
		return true;
	}

	@Override
	public boolean mouseDragged(Click click, double deltaX, double deltaY) {
		if (!dragging) return false;
		int newX = (int) (click.x() - dragOffsetX);
		int newY = (int) (click.y() - dragOffsetY);
		int maxX = Math.max(0, getParentWidth() - getWidth());
		int maxY = Math.max(0, getParentHeight() - getHeight());
		newX = MathHelper.clamp(newX, 0, maxX);
		newY = MathHelper.clamp(newY, 0, maxY);
		setPosition(newX, newY);
		return true;
	}

	@Override
	public boolean mouseReleased(Click click) {
		if (click.isLeft() && dragging) {
			dragging = false;
			onPositionChanged.accept(new int[] { getX(), getY() });
			return true;
		}
		return false;
	}

	private int getParentWidth() {
		var client = net.minecraft.client.MinecraftClient.getInstance();
		return client != null && client.getWindow() != null ? client.getWindow().getScaledWidth() : width;
	}

	private int getParentHeight() {
		var client = net.minecraft.client.MinecraftClient.getInstance();
		return client != null && client.getWindow() != null ? client.getWindow().getScaledHeight() : height;
	}
}
