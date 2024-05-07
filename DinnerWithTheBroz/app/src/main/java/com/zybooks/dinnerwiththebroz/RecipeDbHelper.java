/* Created 4/24/2023
 *
 * Author: Tiffany broz */

package com.zybooks.dinnerwiththebroz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class RecipeDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipes.db";
    private static final int DATABASE_VERSION = 1;

    public RecipeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE " + RecipeContract.RecipeEntry.TABLE_NAME + " ("
                + RecipeContract.RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RecipeContract.RecipeEntry.COLUMN_RECIPE_TITLE + " TEXT NOT NULL, "
                + RecipeContract.RecipeEntry.COLUMN_RECIPE_INGREDIENTS + " TEXT NOT NULL, "
                + RecipeContract.RecipeEntry.COLUMN_RECIPE_STEPS + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_RECIPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeEntry.TABLE_NAME);
        onCreate(db);
    }

    // Add this method to get all recipes as an ArrayList<Recipe>
    public ArrayList<Recipe> getAllRecipes() {
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + RecipeContract.RecipeEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setTitle(cursor.getString(1));
                recipe.setIngredients(cursor.getString(2));
                recipe.setSteps(cursor.getString(3));

                // Adding recipe to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }

        // close cursor and database
        cursor.close();
        db.close();

        // return recipe list
        return recipeList;
    }

    // Add this method to insert a new recipe into the database
    public long addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_TITLE, recipe.getTitle());
        values.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_INGREDIENTS, recipe.getIngredients());
        values.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_STEPS, recipe.getSteps());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, values);

        // close the database
        db.close();

        // return the new row id
        return newRowId;
    }

    // Add this method to delete a recipe from the database
    public void deleteRecipe(long recipeId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the where clause for the SQL query
        String selection = RecipeContract.RecipeEntry._ID + " = ?";

        // Specify the arguments in the where clause
        String[] selectionArgs = { String.valueOf(recipeId) };

        // Delete the recipe from the database
        db.delete(RecipeContract.RecipeEntry.TABLE_NAME, selection, selectionArgs);

        // close the database
        db.close();
    }
}