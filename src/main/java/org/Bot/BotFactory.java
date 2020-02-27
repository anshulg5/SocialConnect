package org.Bot;

import com.google.inject.assistedinject.Assisted;

public interface BotFactory {
    Bot create(@Assisted("name") String name, @Assisted("token") String token);
}
