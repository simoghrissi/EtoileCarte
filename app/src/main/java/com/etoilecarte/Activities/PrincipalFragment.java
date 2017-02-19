package com.etoilecarte.Activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.etoilecarte.Beans.CategorieMenuItem;
import com.etoilecarte.Beans.MyAdapter;
import com.etoilecarte.R;
import com.etoilecarte.Utils.FragmentManagerUtil;
import com.etoilecarte.WebServices.AbstractConnexion;
import com.etoilecarte.WebServices.BackgroundConfirmation;
import com.etoilecarte.WebServices.ConnectionServer;
import com.etoilecarte.WebServices.WebService;
import com.tktm.lyvraison.beans.Category;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static com.etoilecarte.Activities.TableFragment.Table_ID_KEY;


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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        runMainActivity();





        // hide clavier
      /*InputMethodManager inputManager =
                (InputMethodManager) getContext().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                this.getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);*/

    }

    public void setAdapterMenuItem(){

        WebService webService = new WebService(getContext());
        // Asynck Task
        ArrayList<Category> listCategorie = webService.listProduit(PrincipalFragment.url);

        ArrayList<CategorieMenuItem> ListmenuItems = new ArrayList<>();
        for (Category cat : listCategorie) {

            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(PrincipalFragment.url+"/" + cat.getDescription().getImagePath()).getContent());
                ListmenuItems.add(new CategorieMenuItem(cat.getName(), bitmap, cat.getId()));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);

                MyAdapter gridViewAdapter = new MyAdapter(rootView.getContext(),ListmenuItems);
                gridView.setAdapter(gridViewAdapter);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                CategorieMenuItem item = (CategorieMenuItem) parent.getItemAtPosition(position);
                int idCat = item.id;
                FragmentManagerUtil.replaceMainFragments(getActivity(),new ListFoodsFragment().newInstance(getTableId(),Integer.toString(idCat)));

            }
        });

    }
    public void runMainActivity(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        url = preferences.getString("ServerName", "");
        if (url.equals("")){
            Toast.makeText(getActivity(), "L'adresse du serveur est Invalide",
                    Toast.LENGTH_LONG).show();
        }
        ImageView imageLogo = (ImageView)rootView.findViewById(R.id.imageLogo);
        imageLogo.setImageResource(R.drawable.logo);

        Boolean testConnection = ConnectionServer.getInstance().IsReachable(getActivity(), url);
        if(testConnection){
            setAdapterMenuItem();
        }
        }


}
