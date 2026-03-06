package no.matheo.pvpclient.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import no.matheo.pvpclient.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Deaktivert i matheo-pvp-client.mixins.json for 1.21.11:
 * getGamma() returnerer SimpleOption&lt;Double&gt;, ikke double, så redirect-målet matcher ikke.
 * Fullbright håndteres i stedet i MatheoPvpClient ved å sette gamma i client-tick.
 */
@Mixin(GameRenderer.class)
public class GameRendererMixin {

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOptions;getGamma()D"))
	private double fullbrightGamma(GameOptions options) {
		return ModConfig.isFullbright() ? 16.0 : options.getGamma().getValue();
	}
}
