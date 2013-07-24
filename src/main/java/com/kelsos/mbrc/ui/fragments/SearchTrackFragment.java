package com.kelsos.mbrc.ui.fragments;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.google.inject.Inject;
import com.kelsos.mbrc.R;
import com.kelsos.mbrc.adapters.TrackEntryAdapter;
import com.kelsos.mbrc.data.Queue;
import com.kelsos.mbrc.data.TrackEntry;
import com.kelsos.mbrc.data.UserAction;
import com.kelsos.mbrc.events.MessageEvent;
import com.kelsos.mbrc.events.ProtocolEvent;
import com.kelsos.mbrc.events.ui.TrackSearchResults;
import com.kelsos.mbrc.others.Protocol;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class SearchTrackFragment extends RoboSherlockListFragment {
    private static final int QUEUE_NEXT = 1;
    private static final int QUEUE_LAST = 2;
    private static final int PLAY_NOW = 3;
    private static final int GROUP_ID = 14;

    private TrackEntryAdapter adapter;
    @Inject Bus bus;

    @Override public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override public void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerForContextMenu(getListView());
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ui_fragment_library_simpl, container, false);
    }

    @Override public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle(R.string.search_context_header);
        menu.add(GROUP_ID, QUEUE_NEXT, 0, R.string.search_context_queue_next);
        menu.add(GROUP_ID, QUEUE_LAST, 0, R.string.search_context_queue_last);
        menu.add(GROUP_ID, PLAY_NOW, 0, R.string.search_context_play_now);
    }

    @Override public boolean onContextItemSelected(android.view.MenuItem item) {
        if (item.getGroupId() == GROUP_ID) {
            AdapterView.AdapterContextMenuInfo mi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Object line = adapter.getItem(mi.position);
            final String qContext = Protocol.LibraryQueueTrack;
            final String query = ((TrackEntry)line).getSrc();

            UserAction ua = null;
            switch (item.getItemId()) {
                case QUEUE_NEXT:
                    ua = new UserAction(qContext, new Queue("next",query));
                    break;
                case QUEUE_LAST:
                    ua = new UserAction(qContext, new Queue("last",query));
                    break;
                case PLAY_NOW:
                    ua = new UserAction(qContext, new Queue("now", query));
                    break;
            }

            if (ua != null) bus.post(new MessageEvent(ProtocolEvent.UserAction, ua));
            return true;
        } else {
            return false;
        }
    }

    @Subscribe public void handleTrackResults(TrackSearchResults results) {
        adapter = new TrackEntryAdapter(getActivity(), R.layout.ui_list_dual, results.getList());
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}