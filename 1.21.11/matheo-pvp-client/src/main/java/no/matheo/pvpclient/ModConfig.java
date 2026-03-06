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
}
