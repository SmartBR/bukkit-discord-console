package net.smart.console.discord.precommand.executor;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.smart.console.BukkitDiscordConsole;

public abstract class DiscordCommandExecutor implements DiscordCommand {

    public abstract void execute(BukkitDiscordConsole plugin, User user, Message message, String label, String[] args);
}
