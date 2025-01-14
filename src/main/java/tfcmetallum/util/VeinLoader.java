package tfcmetallum.util;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.apache.commons.io.FileUtils;

import net.dries007.tfc.world.classic.worldgen.vein.VeinRegistry;

import static net.dries007.tfc.api.util.TFCConstants.MOD_ID;

public enum VeinLoader
{
    INSTANCE;

    private static final String DEFAULT_ORE_SPAWN_LOCATION = "assets/tfcmetallum/config/tfc_metallum_ores.json";

    public void preInit(File dir)
    {
        File tfcDir = new File(dir, MOD_ID);
        if (!tfcDir.exists() && !tfcDir.mkdir())
        {
            throw new Error("Problem creating TFC extra config directory.");
        }
        File worldGenFile = new File(tfcDir, "tfc_metallum_ores.json");
        try
        {
            if (worldGenFile.createNewFile())
            {
                FileUtils.copyInputStreamToFile(Objects.requireNonNull(VeinRegistry.class.getClassLoader().getResourceAsStream(DEFAULT_ORE_SPAWN_LOCATION)), worldGenFile);
            }
        }
        catch (IOException e)
        {
            throw new Error("Problem creating default ore vein config file.", e);
        }
    }
}
