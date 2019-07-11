package org.openredstone;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class FungeeCommandsExecutor extends JavaPlugin implements PluginMessageListener {

    public static String channel = "FunCommands";
    public static String subChannel = "Dispatcher";

    @Override
    public void onEnable() {
        checkIfBungee();

        if ( !getServer().getPluginManager().isPluginEnabled(this) )
        {
            return;
        }

        getServer().getMessenger().registerIncomingPluginChannel( this, "my:channel", this ); // we register the incoming channel
        // you can register outgoing channel if you want to send messages to the proxy
        // getServer().getMessenger().registerOutgoingPluginChannel( this, "my:channel" );
        getLogger().info("<pluginName> driver enabled successfully.");
    }

    private void checkIfBungee() {

        if (getServer().spigot().getConfig().getBoolean("settings.bungeecord")) {
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

        getServer().broadcastMessage(data);

    }
}
