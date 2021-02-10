package net.smart.console.discord.listener;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class UserMentionClientListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getMessage().getMentionedUsers().size() != 1) return;
        String[] args = event.getMessage().getContentDisplay().split(" ");

        if (args.length == 1 && event.getMessage().getMentionedUsers().get(0).getId().equals(event.getJDA().getSelfUser().getId())) {
            event.getChannel().sendMessage(":eyes: **|** " + event.getAuthor().getAsMention() + ", This project is open source. **Contribute pull requests**: https://github.com/SmartBR/bukkit-discord-console.").queue();
        }
    }
}
