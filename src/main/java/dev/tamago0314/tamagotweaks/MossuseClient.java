package dev.tamago0314.tamagotweaks;

import net.fabricmc.api.ClientModInitializer;

public class MossuseClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ToggleKey.register();
        KeyTick.register();
    }
}