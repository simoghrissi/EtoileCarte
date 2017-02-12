package com.etoilecarte.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.etoilecarte.Beans.MyAdapter;
import com.etoilecarte.R;
import com.etoilecarte.Utils.FragmentManagerUtil;
import com.etoilecarte.WebServices.ConnectionServer;

import static com.etoilecarte.Activities.TableFragment.Table_ID_KEY;
import static com.etoilecarte.R.id.imageLogo;


/**
 * Created by mghrissi on 12/02/2017.
 */

public class PrincipalFragment extends Fragment {

    View rootView;
    public static String url;
    public static final String CAT_ID_KEY = "catId";
    public static PrincipalFragment newInstance(String contactId) {
        PrincipalFragment fragment = new PrincipalFragment();
        Bundle args = new Bundle();
        args.putString(Table_ID_KEY, contactId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_main, container, false);
        return rootView;
    }
    public String getTableId() {
        return getArguments().getString(Table_ID_KEY);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void runMainActivity(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        url = preferences.getString("ServerName", "");
        if (url.equals("")){
            Toast.makeText(getActivity(), "L'adresse du serveur est Invalide",
                    Toast.LENGTH_LONG).show();
        }
        ImageView imageLogo = (ImageView)rootView.findViewById(R.id.imageLogo);
        //   imageJour.setOnClickListener(this);
        imageLogo.setImageResource(R.drawable.logo);
        //  imageJour.setImageResource(R.drawable.menudujour);
        Boolean testConnection = ConnectionServer.getInstance().IsReachable(getActivity(), url);
        if (testConnection) {
            GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
            gridView.setAdapter(new MyAdapter(getActivity()));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    MyAdapter.Item item = (MyAdapter.Item) parent.getItemAtPosition(position);
                    int idCat = item.id;
                    FragmentManagerUtil.replaceMainFragments(getActivity(),new ListFoodsFragment().newInstance(getTableId(),Integer.toString(idCat)));

                }
            });
        }
    }

}


