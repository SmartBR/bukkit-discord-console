package net.smart.console.discord.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.smart.console.BukkitDiscordConsole;
import net.smart.console.discord.precommand.executor.DiscordCommandExecutor;

public class PingCMD extends DiscordCommandExecutor {

    @Override
    @Command(name = "ping", description = "Verificar a latência do bot.")
    public void execute(BukkitDiscordConsole plugin, User user, Message message, String label, String[] args) {
        long ping = message.getJDA().getGatewayPing();

        message.getChannel().sendMessage(":ping_pong: **|** " + ping + ", **Pong!** " + ping + "ms").queue();
    }
}
