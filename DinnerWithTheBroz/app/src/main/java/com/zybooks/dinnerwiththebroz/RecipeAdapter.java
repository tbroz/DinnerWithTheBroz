/* Created 4/24/2023
 *
 * Author: Tiffany broz */

package com.zybooks.dinnerwiththebroz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(@NonNull Context context, @NonNull ArrayList<Recipe> recipeList) {
        super(context, 0, recipeList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_recipe, parent, false);
        }

        Recipe currentRecipe = getItem(position);

        TextView titleTextView = listItemView.findViewById(R.id.title_text_view);
        titleTextView.setText(currentRecipe.getTitle());

        return listItemView;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}