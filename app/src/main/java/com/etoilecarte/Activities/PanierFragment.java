package com.etoilecarte.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.etoilecarte.Beans.Session;
import com.etoilecarte.Beans.Table;
import com.etoilecarte.R;
import com.etoilecarte.Utils.Utils;

import static com.etoilecarte.Activities.TableFragment.Table_ID_KEY;


/**
 * Created by mghrissi on 25/11/2016.
 */

public class PanierFragment extends Fragment {


    public String getTableId() {
        return getArguments().getString(Table_ID_KEY);
    }
    ListView mListView;
    View rootView;
    public static PanierFragment newInstance(String tableId) {
        PanierFragment fragment = new PanierFragment();
        Bundle args = new Bundle();
        args.putString(Table_ID_KEY, tableId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.layout_pannier, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (ListView) rootView.findViewById(R.id.listView_panier);
        Table table = Utils.searchTableById(getTableId());
        Session.table =table ;
            mListView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,Session.table.panier));

    }

}
