package com.fullsail.christopherfortune.gametrack.GameListFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fullsail.christopherfortune.gametrack.R;

import java.util.ArrayList;
import java.util.Map;

public class GamesListAdapter extends BaseAdapter {

    // Base Value for the adapter's Items ID
    private static final int ID_CONSTANT = 0x1111111;

    // Data Collection to store the list of maps
    private final ArrayList<? extends Map<String, ?>> gamesDataCollection;

    // Int variable to store the ID of the Child layout
    private final int baseAdapterChildLayoutID;

    // Int array to store the view IDs from the child layout
    private final int[] baseAdapterChildViewIDs;

    // String array to store the string keys for the collection of maps
    private final String[] baseAdapterDataKeys;

    // Cached Reference to recycle views
    private LayoutInflater baseAdapterLayoutInflater;

    public GamesListAdapter(Context context, ArrayList<? extends Map<String, ?>> dataCollection, String[] dataKeys, int[] childViewIds){

        gamesDataCollection = dataCollection;
        baseAdapterChildLayoutID = R.layout.games_list_row;
        baseAdapterChildViewIDs = childViewIds;
        baseAdapterDataKeys = dataKeys;

        if(baseAdapterChildViewIDs.length != baseAdapterDataKeys.length){

            throw new IllegalArgumentException("Array length mismatch");
        }

        baseAdapterLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return gamesDataCollection.size();
    }

    @Override
    public Object getItem(int position) {
        return gamesDataCollection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // If there isn't a recycled view
        if(convertView == null){

            // Call the cached inflater to inflate the child layout
            convertView = baseAdapterLayoutInflater.inflate(baseAdapterChildLayoutID, parent, false);
        }

        // Retrieve the map chosen at the index chosen
        final Map map = gamesDataCollection.get(position);

        // If the map has data at the specified index
        if(map != null) {

            // Loop through the childViewID array
            for (int i = 0; i < baseAdapterChildViewIDs.length; ++i) {

                // Get the specific view for the ChildLayout
                final View view = convertView.findViewById(baseAdapterChildViewIDs[i]);

                // If the view is not null
                if (view != null) {

                    // Object variable to get the data from teh object at the specified key
                    final Object data = map.get(baseAdapterDataKeys[i]);

                    // If the view is a TextView
                    if(view instanceof TextView) {

                        // If the data being presented is a String
                        if (data instanceof String) {

                            // Set the data to the text view
                            ((TextView) view).setText((String) data);
                        }
                    }
                }
            }
        }

        return convertView;
    }
}
