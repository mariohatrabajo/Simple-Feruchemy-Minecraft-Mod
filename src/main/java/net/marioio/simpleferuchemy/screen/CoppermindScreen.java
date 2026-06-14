package net.marioio.simpleferuchemy.screen;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.marioio.simpleferuchemy.network.UpdateBookTextPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CoppermindScreen extends Screen {
    /**TODO
     * Oscurecer el fondo
     * Nº de caracteres = durabilidad o durabilidad/2
     * que al darle para abajo no salga del focus
     * No guarda todas las paginas
     */
    private final ItemStack bookStack;
    private final int bookSlot;
    private List<EditBox> textBoxs = new ArrayList<>();
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(SimpleFeruchemy.MOD_ID, "textures/gui/coppermind_gui.png");
    private static final float CHARS_PER_PAGE = 378;
    private final int total_chars;
    private int current_page = 0;
    private SwitchPageButton nextBtn;
    private SwitchPageButton prevBtn;

    public CoppermindScreen(ItemStack stack, int slot) {
        super(Component.literal("Coppermind"));
        this.bookStack = stack;
        this.bookSlot = slot;
        this.total_chars = stack.getMaxDamage();
    }


    @Override
    protected void init() {
        int txtWidth = 146, txtHeight = 181;
        int btnWidth = 100, btnHeight = 20, btnY = txtHeight+20;


        // Switch page buttons
        final ResourceLocation BTN_TEXTURE =
                new ResourceLocation(SimpleFeruchemy.MOD_ID, "textures/gui/switch_page_buttons.png");
        int btnMargin = 4;
        prevBtn = new SwitchPageButton(
                (this.width-btnWidth)/2-18-btnMargin, btnY+5, 18, 10,
                BTN_TEXTURE,
                18, 0, 10,
                btn -> switchPage(current_page -1, prevBtn.isEnabled())
        );
        addRenderableWidget(prevBtn);
        nextBtn = new SwitchPageButton(
                (this.width+btnWidth)/2+btnMargin, btnY+5, 18, 10,
                BTN_TEXTURE,
                18, 0, 0,
                btn -> switchPage(current_page +1, nextBtn.isEnabled())
        );
        addRenderableWidget(nextBtn);

        // Writable textboxs
        System.out.println(this.total_chars/CHARS_PER_PAGE);
        for(int i = 0; i < this.total_chars/CHARS_PER_PAGE; i++){
            EditBox textBox = new CoppermindEditBox(this.font, (this.width-txtWidth)/2, 10, txtWidth, txtHeight, Component.empty());
            textBox.setMaxLength((int) CHARS_PER_PAGE);
            textBoxs.add(textBox);
        }
        textBoxs.get(textBoxs.size()-1).setMaxLength((int) (this.total_chars % CHARS_PER_PAGE));
        addTextToEditBoxs();
        switchPage(0, true);

        // "Done" button
        Button doneButton = Button.builder(Component.translatable("gui.done"), btn -> this.onDonePressed())
                .bounds((this.width-btnWidth) / 2, btnY, btnWidth, btnHeight)
                .build();
        this.addRenderableWidget(doneButton);
    }

    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float partialTicks) {
        // Draw background
        int color = 0xAA000000;
        gfx.fill(0, 0, this.width, this.height, color);
        int bgWidth = 146, bgHeight = 181;
        gfx.blit(TEXTURE,(this.width - bgWidth)/2, 10,
                0, 0, bgWidth, bgHeight, bgWidth,bgHeight);

        super.render(gfx, mouseX, mouseY, partialTicks);
        textBoxs.get(current_page).render(gfx, mouseX, mouseY, partialTicks);
        gfx.drawWordWrap(this.font, FormattedText.of(String.valueOf(current_page+1)), this.width/2-((current_page+1 < 10)?3:6), textBoxs.get(current_page).getHeight()+1,20, 0);
    }

    private void addTextToEditBoxs(){
        for(int i = 0; i < textBoxs.size(); i++) {
            // Get saved text
            String text = "";
            if (bookStack.hasTag()) {
                CompoundTag tag = bookStack.getTag();
                if (tag.contains("pages")) {
                    ListTag pages = tag.getList("pages", 8); // 8 = StringTag
                    if (!pages.isEmpty()) {
                        text = pages.getString(i); // primera página
                    }
                }
            }
            textBoxs.get(i).setValue(text);
        }
    }

    @Override
    public void tick() {
        super.tick();
        textBoxs.get(current_page).tick();
    }

    private void switchPage(int newPage, boolean enabled) {
        if(!enabled){
            return;
        }
        // Remove previous widget
        this.removeWidget(textBoxs.get(current_page));

        current_page = newPage;
        // Add the new widget
        this.addRenderableWidget(textBoxs.get(current_page));
        textBoxs.get(current_page).setFocused(true);
        this.setInitialFocus(textBoxs.get(current_page));

        // Enable / Disable buttons
        prevBtn.setEnabled(current_page != 0);
        nextBtn.setEnabled(current_page != textBoxs.size()-1);
    }

    private void onDonePressed(){
        this.saveText();
        assert this.minecraft != null;
        this.minecraft.setScreen(null);
    }

    @Override
    public void onClose() {
        saveText();
        super.onClose();
    }

    private void saveText(){
        List<String> pages = new ArrayList<>();
        for(int i = 0; i < textBoxs.size(); i++){
            pages.add(textBoxs.get(i).getValue());
        }

        // Enviar paquete al servidor
        SimpleFeruchemy.CHANNEL.sendToServer(new UpdateBookTextPacket(this.bookSlot, pages));
    }


}
