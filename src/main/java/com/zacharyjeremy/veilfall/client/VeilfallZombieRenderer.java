package com.zacharyjeremy.veilfall.client;

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
    public ResourceLocation getTextureLocation(Zombie entity) {
        return texture;
    }
}
