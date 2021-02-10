package net.smart.console.discord.precommand;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.smart.console.BukkitDiscordConsole;
import net.smart.console.discord.precommand.executor.DiscordCommand;
import net.smart.console.discord.precommand.executor.DiscordCommandExecutor;
import net.smart.console.discord.precommand.listener.DiscordCommandListener;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DiscordCommandLoader {

    @Getter private final Map<String, Map.Entry<Method, Object>> commands;
    private final String prefix;

    public DiscordCommandLoader(JDA jda, String prefix) {
        this.commands = new HashMap<>();
        this.prefix = prefix;
        jda.addEventListener(new DiscordCommandListener(this, prefix));
    }

    public void registerCommand(DiscordCommandExecutor executor) {
        try {
            Method executeMethod = executor.getClass().getMethod("execute", BukkitDiscordConsole.class, User.class, Message.class, String.class, String[].class);
            if (executeMethod.getAnnotation(DiscordCommand.Command.class) == null)
                throw new NullPointerException();

            DiscordCommand.Command commandInfo = executeMethod.getAnnotation(DiscordCommand.Command.class);
            registerCommand(executor, commandInfo, executeMethod);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void registerCommand(DiscordCommandExecutor executor, DiscordCommand.Command commandInfo, Method executeMethod) {
        try {
            Map.Entry<Method, Object> entry = new AbstractMap.SimpleEntry<>(executeMethod, executor);
            this.commands.put(commandInfo.name().toLowerCase(), entry);

            Arrays.stream(commandInfo.aliases()).forEach(cmd -> commands.put(cmd.toLowerCase(), entry));
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
