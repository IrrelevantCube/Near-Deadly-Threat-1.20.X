package net.irrelevent.neardeadlythreat.world.biome;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.world.biome.surface.ModMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register (new ModOverworldRegions (new Identifier (NearDeadlyThreat.MOD_ID, "overworld"), RegionType.OVERWORLD, 5));

        SurfaceRuleManager.addSurfaceRules (SurfaceRuleManager.RuleCategory.OVERWORLD, NearDeadlyThreat.MOD_ID, ModMaterialRules.makeRules());
    }
}

