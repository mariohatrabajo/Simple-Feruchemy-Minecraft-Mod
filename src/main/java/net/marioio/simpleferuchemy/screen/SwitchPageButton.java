package net.marioio.simpleferuchemy.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;

public class SwitchPageButton extends ImageButton {
    private boolean enabled = true;
    private final ResourceLocation texture;
    // Coords inside the  texture
    private final int texUEnabled;
    private final int texUDisabled;
    private final int texV;

    public SwitchPageButton(int x, int y, int width, int height,
                      ResourceLocation texture,
                      int texUEnabled, int texUDisabled, int texV,
                      OnPress onPress) {
        super(x, y, width, height,
                texUEnabled, texV,  // U, V coords for enabled state
                height,
                texture,
                onPress);
        this.texture = texture;
        this.texUEnabled = texUEnabled;
        this.texUDisabled = texUDisabled;
        this.texV = texV;
    }

    public void setEnabled(boolean value) {
        this.enabled = value;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        int u = this.isEnabled() ? texUEnabled : texUDisabled;
        pGuiGraphics.blit(texture, this.getX(), this.getY(), u, texV, this.width, this.height, 36, 20);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!active) return false;
        this.setFocused(false);
        return super.mouseClicked(mouseX, mouseY, button);
    }
    @Override
    public void setFocused(boolean focused) {
        super.setFocused(false);
    }
}
