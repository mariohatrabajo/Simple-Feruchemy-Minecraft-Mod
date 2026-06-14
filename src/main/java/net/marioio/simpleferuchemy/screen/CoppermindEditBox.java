package net.marioio.simpleferuchemy.screen;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import org.lwjgl.glfw.GLFW;

public class CoppermindEditBox extends EditBox {
    protected final Font font;
    private static final int MARGIN = 10;
    private static int t = 0;
    private static boolean showing_cursor = true;
    private static final int CURSOR_DURATION = 20;
    public CoppermindEditBox(Font pFont, int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pFont, pX, pY, pWidth, pHeight, pMessage);
        this.font = pFont;
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        String text = this.getValue();
        if(showing_cursor){
            if(text.isEmpty()){
                text = "_";
            }else {
                int pos = this.getCursorPosition();
                text = text.substring(0, pos) + "_" + ((pos < text.length())? text.substring(pos+1) : "");
            }
        }

        pGuiGraphics.drawWordWrap(this.font, FormattedText.of(text), this.getX()+MARGIN, this.getY()+MARGIN,this.getWidth()-MARGIN*2, 0);
    }

    @Override
    public void tick() {
        super.tick();
        t++;
        if(t >= CURSOR_DURATION){
            t = 0;
            showing_cursor = !showing_cursor;
        }
        if(this.getCursorPosition() == 378){
            this.moveCursorTo(377);
        }

    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        if (keyCode == GLFW.GLFW_KEY_UP){
            this.moveCursorTo(this.getCursorPosition()-21);
            return true;
        }else if (keyCode == GLFW.GLFW_KEY_DOWN){
            this.moveCursorTo(this.getCursorPosition()+21);
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER) {
            //this.onEnterPressed();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void onEnterPressed(){
        String text = this.getValue();
        int charsInLine = text.length()%21;
        for(int i=0; i < 21-charsInLine; i++){
            text += " ";
        }
        this.setValue(text);
    }

}
