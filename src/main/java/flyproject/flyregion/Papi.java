package flyproject.flyregion;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Papi extends PlaceholderExpansion {
    

    @Override
    public String getAuthor() {
        return "FlyProject";
    }

    @Override
    public String getIdentifier() {
        return "flyregion";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        Plugin plugin = flyproject.flyregion.FlyRegion.getProvidingPlugin(flyproject.flyregion.FlyRegion.class);
        File config = new File(flyproject.flyregion.FlyRegion.getPlugin(flyproject.flyregion.FlyRegion.class).getDataFolder(),"config.yml");
        FileConfiguration conf = YamlConfiguration.loadConfiguration(config);
        for (String key : conf.getConfigurationSection("region").getKeys(false)){
            double x1 = conf.getDouble("region." + key + ".pos1.x");
            double z1 = conf.getDouble("region." + key + ".pos1.z");
            double x2 = conf.getDouble("region." + key + ".pos2.x");
            double z2 = conf.getDouble("region." + key + ".pos2.z");
            Location loc1 = new Location(plugin.getServer().getWorld(conf.getString("region." + key + ".world")),x1,0.0,z1);
            Location loc2 = new Location(plugin.getServer().getWorld(conf.getString("region." + key + ".world")),x2,256.0,z2);
            if (getBlocks(loc1,loc2).contains(player.getPlayer().getLocation().getBlock())){
                if (params.equalsIgnoreCase(key)){
                    return ChatColor.translateAlternateColorCodes('&',conf.getString("region." + key + ".name"));
                }
            }
        }
        String world = "%multiverse_world_alias%";
        world = PlaceholderAPI.setPlaceholders(player,world);
        return world; // Placeholder is unknown by the expansion
    }
    public List<Block> getBlocks(Location pos1, Location pos2)
    {
        if(pos1.getWorld() != pos2.getWorld())
            return null;
        World world = pos1.getWorld();
        List<Block> blocks = new ArrayList<>();
        int x1 = pos1.getBlockX();
        int z1 = pos1.getBlockZ();

        int x2 = pos2.getBlockX();
        int z2 = pos2.getBlockZ();

        int lowestX = Math.min(x1, x2);
        int lowestZ = Math.min(z1, z2);

        int highestX = lowestX == x1 ? x2 : x1;
        int highestZ = lowestX == z1 ? z2 : z1;

        for(int x = lowestX; x <= highestX; x++)
            for(int y = 0; y <= 256; y++)
                for(int z = lowestZ; z <= highestZ; z++)
                    blocks.add(world.getBlockAt(x, y, z));
        return blocks;
    }
}
