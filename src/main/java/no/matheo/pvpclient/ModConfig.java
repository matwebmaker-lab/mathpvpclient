package no.matheo.pvpclient;

/**
 * Klientinnstillinger for Matheo PvP Client.
 * Verdier lagres i minnet (kan utvides til fil senere).
 */
public class ModConfig {

	private static boolean fullbright = false;
	private static double savedGamma = 1.0;
	private static boolean tntTimer = true;
	private static boolean reachHitbox = true;

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
}
