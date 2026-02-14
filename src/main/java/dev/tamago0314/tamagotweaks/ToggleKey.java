package dev.tamago0314.tamagotweaks;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ToggleKey {

    public static boolean ENABLED = true;
    public static KeyBinding KEY;

    public static void register() {
        KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.mossuse.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "category.mossuse"
        ));
    }
}