package dev.tamago0314.tamagotweaks.config;

import dev.tamago0314.tamagotweaks.ToggleKey;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.client.util.InputUtil;

public class KeyConfigScreen extends Screen {

    private ButtonWidget keyButton;

    public KeyConfigScreen(Screen parent) {
        super(Text.literal("Tamago Tweaks Keybind"));
        this.parent = parent;
    }

    private final Screen parent;

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        keyButton = ButtonWidget.builder(
                getKeyText(),
                button -> this.waitingForKey = true
        ).dimensions(centerX - 100, centerY - 10, 200, 20).build();

        this.addDrawableChild(keyButton);

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                button -> this.client.setScreen(parent)
        ).dimensions(centerX - 100, centerY + 20, 200, 20).build());
    }

    private boolean waitingForKey = false;

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (waitingForKey) {
            KeyBinding key = ToggleKey.KEY;
            key.setBoundKey(InputUtil.fromKeyCode(keyCode, scanCode));
            KeyBinding.updateKeysByCode();
            keyButton.setMessage(getKeyText());
            waitingForKey = false;
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private Text getKeyText() {
        return Text.literal("Toggle Key: " + ToggleKey.KEY.getBoundKeyLocalizedText().getString());
    }
}