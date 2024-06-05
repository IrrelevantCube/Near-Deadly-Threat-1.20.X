package net.irrelevent.neardeadlythreat.entity.client;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.NearDeadlyThreatClient;
import net.irrelevent.neardeadlythreat.entity.custom.BrachytusEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BrachytusRenderer extends MobEntityRenderer <BrachytusEntity, BrachytusModel<BrachytusEntity>>{

    private static final Identifier TEXTURE = new Identifier (NearDeadlyThreat.MOD_ID, "textures/entity/itsallcrabbroken.png");
    public BrachytusRenderer(EntityRendererFactory.Context context) {
        super (context, new BrachytusModel<> (context.getPart (ModModelLayers.BRACHYTUS)), 2.5f);
    }

    @Override
    public Identifier getTexture(BrachytusEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BrachytusEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby ()) {
            matrixStack.scale (1f, 1f, 1f);

        }else {
            matrixStack.scale (1f, 1f, 1f);
        }



        super.render (mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
