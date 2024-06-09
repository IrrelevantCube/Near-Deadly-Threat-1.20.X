package net.irrelevent.neardeadlythreat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.irrelevent.neardeadlythreat.entity.ModEntities;
import net.irrelevent.neardeadlythreat.entity.client.BrachymiteModel;
import net.irrelevent.neardeadlythreat.entity.client.BrachytusModel;
import net.irrelevent.neardeadlythreat.entity.client.BrachytusRenderer;
import net.irrelevent.neardeadlythreat.entity.client.BrachymiteRenderer;
import net.irrelevent.neardeadlythreat.entity.client.ModModelLayers;
import net.minecraft.client.render.RenderLayer;

public class NearDeadlyThreatClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock (ModBlocks.YELLOW_MERANTI_LEAVES, RenderLayer.getCutout ());
        BlockRenderLayerMap.INSTANCE.putBlock (ModBlocks.YELLOW_MERANTI_DOOR, RenderLayer.getCutout ());
        BlockRenderLayerMap.INSTANCE.putBlock (ModBlocks.YELLOW_MERANTI_TRAPDOOR, RenderLayer.getCutout ());

        BlockRenderLayerMap.INSTANCE.putBlock (ModBlocks.YELLOW_MERANTI_SAPLING, RenderLayer.getCutout ());
        BlockRenderLayerMap.INSTANCE.putBlock (ModBlocks.FRACTURED_YELLOW_MERANTI_SAPLING, RenderLayer.getCutout ());
        EntityRendererRegistry.register (ModEntities.BRACHYTUS, BrachytusRenderer::new);
        EntityModelLayerRegistry.registerModelLayer (ModModelLayers.BRACHYTUS, BrachytusModel::getTexturedModelData);
        EntityRendererRegistry.register (ModEntities.BRACHYMITE, BrachymiteRenderer::new);
        EntityModelLayerRegistry.registerModelLayer (ModModelLayers.BRACHYMITE, BrachymiteModel::getTexturedModelData);

    }
}
