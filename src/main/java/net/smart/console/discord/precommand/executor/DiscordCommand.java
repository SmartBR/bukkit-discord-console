package net.smart.console.discord.precommand.executor;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface DiscordCommand {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public abstract @interface Command {

        @Nonnull String name();
        String description();
        String[] aliases() default {""};
    }
}
