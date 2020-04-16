package net.TntClient.modules.render;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.Collection;

public class ShowPotions extends Module {

    public ShowPotions() {
        super("Show Potions", Category.RENDER, false);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(Event2D event) {
        final Collection<PotionEffect> potions = mc.thePlayer.getActivePotionEffects();
        if(!potions.isEmpty()) {
            int j = (int) ((event.getHeight() - potions.size() * 33) / 2);

            for (PotionEffect potioneffect : potions) {
                final Potion potion = Potion.potionTypes[potioneffect.getPotionID()];

                String s1 = I18n.format(potion.getName());
                if (potioneffect.getAmplifier() == 1) {
                    s1 = s1 + " " + I18n.format("enchantment.level.2");
                } else if (potioneffect.getAmplifier() == 2) {
                    s1 = s1 + " " + I18n.format("enchantment.level.3");
                } else if (potioneffect.getAmplifier() == 3) {
                    s1 = s1 + " " + I18n.format("enchantment.level.4");
                }

                mc.fontRendererObj.drawStringWithShadow(s1, 5 + 10 + 18, j - 1, 16777215);
                mc.fontRendererObj.drawStringWithShadow(Potion.getDurationString(potioneffect), 5 + 10 + 18, j + 9, 0xafafaf);

                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(GuiContainer.inventoryBackground);

                if (potion.hasStatusIcon()) {
                    int i1 = potion.getStatusIconIndex();
                    drawTexturedModalRect(5 + 6, j, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                }
                j += 33;
            }
        }
    }

    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0).tex((float) (textureX) * f, (float) (textureY + height) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0).tex((float) (textureX + width) * f, (float) (textureY + height) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0).tex((float) (textureX + width) * f, (float) (textureY) * f1).endVertex();
        worldrenderer.pos(x, y, 0).tex((float) (textureX) * f, (float) (textureY) * f1).endVertex();
        tessellator.draw();
    }
}
