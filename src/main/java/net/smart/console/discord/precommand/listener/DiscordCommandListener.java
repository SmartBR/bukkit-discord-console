package net.smart.console.discord.precommand.listener;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.smart.console.BukkitDiscordConsole;
import net.smart.console.discord.precommand.DiscordCommandLoader;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

@AllArgsConstructor
public class DiscordCommandListener extends ListenerAdapter {

    private final DiscordCommandLoader commandLoader;
    private final String prefix;

    @SneakyThrows @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();
        String label = message.split(" ")[0].toLowerCase();
        if (!label.startsWith(prefix)) return;

        if (!this.commandLoader.getCommands().containsKey((label = label.replaceFirst(prefix, "")))) {
            event.getChannel().sendMessage(":x: **|** " + event.getAuthor().getAsMention() + ", unknown command. **Contribute pull requests**: https://github.com/SmartBR/bukkit-discord-console.").queue();
            return;
        }

        Map.Entry<Method, Object> entry = this.commandLoader.getCommands().get(label);
        String[] args = message.split(" ");
        args = Arrays.copyOfRange(args, 1, args.length);

        entry.getKey().invoke(entry.getValue(), BukkitDiscordConsole.getInstance(), event.getAuthor(), event.getMessage(), label, args);
    }
}
