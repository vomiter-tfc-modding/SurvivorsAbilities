package com.vomiter.survivorsabilities.mixin.sense;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.vomiter.survivorsabilities.core.SAEffects;
import com.vomiter.survivorsabilities.core.effect.SenseEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRenderer.class)
public abstract class LevelRenderer_SenseGlowMixin {
    @WrapOperation(
            method = "renderLevel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;shouldEntityAppearGlowing(Lnet/minecraft/world/entity/Entity;)Z")
    )
    private boolean forceGlowing(Minecraft instance, Entity entity, Operation<Boolean> original){
        LocalPlayer viewer = instance.player;
        for (SenseEffect.SenseType senseType : SenseEffect.SenseType.values()) {
            if (viewer != null
                    && viewer.hasEffect(SAEffects.SENSES.get(senseType).get())
                    && entity instanceof LivingEntity le
                    && le.getType().is(SenseEffect.getTargetTag(senseType, SenseEffect.GlowColor.NONE))
                    && le.distanceTo(viewer) < 32
            ) return true;
        }
        return original.call(instance, entity);
    }


    @WrapOperation(
            method =
                    "renderLevel(Lcom/mojang/blaze3d/vertex/PoseStack;FJZ" +
                            "Lnet/minecraft/client/Camera;" +
                            "Lnet/minecraft/client/renderer/GameRenderer;" +
                            "Lnet/minecraft/client/renderer/LightTexture;" +
                            "Lorg/joml/Matrix4f;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OutlineBufferSource;setColor(IIII)V"
            )
    )
    private void colorOutline(
            OutlineBufferSource outline, int r, int g, int b, int a,
            Operation<Void> original,
            PoseStack poseStack, float partialTick, long nanoTime, boolean renderBlockOutline,
            net.minecraft.client.Camera camera, GameRenderer gameRenderer,
            LightTexture lightTexture, Matrix4f projectionMatrix,
            @Local Entity entity
    ) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer viewer = mc.player;
        if(viewer == null) return;
        if(!(entity instanceof LivingEntity le)) return;
        if(!le.isAlive()) return;

        // 預設顏色 = 原本的 r/g/b/a
        int cr = r;
        int cg = g;
        int cb = b;
        int ca = a;

        for (SenseEffect.SenseType senseType : SenseEffect.SenseType.values()) {
            if(!viewer.hasEffect(SAEffects.SENSES.get(senseType).get())) continue;
            for (SenseEffect.GlowColor glowColor : SenseEffect.GlowColor.values()) {
                if(glowColor.equals(SenseEffect.GlowColor.NONE)) continue;
                if(!le.getType().is(SenseEffect.getTargetTag(senseType, glowColor))) continue;
                if(glowColor.equals(SenseEffect.GlowColor.RED)) {
                    cr = 255;
                    cg = 0;
                    cb = 0;
                }
                else if(glowColor.equals(SenseEffect.GlowColor.YELLOW)) {
                    cr = 255;
                    cg = 255;
                    cb = 0;
                }
            }
        }
        original.call(outline, cr, cg, cb, ca);
    }
}
