package com.etoilecarte.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.etoilecarte.Beans.CustomAdapterList;
import com.etoilecarte.Beans.Food;
import com.etoilecarte.Beans.Session;
import com.etoilecarte.R;
import com.etoilecarte.Utils.FragmentManagerUtil;
import com.etoilecarte.WebServices.WebService;
import com.tktm.lyvraison.beans.Article;
import com.tktm.lyvraison.beans.Category;

import java.util.ArrayList;

import static com.etoilecarte.Activities.PrincipalFragment.CAT_ID_KEY;
import static com.etoilecarte.Activities.PrincipalFragment.url;
import static com.etoilecarte.Activities.TableFragment.Table_ID_KEY;


/**
 * Created by simo on 16/04/16.
 */
public class ListFoodsFragment extends Fragment implements  AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<Article> articles = null;
    ArrayList<Food> foods=null;
    WebService webService = new WebService(getActivity());
    public static final String FOOD_IMAGE_KEY = "foodImageId";
    public static final String FOOD_TEXT_KEY = "foodTextId";

    ListView mListView;
    View rootView;
    public static ListFoodsFragment newInstance(String contactId,String idCat) {
        ListFoodsFragment fragment = new ListFoodsFragment();
        Bundle args = new Bundle();
        args.putString(Table_ID_KEY, contactId);
        args.putString(CAT_ID_KEY,idCat);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.list_view_cat, container, false);
        return rootView;

    }
    public String getTableId() {
        return getArguments().getString(Table_ID_KEY);
    }
    public String getCatId() {
        return getArguments().getString(CAT_ID_KEY);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


            foods = new ArrayList<>();
            ArrayList<Category> categories=null;

            if (!(getCatId() == null || getCatId().equalsIgnoreCase(""))){

            articles=webService.listArticle(getCatId(),url);

            for(Article article :articles ){
                Food food = new Food();
                food.setTitre(article.getName());
                food.setPrix(String.valueOf(article.getPrice())+" €");
                food.setImage(article.getDescription().getUrlSmallImage());
                food.setDescription(article.getDescription().getText());
                food.setIdFood(article.getId());
                foods.add(food);
            }
            }

        ListAdapter listAdapter = new CustomAdapterList(rootView.getContext(),foods);

        listView = (ListView) rootView.findViewById(R.id.listViewCat);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        listView.getItemAtPosition(i);
        String largeImage =articles.get(i).getDescription().getUrlLargeImage();
        String textName=articles.get(i).getName();
       // FragmentManagerUtil.replaceMainFragments(getActivity(),new DetailsFragment().newInstance(largeImage,textName));
        FragmentManagerUtil.replaceMainFragments(getActivity(),new PanierFragment().newInstance(getTableId()));


    }
}
