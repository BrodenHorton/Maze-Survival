package me.brody.mazesurvival.event;

import me.brody.mazesurvival.event.eventargs.EventArgs;

public interface IListener<T extends EventArgs> {
    void execute(Object sender, T args);
}
