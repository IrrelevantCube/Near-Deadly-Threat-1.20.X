package net.irrelevent.neardeadlythreat.entity.client;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.entity.custom.BrachymiteEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BrachymiteRenderer extends MobEntityRenderer <BrachymiteEntity, BrachymiteModel<BrachymiteEntity>>{

    private static final Identifier TEXTURE = new Identifier (NearDeadlyThreat.MOD_ID, "textures/entity/vengeancejr.png");
    public BrachymiteRenderer(EntityRendererFactory.Context context) {
        super (context, new BrachymiteModel<> (context.getPart (ModModelLayers.BRACHYMITE)), 2.5f);
    }

    @Override
    public Identifier getTexture(BrachymiteEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BrachymiteEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby ()) {
            matrixStack.scale (0.5f, 0.5f, 0.5f);

        }else {
            matrixStack.scale (0.5f, 0.5f, 0.5f);
        }



        super.render (mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
