package no.matheo.pvpclient;

/**
 * Klientinnstillinger for Matheo PvP Client.
 * Verdier lagres i minnet (kan utvides til fil senere).
 */
public class ModConfig {

	private static boolean autoSprint = true;
	private static boolean fullbright = false;
	private static double savedGamma = 1.0;
	private static boolean tntTimer = true;
	private static boolean reachHitbox = true;
	private static boolean showFps = true;
	private static boolean showCoords = false;
	private static boolean showArmor = true;

	public static boolean isAutoSprint() {
		return autoSprint;
	}

	public static void setAutoSprint(boolean enabled) {
		autoSprint = enabled;
	}

	public static void toggleAutoSprint() {
		autoSprint = !autoSprint;
	}

	public static boolean isFullbright() {
		return fullbright;
	}

	public static void setFullbright(boolean enabled) {
		fullbright = enabled;
	}

	public static void toggleFullbright() {
		fullbright = !fullbright;
	}

	public static double getSavedGamma() {
		return savedGamma;
	}

	public static void setSavedGamma(double gamma) {
		savedGamma = gamma;
	}

	public static boolean isTntTimer() {
		return tntTimer;
	}

	public static void setTntTimer(boolean enabled) {
		tntTimer = enabled;
	}

	public static void toggleTntTimer() {
		tntTimer = !tntTimer;
	}

	public static boolean isReachHitbox() {
		return reachHitbox;
	}

	public static void setReachHitbox(boolean enabled) {
		reachHitbox = enabled;
	}

	public static void toggleReachHitbox() {
		reachHitbox = !reachHitbox;
	}

	public static boolean isShowFps() {
		return showFps;
	}

	public static void setShowFps(boolean enabled) {
		showFps = enabled;
	}

	public static void toggleShowFps() {
		showFps = !showFps;
	}

	public static boolean isShowCoords() {
		return showCoords;
	}

	public static void setShowCoords(boolean enabled) {
		showCoords = enabled;
	}

	public static void toggleShowCoords() {
		showCoords = !showCoords;
	}

	public static boolean isShowArmor() {
		return showArmor;
	}

	public static void setShowArmor(boolean enabled) {
		showArmor = enabled;
	}

	public static void toggleShowArmor() {
		showArmor = !showArmor;
	}

	// HUD-posisjoner (flyttbare elementer)
	private static int fpsX = 6, fpsY = 6;
	private static int coordsX = 6, coordsY = 18;
	private static int tntX = 6, tntY = 30;
	private static int armorX = 6, armorY = 42;

	public static int getFpsX() { return fpsX; }
	public static void setFpsX(int x) { fpsX = x; }
	public static int getFpsY() { return fpsY; }
	public static void setFpsY(int y) { fpsY = y; }
	public static int getCoordsX() { return coordsX; }
	public static void setCoordsX(int x) { coordsX = x; }
	public static int getCoordsY() { return coordsY; }
	public static void setCoordsY(int y) { coordsY = y; }
	public static int getTntX() { return tntX; }
	public static void setTntX(int x) { tntX = x; }
	public static int getTntY() { return tntY; }
	public static void setTntY(int y) { tntY = y; }
	public static int getArmorX() { return armorX; }
	public static void setArmorX(int x) { armorX = x; }
	public static int getArmorY() { return armorY; }
	public static void setArmorY(int y) { armorY = y; }
}
