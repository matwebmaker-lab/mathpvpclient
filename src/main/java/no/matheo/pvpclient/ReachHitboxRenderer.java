package no.matheo.pvpclient;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Viser rød outline (glow) på spillere innenfor slåavstand.
 * Bruker vanlig entity glow i stedet for custom world rendering (Fabric 1.21.11).
 */
public class ReachHitboxRenderer {

	private static final double MELEE_REACH = 3.0;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.world == null || client.getCameraEntity() == null) return;

			double reachSq = MELEE_REACH * MELEE_REACH;
			Entity camera = client.getCameraEntity();
			boolean featureOn = ModConfig.isReachHitbox();

			for (Entity entity : client.world.getEntities()) {
				if (!(entity instanceof PlayerEntity) || entity == camera || !entity.isAlive()) continue;
				boolean inReach = featureOn && entity.squaredDistanceTo(camera) <= reachSq;
				entity.setGlowing(inReach);
			}
		});
	}
}
