package net.TntClient.event.events;

import net.TntClient.event.Event;
import net.minecraft.network.Packet;

public class EventSendPacket extends Event
{
    private final Packet packet;

    public EventSendPacket(Packet packet)
    {
        this.packet = packet;
    }

    public Packet getPacket()
    {
        return packet;
    }
}
