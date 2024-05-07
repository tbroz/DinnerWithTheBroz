/* Created 4/24/2023
 *
 * Author: Tiffany broz */

package com.zybooks.dinnerwiththebroz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {

    private TextView mTitleTextView;
    private TextView mIngredientsTextView;
    private TextView mInstructionsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mTitleTextView = findViewById(R.id.add_recipe_title);
        mIngredientsTextView = findViewById(R.id.recipe_ingredients);
        mInstructionsTextView = findViewById(R.id.recipe_steps);

        Recipe recipe = getIntent().getParcelableExtra("recipe");

        // Set the recipe title, ingredients, and steps
        mTitleTextView.setText(recipe.getTitle());
        mIngredientsTextView.setText(recipe.getIngredients());
        mInstructionsTextView.setText(recipe.getSteps());
    }
}