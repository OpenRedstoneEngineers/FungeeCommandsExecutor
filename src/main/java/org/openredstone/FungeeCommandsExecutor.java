package org.openredstone;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.openredstone.messaging.Message;
import org.openredstone.messaging.MessageExecutor;

public class FungeeCommandsExecutor extends JavaPlugin implements PluginMessageListener {

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

        getServer().getMessenger().registerIncomingPluginChannel( this, channel, this ); // we register the incoming channel
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

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equalsIgnoreCase(channel)) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput( bytes );
        String subChannel = in.readUTF();

        if (!subChannel.equalsIgnoreCase(subChannel)) {
            return;
        }

        String data = in.readUTF();

        try {
            Message message = new Message(data);
            MessageExecutor.execute(message);
        } catch (Exception e) {
            getLogger().warning(e.getMessage());
        }

    }
}
