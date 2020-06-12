package net.TntClient.event.events;

import net.TntClient.event.Event;

public class EventKey extends Event
{
    private final int key;

    public EventKey(int key)
    {
        this.key = key;
    }

    public int getKey()
    {
        return key;
    }
}
