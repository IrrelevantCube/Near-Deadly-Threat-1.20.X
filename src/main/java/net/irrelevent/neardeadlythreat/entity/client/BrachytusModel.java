// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.irrelevent.neardeadlythreat.entity.client;

import net.irrelevent.neardeadlythreat.entity.animation.ModAnimations;
import net.irrelevent.neardeadlythreat.entity.custom.BrachytusEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BrachytusModel<T extends BrachytusEntity> extends SinglePartEntityModel<T> {
	private final ModelPart brachytus;
	private final ModelPart head;

	public BrachytusModel(ModelPart root) {
		this.brachytus = root.getChild("brachytus.json");
		this.head = brachytus.getChild ("head");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData brachytus = modelPartData.addChild("brachytus.json", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData leg8 = brachytus.addChild("leg8", ModelPartBuilder.create(), ModelTransform.of(15.0F, -12.0275F, 12.4829F, 0.0F, 0.48F, 0.0F));

		ModelPartData cube_r1 = leg8.addChild("cube_r1", ModelPartBuilder.create().uv(40, 40).cuboid(-1.0F, -0.376F, -2.2704F, 2.0F, 7.25F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.0547F, 5.25F, 3.3537F, 3.1416F, 0.0F, -3.1416F));

		ModelPartData cube_r2 = leg8.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -7.3787F, -2.7583F, 3.0F, 8.25F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.0547F, 5.25F, 3.3537F, 2.3998F, 0.0F, -3.1416F));

		ModelPartData leg7 = brachytus.addChild("leg7", ModelPartBuilder.create(), ModelTransform.pivot(9.5F, -12.0F, 13.0F));

		ModelPartData cube_r3 = leg7.addChild("cube_r3", ModelPartBuilder.create().uv(34, 78).cuboid(-1.0F, -0.376F, -2.2704F, 2.0F, 7.25F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.2225F, 2.4829F, 3.1416F, 0.0F, -3.1416F));

		ModelPartData cube_r4 = leg7.addChild("cube_r4", ModelPartBuilder.create().uv(25, 0).cuboid(-1.5F, -7.3787F, -2.7583F, 3.0F, 8.25F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.2225F, 2.4829F, 2.3998F, 0.0F, -3.1416F));

		ModelPartData leg6 = brachytus.addChild("leg6", ModelPartBuilder.create(), ModelTransform.pivot(3.25F, -12.0F, 13.5F));

		ModelPartData cube_r5 = leg6.addChild("cube_r5", ModelPartBuilder.create().uv(10, 87).cuboid(-1.0F, -0.376F, -2.2704F, 2.0F, 7.25F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.2225F, 1.9829F, 3.1416F, 0.0F, -3.1416F));

		ModelPartData cube_r6 = leg6.addChild("cube_r6", ModelPartBuilder.create().uv(34, 8).cuboid(-1.5F, -7.3787F, -2.7583F, 3.0F, 8.25F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.2225F, 1.9829F, 2.3998F, 0.0F, -3.1416F));

		ModelPartData leg5 = brachytus.addChild("leg5", ModelPartBuilder.create(), ModelTransform.of(-3.25F, -12.0275F, 12.7329F, 0.0F, -0.5236F, 0.0F));

		ModelPartData cube_r7 = leg5.addChild("cube_r7", ModelPartBuilder.create().uv(32, 87).cuboid(-1.0F, -0.376F, -2.2704F, 2.0F, 7.25F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.375F, 5.25F, 2.3816F, 3.1416F, 0.0F, -3.1416F));

		ModelPartData cube_r8 = leg5.addChild("cube_r8", ModelPartBuilder.create().uv(0, 78).cuboid(-1.5F, -7.3787F, -2.7583F, 3.0F, 8.25F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.375F, 5.25F, 2.3816F, 2.3998F, 0.0F, -3.1416F));

		ModelPartData leg4 = brachytus.addChild("leg4", ModelPartBuilder.create().uv(110, 0).cuboid(-1.4175F, 4.874F, -5.4935F, 2.0F, 7.25F, 2.0F, new Dilation(0.0F)), ModelTransform.of(15.25F, -12.0275F, -17.2671F, 0.0F, -0.5236F, 0.0F));

		ModelPartData cube_r9 = leg4.addChild("cube_r9", ModelPartBuilder.create().uv(22, 78).cuboid(-1.0F, -4.5F, -1.5F, 3.0F, 8.25F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.9175F, 2.2775F, -2.206F, -0.7418F, 0.0F, 0.0F));

		ModelPartData leg3 = brachytus.addChild("leg3", ModelPartBuilder.create().uv(110, 9).cuboid(-1.0F, 4.874F, -5.2704F, 2.0F, 7.25F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(9.5F, -12.0275F, -17.2671F));

		ModelPartData cube_r10 = leg3.addChild("cube_r10", ModelPartBuilder.create().uv(86, 78).cuboid(-1.0F, -4.5F, -1.5F, 3.0F, 8.25F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 2.2775F, -1.9829F, -0.7418F, 0.0F, 0.0F));

		ModelPartData leg2 = brachytus.addChild("leg2", ModelPartBuilder.create().uv(110, 18).cuboid(-1.0F, 4.874F, -4.7704F, 2.0F, 7.25F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.25F, -12.0275F, -17.7671F));

		ModelPartData cube_r11 = leg2.addChild("cube_r11", ModelPartBuilder.create().uv(107, 78).cuboid(-1.0F, -4.5F, -1.5F, 3.0F, 8.25F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 2.2775F, -1.4829F, -0.7418F, 0.0F, 0.0F));

		ModelPartData leg = brachytus.addChild("leg", ModelPartBuilder.create().uv(86, 110).cuboid(-0.799F, 4.874F, -5.6185F, 2.0F, 7.25F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.75F, -12.0275F, -17.2671F, 0.0F, 0.5236F, 0.0F));

		ModelPartData cube_r12 = leg.addChild("cube_r12", ModelPartBuilder.create().uv(30, 108).cuboid(-1.0F, -4.5F, -1.5F, 3.0F, 8.25F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.299F, 2.2775F, -2.331F, -0.7418F, 0.0F, 0.0F));

		ModelPartData smolclaw1 = brachytus.addChild("smolclaw1", ModelPartBuilder.create(), ModelTransform.pivot(-12.75F, -29.0F, -39.25F));

		ModelPartData cube_r13 = smolclaw1.addChild("cube_r13", ModelPartBuilder.create().uv(0, 124).cuboid(-3.625F, -4.0F, -6.125F, 7.25F, 6.0F, 12.25F, new Dilation(0.0F)), ModelTransform.of(-3.8108F, 0.5F, 5.5146F, 0.1309F, -0.6109F, 0.0F));

		ModelPartData smolclaw2 = brachytus.addChild("smolclaw2", ModelPartBuilder.create(), ModelTransform.pivot(-12.5F, -25.0F, -38.25F));

		ModelPartData cube_r14 = smolclaw2.addChild("cube_r14", ModelPartBuilder.create().uv(0, 142).cuboid(-3.625F, -0.25F, -6.125F, 7.25F, 2.0F, 12.25F, new Dilation(0.0F)), ModelTransform.of(-4.0608F, 0.5F, 4.5146F, -0.1745F, -0.6109F, 0.0F));

		ModelPartData arm2 = brachytus.addChild("arm2", ModelPartBuilder.create().uv(194, 0).cuboid(-4.25F, -2.75F, -10.0F, 8.0F, 6.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -26.5F, -36.0F));

		ModelPartData cube_r15 = arm2.addChild("cube_r15", ModelPartBuilder.create().uv(0, 40).cuboid(-5.875F, -2.5F, -1.25F, 15.25F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-11.875F, 0.25F, -4.75F, 0.0F, 0.4363F, 0.0F));

		ModelPartData bigboiclaw2 = brachytus.addChild("bigboiclaw2", ModelPartBuilder.create().uv(173, 153).cuboid(-6.8125F, 0.0F, -26.9591F, 15.0F, 2.0F, 26.75F, new Dilation(0.0F))
		.uv(110, 38).cuboid(-5.8125F, -4.5F, -26.9591F, 13.0F, 4.5F, 2.0F, new Dilation(0.0F))
		.uv(122, 216).cuboid(-6.8125F, -4.75F, -26.9591F, 1.0F, 4.75F, 26.75F, new Dilation(0.0F))
		.uv(42, 197).cuboid(7.1875F, -4.75F, -26.9591F, 1.0F, 4.75F, 26.75F, new Dilation(0.0F)), ModelTransform.of(-30.0F, -18.25F, 44.125F, 0.0F, 0.3491F, 0.0F));

		ModelPartData bigboiclaw1 = brachytus.addChild("bigboiclaw1", ModelPartBuilder.create().uv(172, 121).cuboid(-8.0173F, -16.75F, -28.7156F, 16.0F, 2.0F, 28.25F, new Dilation(0.0F))
		.uv(216, 58).cuboid(-6.5173F, -14.75F, -2.4656F, 13.0F, 15.25F, 2.0F, new Dilation(0.0F))
		.uv(0, 108).cuboid(-6.5173F, -14.75F, -28.7156F, 13.0F, 10.75F, 2.0F, new Dilation(0.0F))
		.uv(193, 189).cuboid(-8.0173F, -14.75F, -28.7156F, 1.5F, 10.75F, 28.25F, new Dilation(0.0F))
		.uv(48, 124).cuboid(6.4827F, -14.75F, -28.7156F, 1.5F, 10.75F, 28.25F, new Dilation(0.0F)), ModelTransform.of(-29.25F, -18.75F, 44.125F, 0.0F, 0.3491F, 0.0F));

		ModelPartData arm1 = brachytus.addChild("arm1", ModelPartBuilder.create().uv(0, 189).cuboid(-8.5F, -6.5F, 0.0F, 16.0F, 16.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(6.25F, -27.75F, 37.0F));

		ModelPartData cube_r16 = arm1.addChild("cube_r16", ModelPartBuilder.create().uv(149, 181).cuboid(-4.0F, -5.0F, -13.0F, 10.0F, 10.0F, 26.0F, new Dilation(0.0F)), ModelTransform.of(-18.3821F, 4.5F, 7.3029F, 0.0F, -1.8762F, 0.0F));

		ModelPartData head = brachytus.addChild("head", ModelPartBuilder.create().uv(37, 0).cuboid(-15.5F, -1.5F, 9.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 11).cuboid(-15.5F, -1.5F, -12.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-13.5F, -25.5F, -1.0F));

		ModelPartData cube_r17 = head.addChild("cube_r17", ModelPartBuilder.create().uv(0, 17).cuboid(0.25F, -0.25F, -1.0F, 5.0F, 1.75F, 1.75F, new Dilation(0.0F)), ModelTransform.of(-14.0F, 0.0F, -10.5F, 0.0F, -0.6981F, 0.0F));

		ModelPartData cube_r18 = head.addChild("cube_r18", ModelPartBuilder.create().uv(37, 6).cuboid(0.5F, -0.75F, -0.75F, 5.0F, 1.75F, 1.75F, new Dilation(0.0F)), ModelTransform.of(-14.0F, 0.0F, 10.5F, 0.0F, 0.6981F, 0.0F));

		ModelPartData cube_r19 = head.addChild("cube_r19", ModelPartBuilder.create().uv(110, 0).cuboid(-3.0F, -4.75F, -3.0F, 28.0F, 10.5F, 28.0F, new Dilation(0.0F))
		.uv(88, 149).cuboid(-3.5F, 5.0F, -3.5F, 28.5F, 2.0F, 28.5F, new Dilation(0.0F)), ModelTransform.of(-14.5F, 1.25F, 0.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData cube_r20 = head.addChild("cube_r20", ModelPartBuilder.create().uv(132, 50).cuboid(-3.5F, 1.0F, -3.5F, 28.25F, 4.25F, 28.25F, new Dilation(0.0F))
		.uv(150, 82).cuboid(-3.5F, -1.0F, -3.5F, 28.5F, 2.0F, 28.5F, new Dilation(0.0F)), ModelTransform.of(-14.5F, -5.5F, 0.0F, 0.0F, 0.7854F, -0.2182F));

		ModelPartData body = brachytus.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-15.0F, -14.75F, -26.0F, 30.0F, 28.25F, 50.0F, new Dilation(0.0F))
		.uv(75, 179).cuboid(-12.0F, -14.5F, 24.0F, 24.0F, 26.0F, 13.0F, new Dilation(0.0F))
		.uv(216, 28).cuboid(-10.0F, -11.5F, -36.0F, 20.0F, 20.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 124).cuboid(-19.0F, -13.5F, -21.0F, 4.0F, 25.0F, 40.0F, new Dilation(0.0F))
		.uv(88, 84).cuboid(15.0F, -13.5F, -21.0F, 11.0F, 25.0F, 40.0F, new Dilation(0.0F))
		.uv(0, 78).cuboid(-10.75F, -19.25F, -21.0F, 22.0F, 4.5F, 42.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -25.5F, 0.0F));

		ModelPartData cube_r21 = body.addChild("cube_r21", ModelPartBuilder.create().uv(56, 227).cuboid(-7.0F, -3.5F, -10.25F, 14.0F, 7.0F, 17.25F, new Dilation(0.0F)), ModelTransform.of(0.0F, -19.75F, 1.0F, 0.0F, 0.0F, 0.3491F));

		ModelPartData cube_r22 = body.addChild("cube_r22", ModelPartBuilder.create().uv(0, 0).cuboid(-1.375F, -10.875F, -9.0F, 3.75F, 21.75F, 19.25F, new Dilation(0.0F)), ModelTransform.of(-0.1042F, -22.3218F, -0.3743F, 1.2918F, -0.5687F, -1.3148F));

		ModelPartData cube_r23 = body.addChild("cube_r23", ModelPartBuilder.create().uv(0, 78).cuboid(-1.0F, -1.875F, -18.875F, 2.0F, 12.75F, 18.75F, new Dilation(0.0F)), ModelTransform.of(1.0F, -25.375F, 9.875F, 0.7664F, -1.0609F, 0.1556F));

		ModelPartData cube_r24 = body.addChild("cube_r24", ModelPartBuilder.create().uv(86, 78).cuboid(-1.0F, -4.875F, -17.875F, 2.0F, 15.75F, 17.75F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -25.375F, -8.375F, 1.4967F, -0.7128F, -0.9143F));
		return TexturedModelData.of(modelData, 512, 512);
	}
	@Override
	public void setAngles(BrachytusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart ().traverse ().forEach (ModelPart::resetTransform);
		this.setHeadAngles (netHeadYaw, headPitch);

		this.animateMovement (ModAnimations.BRACHYTUS_WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation (entity.idleAnimationState, ModAnimations.BRACHYTUS_IDLE, ageInTicks, 1f);
		this.updateAnimation (entity.attackAnimationState, ModAnimations.BRACHYTUS_ATTACK, ageInTicks, 1f);
		this.updateAnimation (entity.attackAnimationState, ModAnimations.BRACHYTUS_MAGIC, ageInTicks, 1f);
	}
	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 30f);
		headPitch = MathHelper.clamp (headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw = 0.017453292F;
		this.head.pitch = headPitch = 0.01745329F;
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		brachytus.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return brachytus;
	}
}