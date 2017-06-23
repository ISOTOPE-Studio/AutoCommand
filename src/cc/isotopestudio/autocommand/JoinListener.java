package cc.isotopestudio.autocommand;
/*
 * Created by Mars Tan on 6/9/2017.
 * Copyright ISOTOPE Studio
 */

import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static cc.isotopestudio.autocommand.AutoCommand.config;
import static cc.isotopestudio.autocommand.AutoCommand.plugin;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ConsoleCommandSender console = Bukkit.getConsoleSender();
                Player player = event.getPlayer();
                if (!player.isOnline()) return;
                if (config.getBoolean("toggle.console"))
                    config.getStringList("console").stream()
                            .map(s -> s.replaceAll("<player>", player.getName()))
                            .forEach(s -> {
                                try {
                                    Bukkit.dispatchCommand(console, s);
                                } catch (CommandException e) {
                                    e.printStackTrace();
                                }
                            });
                if (config.getBoolean("toggle.player"))
                    config.getStringList("player").stream()
                            .map(s -> s.replaceAll("<player>", player.getName()))
                            .forEach(s -> {
                                try {
                                    Bukkit.dispatchCommand(player, s);
                                } catch (CommandException e) {
                                    e.printStackTrace();
                                }
                            });
            }
        }.runTaskLater(plugin, 20);
    }
}
