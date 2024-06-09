// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.irrelevent.neardeadlythreat.entity.client;

import net.irrelevent.neardeadlythreat.entity.animation.ModAnimations;
import net.irrelevent.neardeadlythreat.entity.custom.BrachymiteEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BrachymiteModel <T extends BrachymiteEntity> extends SinglePartEntityModel<T> {
	private final ModelPart brachymite;
	private final ModelPart head;

	public BrachymiteModel(ModelPart root) {
		this.brachymite = root.getChild ("brachymite");

		this.head = brachymite.getChild ("body").getChild ("torso").getChild ("head");

	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData ();
		ModelPartData modelPartData = modelData.getRoot ();
		ModelPartData brachymite = modelPartData.addChild ("brachymite", ModelPartBuilder.create (), ModelTransform.pivot (0.0F, 24.0F, 0.0F));

		ModelPartData body = brachymite.addChild ("body", ModelPartBuilder.create (), ModelTransform.pivot (0.0F, 0.0F, 0.0F));

		ModelPartData torso = body.addChild ("torso", ModelPartBuilder.create ().uv (0, 0).cuboid (-7.5F, -12.75F, -3.25F, 15.0F, 10.0F, 9.0F, new Dilation (0.0F)), ModelTransform.pivot (0.0F, 0.0F, 0.0F));

		ModelPartData head = torso.addChild ("head", ModelPartBuilder.create (), ModelTransform.pivot (0.0F, -7.875F, -3.25F));

		ModelPartData cube_r1 = head.addChild ("cube_r1", ModelPartBuilder.create ().uv (0, 4).cuboid (-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation (0.0F)), ModelTransform.of (2.953F, 0.75F, -4.0852F, 0.0F, -0.6545F, 0.0F));

		ModelPartData cube_r2 = head.addChild ("cube_r2", ModelPartBuilder.create ().uv (0, 0).cuboid (-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation (0.0F)), ModelTransform.of (-3.0761F, 0.75F, -3.8423F, 0.0F, 0.6545F, 0.0F));

		ModelPartData cube_r3 = head.addChild ("cube_r3", ModelPartBuilder.create ().uv (28, 21).cuboid (-3.5F, -2.5F, -4.5F, 8.0F, 5.0F, 8.0F, new Dilation (0.0F)), ModelTransform.of (0.0F, -0.1421F, 1.1684F, 0.3038F, 0.762F, 0.2132F));

		ModelPartData cube_r4 = head.addChild ("cube_r4", ModelPartBuilder.create ().uv (0, 19).cuboid (-4.5F, -0.5F, -4.5F, 9.0F, 1.0F, 9.0F, new Dilation (0.0F)), ModelTransform.of (0.0F, -2.3533F, -0.2825F, 0.339F, 0.7561F, 0.2374F));

		ModelPartData right_arm = torso.addChild ("right_arm", ModelPartBuilder.create ().uv (39, 0).cuboid (-4.875F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation (0.0F)), ModelTransform.pivot (-7.5F, -7.875F, 1.25F));

		ModelPartData cube_r5 = right_arm.addChild ("cube_r5", ModelPartBuilder.create ().uv (38, 34).cuboid (-4.0F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new Dilation (0.0F)), ModelTransform.of (-4.875F, -1.5F, 0.5F, 0.0F, 0.0F, 1.5708F));

		ModelPartData claw11 = right_arm.addChild ("claw11", ModelPartBuilder.create ().uv (16, 30).cuboid (-4.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation (0.0F)), ModelTransform.of (-4.25F, -5.375F, 0.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData claw12 = right_arm.addChild ("claw12", ModelPartBuilder.create ().uv (0, 39).cuboid (0.0F, -6.0F, -2.0F, 1.0F, 6.0F, 4.0F, new Dilation (0.0F)), ModelTransform.of (-4.25F, -5.375F, 0.0F, 0.0F, 0.0F, 0.2182F));

		ModelPartData left_arm = torso.addChild ("left_arm", ModelPartBuilder.create ().uv (38, 36).cuboid (-0.125F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation (0.0F)), ModelTransform.pivot (7.5F, -7.875F, 1.25F));

		ModelPartData cube_r6 = left_arm.addChild ("cube_r6", ModelPartBuilder.create ().uv (27, 19).cuboid (-4.0F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new Dilation (0.0F)), ModelTransform.of (3.875F, -1.5F, 0.5F, 0.0F, 0.0F, 1.5708F));

		ModelPartData claw21 = left_arm.addChild ("claw21", ModelPartBuilder.create ().uv (0, 29).cuboid (-4.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation (0.0F)), ModelTransform.of (4.375F, -5.375F, 0.0F, 3.1416F, 0.0F, -2.9671F));

		ModelPartData claw22 = left_arm.addChild ("claw22", ModelPartBuilder.create ().uv (32, 34).cuboid (-1.0F, -6.0F, -2.0F, 1.0F, 6.0F, 4.0F, new Dilation (0.0F)), ModelTransform.of (4.375F, -5.375F, 0.0F, 0.0F, 0.0F, -0.2182F));

		ModelPartData leg1 = torso.addChild ("leg1", ModelPartBuilder.create (), ModelTransform.pivot (-7.5182F, -3.2571F, -1.75F));

		ModelPartData cube_r7 = leg1.addChild ("cube_r7", ModelPartBuilder.create ().uv (39, 2).cuboid (0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation (0.0F)), ModelTransform.of (-0.4818F, 0.2571F, -0.5F, 0.0F, 0.0F, 0.48F));

		ModelPartData leg2 = torso.addChild ("leg2", ModelPartBuilder.create (), ModelTransform.pivot (-8.0182F, -3.2571F, 1.5F));

		ModelPartData cube_r8 = leg2.addChild ("cube_r8", ModelPartBuilder.create ().uv (31, 21).cuboid (0.1154F, -0.7782F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation (0.0F)), ModelTransform.of (0.0182F, 0.0071F, 0.0F, 0.0F, 0.0F, 0.48F));

		ModelPartData leg3 = torso.addChild ("leg3", ModelPartBuilder.create (), ModelTransform.pivot (-8.0182F, -3.2571F, 4.75F));

		ModelPartData cube_r9 = leg3.addChild ("cube_r9", ModelPartBuilder.create ().uv (16, 29).cuboid (0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation (0.0F)), ModelTransform.of (0.0182F, 0.2571F, -0.5F, 0.0F, 0.0F, 0.48F));

		ModelPartData leg4 = torso.addChild ("leg4", ModelPartBuilder.create (), ModelTransform.of (7.9818F, -3.3821F, -1.75F, 0.0F, 3.1416F, 0.0F));

		ModelPartData cube_r10 = leg4.addChild ("cube_r10", ModelPartBuilder.create ().uv (27, 21).cuboid (0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation (0.0F)), ModelTransform.of (0.0182F, 0.3821F, -0.5F, 0.0F, 0.0F, 0.48F));

		ModelPartData leg5 = torso.addChild ("leg5", ModelPartBuilder.create (), ModelTransform.of (7.9818F, -3.3821F, 1.5F, 0.0F, 3.1416F, 0.0F));

		ModelPartData cube_r11 = leg5.addChild ("cube_r11", ModelPartBuilder.create ().uv (4, 19).cuboid (0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation (0.0F)), ModelTransform.of (0.0182F, 0.3821F, -0.5F, 0.0F, 0.0F, 0.48F));

		ModelPartData leg6 = torso.addChild ("leg6", ModelPartBuilder.create (), ModelTransform.of (7.9818F, -3.3821F, 4.75F, 0.0F, 3.1416F, 0.0F));

		ModelPartData cube_r12 = leg6.addChild ("cube_r12", ModelPartBuilder.create ().uv (0, 19).cuboid (0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation (0.0F)), ModelTransform.of (0.0182F, 0.3821F, -0.5F, 0.0F, 0.0F, 0.48F));
		return TexturedModelData.of (modelData, 64, 64);
	}

	@Override
	public void setAngles(BrachymiteEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart ().traverse ().forEach (ModelPart::resetTransform);
		this.setHeadAngles (netHeadYaw, headPitch);

		this.animateMovement (ModAnimations.WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation (entity.idleAnimationState, ModAnimations.IDLE, ageInTicks, 1f);
		this.updateAnimation (entity.attackAnimationState, ModAnimations.ATTACK, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp (headYaw, -30.0f, 30f);
		headPitch = MathHelper.clamp (headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw = 0.017453292F;
		this.head.pitch = headPitch = 0.01745329F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		brachymite.render (matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return brachymite;
	}

}