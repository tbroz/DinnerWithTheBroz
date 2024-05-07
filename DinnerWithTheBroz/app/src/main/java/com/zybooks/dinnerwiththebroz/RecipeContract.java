/* Created 4/24/2023
 *
 * Author: Tiffany broz */

package com.zybooks.dinnerwiththebroz;

import android.provider.BaseColumns;

public class RecipeContract {

    private RecipeContract() {}

    public static class RecipeEntry implements BaseColumns {

        public static final String TABLE_NAME = "recipes";
        public static final String COLUMN_RECIPE_TITLE = "title";
        public static final String COLUMN_RECIPE_INGREDIENTS = "ingredients";
        public static final String COLUMN_RECIPE_STEPS = "steps";
        // public static final String COLUMN_RECIPE_DELETED = "deleted";
        // public static final String COLUMN_DELETED = "deleted"; // new column for marking deleted recipes
    }
}
