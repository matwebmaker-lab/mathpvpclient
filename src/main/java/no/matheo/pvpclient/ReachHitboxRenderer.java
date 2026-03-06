package no.matheo.pvpclient;

/**
 * Tegner rød hitbox rundt spillere som er innenfor slåavstand (reach).
 * Midlertidig deaktivert for 1.21.11 – Fabric API WorldRenderEvents/RenderLayer
 * har endret seg; kan reaktiveres når riktig API er funnet.
 */
public class ReachHitboxRenderer {

	public static void register() {
		// TODO 1.21.11: Bruk ny WorldRenderEvents/RenderLayer API for rød hitbox
	}
}
