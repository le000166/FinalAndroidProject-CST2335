package com.mayabo.finalandroidproject.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mayabo.finalandroidproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the final project
 * Topic is specify to Recipe Search Engine
 * This project is a group project
 * Each person take one topic
 * My Topic is : Recipe
 * @author The Dai Phong Le
 * @version 1.0
 * @since 2019-11-11
 */

public class ListFavouriteActivity extends AppCompatActivity {

    ListView theList;
    CustomListAdapter adapter;
    ArrayList<Recipe> savedList;
    Toolbar tbar;
    public static final String ACTIVITY_NAME = "ListFavouriteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_favourite_acvitity);
        tbar = (Toolbar) findViewById(R.id.toolbar);
        tbar.setTitle("Your Favourite List");
        setSupportActionBar(tbar);

        theList = (ListView) findViewById(R.id.list_favourite);

        DatabaseHandler db = new DatabaseHandler(this);

        savedList = new ArrayList<Recipe>(db.getAllRecipes());

        adapter = new CustomListAdapter(this, savedList);
        theList.setAdapter(adapter);
        adapter.notifyDataSetChanged();





            theList.setOnItemClickListener((parent, view, position, id) -> {
                Log.e("You clicked on :", " item " + position);
                Recipe chosenRecipe = savedList.get(position);
                Intent singlePage = new Intent(ListFavouriteActivity.this, RecipeSingle.class);
                singlePage.putExtra("ActivityName", ACTIVITY_NAME);
                singlePage.putExtra("title", chosenRecipe.getTitle());
                singlePage.putExtra("url", chosenRecipe.getUrl());
                singlePage.putExtra("imageUrl", chosenRecipe.getImgUrl());
                singlePage.putExtra("imageID", chosenRecipe.getImageID());
                startActivity(singlePage);
            });



    }





    /**
     * This is the favourite list is loaded from database list
     * The list will load the adapter and post everything in the database to the list view
     * using custom adapter
     * using the same list view that is create
     * click on the item will take the user to another single page
     * the single page will have the Remove button
     * Delete the item from the database
     * */
}
