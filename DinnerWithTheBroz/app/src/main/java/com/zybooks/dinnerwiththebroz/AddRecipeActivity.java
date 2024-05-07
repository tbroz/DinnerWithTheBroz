/* Created 4/24/2023
 *
 * Author: Tiffany broz */

package com.zybooks.dinnerwiththebroz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecipeActivity extends AppCompatActivity {

    private RecipeDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        mDbHelper = new RecipeDbHelper(this);
    }

    public void saveRecipe(View view) {
        EditText titleEditText = findViewById(R.id.recipe_title_edittext);
        EditText ingredientsEditText = findViewById(R.id.recipe_ingredients_edittext);
        EditText instructionsEditText = findViewById(R.id.recipe_directions_edittext);

        String title = titleEditText.getText().toString().trim();
        String ingredients = ingredientsEditText.getText().toString().trim();
        String instructions = instructionsEditText.getText().toString().trim();

        if (title.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setIngredients(ingredients);
        recipe.setSteps(instructions);

        mDbHelper.addRecipe(recipe);

        Toast.makeText(this, "Recipe saved successfully", Toast.LENGTH_SHORT).show();

        // Reset the text fields
        titleEditText.getText().clear();
        ingredientsEditText.getText().clear();
        instructionsEditText.getText().clear();

        // Go back to the main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
