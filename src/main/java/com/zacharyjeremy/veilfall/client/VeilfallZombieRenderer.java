package com.zacharyjeremy.veilfall.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

/** Each threat gets a generated, original skin with its own palette and luminous markings. */
public final class VeilfallZombieRenderer extends ZombieRenderer {
    private final ResourceLocation texture;

    public VeilfallZombieRenderer(EntityRendererProvider.Context context, String textureName) {
        super(context);
        this.texture = new ResourceLocation("veilfall", "textures/entity/" + textureName + ".png");
    }

    @Override
    protected void scale(Zombie entity, PoseStack poseStack, float partialTick) {
        super.scale(entity, poseStack, partialTick);
        if ("the_unmaking".equals(texture.getPath().substring(texture.getPath().lastIndexOf('/') + 1, texture.getPath().length() - 4))) {
            poseStack.scale(1.65F, 1.65F, 1.65F);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return texture;
    }
}
