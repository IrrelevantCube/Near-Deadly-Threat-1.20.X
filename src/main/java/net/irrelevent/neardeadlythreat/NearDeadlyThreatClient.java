package net.irrelevent.neardeadlythreat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.irrelevent.neardeadlythreat.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class NearDeadlyThreatClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock (ModBlocks.YELLOW_MERANTI_LEAVES, RenderLayer.getCutout ());
        BlockRenderLayerMap.INSTANCE.putBlock (ModBlocks.YELLOW_MERANTI_DOOR, RenderLayer.getCutout ());
        BlockRenderLayerMap.INSTANCE.putBlock (ModBlocks.YELLOW_MERANTI_TRAPDOOR, RenderLayer.getCutout ());

    }
}
