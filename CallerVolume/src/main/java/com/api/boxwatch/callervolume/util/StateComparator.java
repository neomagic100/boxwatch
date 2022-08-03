package com.api.boxwatch.callervolume.util;

import java.util.Comparator;

import com.api.boxwatch.callervolume.mapping.CallerVolumeView;

public class StateComparator implements Comparator<CallerVolumeView> {

	@Override
	public int compare(CallerVolumeView v1, CallerVolumeView v2) {
		String state1 = v1.getState();
		String state2 = v2.getState();

		return state1.compareTo(state2);
	}

}
