package dev.tamago0314.tamagotweaks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;

public class KeyTick {

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ToggleKey.KEY.wasPressed()) {
                ToggleKey.ENABLED = !ToggleKey.ENABLED;

                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal("MossUse: " + (ToggleKey.ENABLED ? "ON" : "OFF")),
                            true
                    );
                }
            }
        });
    }
}