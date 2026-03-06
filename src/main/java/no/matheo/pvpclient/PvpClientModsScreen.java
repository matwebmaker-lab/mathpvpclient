package no.matheo.pvpclient;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Lunar-stil mods-panel: sidebar, faner (ALL, HUD, Visuals), rutenett med mod-kort og ENABLED/DISABLED.
 */
public class PvpClientModsScreen extends Screen {

	private static final int SIDEBAR_WIDTH = 140;
	private static final int HEADER_HEIGHT = 40;
	private static final int TAB_ROW = 48;
	private static final int CONTENT_TOP = 60;
	private static final int CARD_WIDTH = 220;
	private static final int CARD_HEIGHT = 44;
	private static final int CARD_GAP = 10;
	private static final int SCROLLBAR_WIDTH = 8;
	private static final int ACCENT_COLOR = 0xFF_2D_D4_9E;
	private static final int PANEL_BG = 0xE0_1A_1A_1A;
	private static final int SIDEBAR_BG = 0xF0_12_12_12;

	private static final Identifier LOGO = Identifier.of("matheo-pvp-client", "textures/gui/logo.png");
	private static final Identifier ICON_AUTO_SPRINT = Identifier.of("matheo-pvp-client", "textures/gui/icon_auto_sprint.png");
	private static final Identifier ICON_FULLBRIGHT = Identifier.of("matheo-pvp-client", "textures/gui/icon_fullbright.png");
	private static final Identifier ICON_TNT_TIMER = Identifier.of("matheo-pvp-client", "textures/gui/icon_tnt_timer.png");
	private static final Identifier ICON_REACH_HITBOX = Identifier.of("matheo-pvp-client", "textures/gui/icon_reach_hitbox.png");
	private static final Identifier ICON_FPS = Identifier.of("matheo-pvp-client", "textures/gui/icon_fps.png");
	private static final Identifier ICON_COORDS = Identifier.of("matheo-pvp-client", "textures/gui/icon_coords.png");
	private static final Identifier ICON_ARMOR = Identifier.of("matheo-pvp-client", "textures/gui/icon_armor.png");

	private static final Text TITLE = Text.translatable("menu.matheo_pvp_client.title");
	private static final Text TAB_ALL = Text.literal("ALL");
	private static final Text TAB_HUD = Text.literal("HUD");
	private static final Text TAB_VISUALS = Text.literal("VISUALS");
	private static final Text EDIT_HUD_LAYOUT = Text.literal("EDIT HUD LAYOUT");

	private int activeTab = 0; // 0=ALL, 1=HUD, 2=Visuals
	private final List<ModCardWidget> cards = new ArrayList<>();
	private final List<PositionedCard> positionedCards = new ArrayList<>();
	private int scrollOffset = 0;
	private int maxScroll = 0;
	private int contentHeight = 0;

	public PvpClientModsScreen() {
		super(TITLE);
	}

	@Override
	protected void init() {
		super.init();
		cards.clear();
		positionedCards.clear();
		scrollOffset = 0;

		int mainLeft = SIDEBAR_WIDTH;
		int mainWidth = this.width - SIDEBAR_WIDTH;
		int closeX = this.width - 24;
		this.addDrawableChild(ButtonWidget.builder(Text.literal("✕"), btn -> {
			if (client != null) client.setScreen(new PvpClientScreen());
		}).dimensions(closeX, 8, 18, 18).build());

		// Sidebar: "EDIT HUD LAYOUT" – åpne flyttbar HUD-editor
		this.addDrawableChild(ButtonWidget.builder(EDIT_HUD_LAYOUT, btn -> {
			if (client != null) client.setScreen(new HudLayoutScreen());
		}).dimensions(10, this.height - 32, SIDEBAR_WIDTH - 20, 20).build());

		// Mod-kort i rutenett
		int cols = Math.max(1, (mainWidth - 24 - SCROLLBAR_WIDTH) / (CARD_WIDTH + CARD_GAP));
		int x0 = mainLeft + 16;
		int y0 = CONTENT_TOP;

		ModEntry[] entries = {
			new ModEntry(ICON_AUTO_SPRINT, Text.translatable("menu.matheo_pvp_client.auto_sprint"), "visuals", ModConfig::isAutoSprint, ModConfig::setAutoSprint),
			new ModEntry(ICON_FULLBRIGHT, Text.translatable("menu.matheo_pvp_client.fullbright"), "visuals", ModConfig::isFullbright, ModConfig::setFullbright),
			new ModEntry(ICON_TNT_TIMER, Text.translatable("menu.matheo_pvp_client.tnt_timer"), "hud", ModConfig::isTntTimer, ModConfig::setTntTimer),
			new ModEntry(ICON_REACH_HITBOX, Text.translatable("menu.matheo_pvp_client.reach_hitbox"), "visuals", ModConfig::isReachHitbox, ModConfig::setReachHitbox),
			new ModEntry(ICON_FPS, Text.translatable("menu.matheo_pvp_client.fps"), "hud", ModConfig::isShowFps, ModConfig::setShowFps),
			new ModEntry(ICON_COORDS, Text.translatable("menu.matheo_pvp_client.coords"), "hud", ModConfig::isShowCoords, ModConfig::setShowCoords),
			new ModEntry(ICON_ARMOR, Text.translatable("menu.matheo_pvp_client.armor"), "hud", ModConfig::isShowArmor, ModConfig::setShowArmor),
		};
		for (int i = 0; i < entries.length; i++) {
			int col = i % cols;
			int row = i / cols;
			int x = x0 + col * (CARD_WIDTH + CARD_GAP);
			int y = y0 + row * (CARD_HEIGHT + CARD_GAP);
			ModEntry e = entries[i];
			ModCardWidget w = new ModCardWidget(x, y, CARD_WIDTH, CARD_HEIGHT, e.icon, e.name, e.getter, e.setter);
			cards.add(w);
			positionedCards.add(new PositionedCard(w, x, y, e.category));
		}

		int rows = (entries.length + cols - 1) / cols;
		contentHeight = rows * (CARD_HEIGHT + CARD_GAP) + 20;
		int visibleH = this.height - CONTENT_TOP - 16;
		maxScroll = Math.max(0, contentHeight - visibleH);

		for (ModCardWidget c : cards) {
			this.addDrawableChild(c);
		}
	}

	private record ModEntry(Identifier icon, Text name, String category,
	                        java.util.function.BooleanSupplier getter,
	                        java.util.function.Consumer<Boolean> setter) {}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		if (mouseX > SIDEBAR_WIDTH) {
			int delta = (int) Math.signum(-verticalAmount) * 40;
			scrollOffset = MathHelper.clamp(scrollOffset + delta, 0, maxScroll);
			applyScroll();
			return true;
		}
		return false;
	}

	private void applyScroll() {
		int mainLeft = SIDEBAR_WIDTH;
		int mainWidth = this.width - SIDEBAR_WIDTH;
		int cols = Math.max(1, (mainWidth - 24 - SCROLLBAR_WIDTH) / (CARD_WIDTH + CARD_GAP));
		int x0 = mainLeft + 16;
		int y0 = CONTENT_TOP;
		int col = 0;
		int row = 0;
		for (PositionedCard pc : positionedCards) {
			boolean show = (activeTab == 0) || (activeTab == 1 && "hud".equals(pc.category)) || (activeTab == 2 && "visuals".equals(pc.category));
			pc.widget.visible = show;
			if (show) {
				int x = x0 + col * (CARD_WIDTH + CARD_GAP);
				int y = y0 + row * (CARD_HEIGHT + CARD_GAP) - scrollOffset;
				pc.widget.setPosition(x, y);
				col++;
				if (col >= cols) { col = 0; row++; }
			}
		}
	}

	@Override
	public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
		context.fill(0, 0, this.width, this.height, 0xC0_08_08_0C);
		context.fill(0, 0, SIDEBAR_WIDTH, this.height, SIDEBAR_BG);
		context.fill(SIDEBAR_WIDTH, 0, SIDEBAR_WIDTH + 2, this.height, ACCENT_COLOR);
		context.fill(SIDEBAR_WIDTH + 2, 0, this.width, this.height, PANEL_BG);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		applyScroll();
		super.render(context, mouseX, mouseY, delta);

		// Header: logo + tittel
		int logoSize = 32;
		context.drawTexturedQuad(LOGO, 12, 8, 12 + logoSize, 8 + logoSize, 0f, 1f, 0f, 1f);
		context.drawText(this.textRenderer, TITLE, 50, 16, 0xFFFFFF, false);

		// Faner: MODS (allerede valgt), SETTINGS, WAYPOINTS – vi viser ALL, HUD, VISUALS
		int tabW = 72;
		int tabY = TAB_ROW - 20;
		drawTab(context, 0, SIDEBAR_WIDTH + 12, tabY, TAB_ALL);
		drawTab(context, 1, SIDEBAR_WIDTH + 12 + tabW + 4, tabY, TAB_HUD);
		drawTab(context, 2, SIDEBAR_WIDTH + 12 + (tabW + 4) * 2, tabY, TAB_VISUALS);

		// Scrollbar
		if (maxScroll > 0) {
			int sbX = this.width - SCROLLBAR_WIDTH - 6;
			int sbY = CONTENT_TOP;
			int sbH = this.height - CONTENT_TOP - 12;
			context.fill(sbX, sbY, sbX + SCROLLBAR_WIDTH, sbY + sbH, 0x40_00_00_00);
			int thumbH = Math.max(24, sbH * sbH / (contentHeight));
			int thumbY = sbY + (int)((long) scrollOffset * (sbH - thumbH) / Math.max(1, maxScroll));
			context.fill(sbX, thumbY, sbX + SCROLLBAR_WIDTH, thumbY + thumbH, 0xFF_60_60_60);
		}
	}

	private void drawTab(DrawContext context, int index, int x, int y, Text label) {
		boolean active = (activeTab == index);
		int color = active ? ACCENT_COLOR : 0xFF_88_88_88;
		int bg = active ? 0x30_2D_D4_9E : 0x20_40_40_40;
		int w = 72;
		int h = 22;
		context.fill(x, y, x + w, y + h, bg);
		context.drawText(this.textRenderer, label, x + (w - this.textRenderer.getWidth(label)) / 2, y + (h - 8) / 2, color, false);
	}

	@Override
	public boolean mouseClicked(net.minecraft.client.gui.Click click, boolean doubled) {
		double mouseX = click.x();
		double mouseY = click.y();
		// Tab-klikk
		int tabW = 72;
		int tabY = TAB_ROW - 20;
		if (mouseY >= tabY && mouseY <= tabY + 22) {
			if (mouseX >= SIDEBAR_WIDTH + 12 && mouseX < SIDEBAR_WIDTH + 12 + tabW) { activeTab = 0; return true; }
			if (mouseX >= SIDEBAR_WIDTH + 12 + tabW + 4 && mouseX < SIDEBAR_WIDTH + 12 + (tabW + 4) * 2) { activeTab = 1; return true; }
			if (mouseX >= SIDEBAR_WIDTH + 12 + (tabW + 4) * 2 && mouseX < SIDEBAR_WIDTH + 12 + (tabW + 4) * 3) { activeTab = 2; return true; }
		}
		return super.mouseClicked(click, doubled);
	}

	private record PositionedCard(ModCardWidget widget, int baseX, int baseY, String category) {}
}
