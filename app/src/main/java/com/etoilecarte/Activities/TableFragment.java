package com.etoilecarte.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.etoilecarte.Beans.Session;
import com.etoilecarte.Beans.Table;
import com.etoilecarte.R;
import com.etoilecarte.Utils.FragmentManagerUtil;

import java.util.ArrayList;

/**
 * Created by mghrissi on 30/01/2017.
 */

public class TableFragment extends Fragment{
    static TableFragment instance;

    ArrayList<Button> arrayButton ;
    View rootView;
    public static final String Table_ID_KEY = "tableId";

    public static TableFragment newInstance(String tableId) {
        TableFragment fragment = new TableFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString(Table_ID_KEY, tableId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.table_layout, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String buttonId="";

        ViewGroup parentView = (ViewGroup) rootView.findViewById(R.id.tableActivity);
        for(int i=0; i < parentView.getChildCount(); i++) {
            //int id = getResources().getIdentifier("button_"+i, "id", getPackageName());
            View childView = parentView.getChildAt(i);
            int resID = childView.getId();
            String []splitid =childView.getResources().getResourceName(resID).toString().split("/");
            buttonId=splitid[1];
            final String finalButtonId = buttonId;
            // Button b = (Button) findViewById(resID);
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Session.arrayTable.isEmpty()){
                        Session.newTable(finalButtonId);
                        Toast.makeText(getActivity(), finalButtonId, Toast.LENGTH_LONG).show();

                        FragmentManagerUtil.replaceMainFragments(getActivity(),new PrincipalFragment().newInstance(finalButtonId));

                    }else{
                        if(!tableDejaPrise(Session.arrayTable,finalButtonId)){
                            Session.newTable(finalButtonId);
                            FragmentManagerUtil.replaceMainFragments(getActivity(),new PrincipalFragment().newInstance(finalButtonId));

                        }else{
                            FragmentManagerUtil.replaceMainFragments(getActivity(),new PrincipalFragment().newInstance(finalButtonId));
                            Toast.makeText(getActivity(), "Table deja utilisée", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            });

        }
    }



    public boolean tableDejaPrise(ArrayList<Table> table,String idTable){

        for(int i =0; i<table.size();i++){
            if(table.get(i).getIdTable().equalsIgnoreCase(idTable)){
                return true;
            }
        }
        return false;
    }

}
