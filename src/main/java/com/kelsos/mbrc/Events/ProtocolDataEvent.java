package com.kelsos.mbrc.events;

import com.kelsos.mbrc.data.MusicTrack;
import com.kelsos.mbrc.enums.ProtocolHandlerEventType;
import com.kelsos.mbrc.interfaces.IEvent;

import java.util.ArrayList;

public class ProtocolDataEvent implements IEvent
{

	private ProtocolHandlerEventType type;
	private String data;
	private ArrayList<MusicTrack> trackList;

	public ProtocolDataEvent(ProtocolHandlerEventType type, String data)
	{
		this.type = type;
		this.data = data;
		this.trackList = null;
	}

	public ProtocolDataEvent(ProtocolHandlerEventType type, ArrayList<MusicTrack> trackList)
	{
		this.type = type;
		this.data = "";
		this.trackList = trackList;
	}

	public ProtocolDataEvent(ProtocolHandlerEventType type)
	{
		this.type = type;
		this.data = "";
		this.trackList = null;
	}


	public ProtocolHandlerEventType getType()
	{
		return type;
	}

	public String getData()
	{
		return data;
	}

	public ArrayList<MusicTrack> getTrackList()
	{
		return trackList;
	}
}