package org.openredstone;

import org.bukkit.plugin.java.JavaPlugin;
import org.openredstone.events.PluginMessageEvent;

public class FungeeCommandsExecutor extends JavaPlugin {

    public static String channel = "fun:commands";
    public static String subChannel = "dispatcher";
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        checkIfBungee();

        this.plugin = this;

        if ( !getServer().getPluginManager().isPluginEnabled(this) )
        {
            return;
        }

        getServer().getMessenger().registerIncomingPluginChannel( this, channel, new PluginMessageEvent()); // we register the incoming channel
        getLogger().info("Dispatcher enabled successfully.");
    }

    private void checkIfBungee() {

        if (!getServer().spigot().getConfig().getBoolean("settings.bungeecord")) {
            getLogger().severe("This server is not BungeeCord.");
            getLogger().severe("If the server is already hooked to BungeeCord, please enable it into your spigot.yml aswell.");
            getLogger().severe("Plugin disabled!");
            getServer().getPluginManager().disablePlugin(this);
        }

    }
}
