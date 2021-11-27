package flyproject.flyregion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlyRegion extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("\n" +
                "    ________      ____             _           \n" +
                "   / ____/ /_  __/ __ \\___  ____ _(_)___  ____ \n" +
                "  / /_  / / / / / /_/ / _ \\/ __ `/ / __ \\/ __ \\\n" +
                " / __/ / / /_/ / _, _/  __/ /_/ / / /_/ / / / /\n" +
                "/_/   /_/\\__, /_/ |_|\\___/\\__, /_/\\____/_/ /_/ \n" +
                "        /____/           /____/                \n\n" +
                "(c) Copyright 2021 FlyProject");
        // Plugin startup logic
        saveDefaultConfig();
        new Papi().register();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§7[§bFlyRegion§7] §a配置文件已重载");
        reloadConfig();
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
