package cc.isotopestudio.autocommand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoCommand extends JavaPlugin {

    private static final String pluginName = "AutoCommand";
    static AutoCommand plugin;
    static PluginFile config;

    @Override
    public void onEnable() {
        plugin = this;
        config = new PluginFile(this, "config.yml", "config.yml");
        config.setEditable(false);

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

        getLogger().info(pluginName + "�ɹ�����!");
        getLogger().info(pluginName + "��ISOTOPE Studio����!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "�ɹ�ж��!");
    }

}
