/* Created 4/24/2023
 *
 * Author: Tiffany broz */

package com.zybooks.dinnerwiththebroz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private RecipeAdapter mRecipeAdapter;
    private RecipeDbHelper mDbHelper;
    private TextView mNoRecipeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new RecipeDbHelper(this);
        mListView = findViewById(R.id.list_view);
        mNoRecipeTextView = findViewById(R.id.no_recipe_text);

        // Set up the list view with recipe titles
        ArrayList<Recipe> recipeList = mDbHelper.getAllRecipes();
        mRecipeAdapter = new RecipeAdapter(this, recipeList);
        mListView.setAdapter(mRecipeAdapter);

        // Set up the list view item click listener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = (Recipe) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.putExtra("recipe", recipe);
                startActivity(intent);
            }
        });

        // Set up the list view item long click listener to delete recipe
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = (Recipe) parent.getItemAtPosition(position);
                confirmDeleteDialog(recipe);
                return true;
            }
        });

        // Hide the "no recipe" text view if there are recipes in the database
        if (recipeList.size() > 0) {
            mNoRecipeTextView.setVisibility(View.GONE);
        }
    }

    public void addRecipe(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        Recipe recipe = new Recipe("", "", "");
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the list view when the activity is resumed
        ArrayList<Recipe> recipeList = mDbHelper.getAllRecipes();
        mRecipeAdapter.clear();
        mRecipeAdapter.addAll(recipeList);
        mRecipeAdapter.notifyDataSetChanged();

        // Show or hide the "no recipe" text view based on the number of recipes in the database
        if (recipeList.size() > 0) {
            mNoRecipeTextView.setVisibility(View.GONE);
        } else {
            mNoRecipeTextView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Close the database when the activity is destroyed
        mDbHelper.close();
    }

    private void confirmDeleteDialog(final Recipe recipe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Recipe");
        builder.setMessage("Are you sure you want to delete this recipe?");
        builder.setPositiveButton("Delete", (dialog, which) -> {
            mDbHelper.deleteRecipe(recipe.getId());
            Toast.makeText(MainActivity.this, "Recipe deleted", Toast.LENGTH_SHORT).show();
            onResume(); // Refresh the list view
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
