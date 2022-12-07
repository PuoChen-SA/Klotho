package com.brokendust.klotho.gui;

import com.brokendust.klotho.Klotho;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TestGui extends Screen {

    TranslationTextComponent content = new TranslationTextComponent("gui." + Klotho.modId + ".test");

    ResourceLocation resourceLocation = new ResourceLocation(Klotho.modId , "textures/gui/test_gui.png");

    public TestGui(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(resourceLocation);
        int textureWidth = 208;
        int textureHeight = 156;
        this.blit(matrixStack, this.width / 2 - 150, 10, 0, 0, 300, 200, textureWidth, textureHeight);
        drawCenteredString(matrixStack, this.font, content, this.width / 2 - 10, 30, 0xeb0505);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
