package net.smart.console.discord.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.smart.console.BukkitDiscordConsole;
import net.smart.console.discord.precommand.executor.DiscordCommandExecutor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

public class ExecutorCMD extends DiscordCommandExecutor {

    @Override
    @Command(name = "executor", description = "Executar um comando no console.", aliases = {"executar"})
    public void execute(BukkitDiscordConsole plugin, User user, Message message, String label, String[] args) {
        if (!message.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            message.getChannel().sendMessage(plugin.getConfigMessages().get("user-no-permission").toString()
                    .replace("{user}", user.getAsMention())).queue();
            return;
        }

        if (args.length == 0) {
            message.getChannel().sendMessage(plugin.getConfigMessages().get("executor-usage").toString()
                    .replace("{user}", user.getAsMention())
                    .replace("{label}", label)
            ).queue();
            return;
        }

        try {
            String command = StringUtils.join(args, " ");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

            message.getChannel().sendMessage(plugin.getConfigMessages().get("executor-success").toString()
                    .replace("{user}", user.getAsMention())).queue();
        }catch (Exception exception) {
            exception.printStackTrace();
            message.getChannel().sendMessage(plugin.getConfigMessages().get("executor-failed").toString()
                    .replace("{user}", user.getAsMention())
                    .replace("{error-message}", exception.getMessage())
            ).queue();
        }
    }
}
