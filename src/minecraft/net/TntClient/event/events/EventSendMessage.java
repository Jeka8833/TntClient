package net.TntClient.event.events;

import net.TntClient.event.Event;

public class EventSendMessage extends Event {

    public final String text;

    public EventSendMessage(final String text) {
        this.text = text;
    }

}
