package in.khofid.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.khofid.bakingapp.R;
import in.khofid.bakingapp.data.Ingredients;

/**
 * Created by ofid on 9/25/17.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Ingredients> mIngredients;

    public IngredientsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id = R.layout.ingredient_item;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(id, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Ingredients entry = mIngredients.get(position);
        String item = entry.ingredient + ", "
                + String.valueOf(entry.quantity) + " " + entry.measure;

        holder.tvIngredientNumber.setText(String.valueOf(position + 1) + ". ");
        holder.tvIngredient.setText(item);
    }

    @Override
    public int getItemCount() {
        if(null == mIngredients) return 0;
        return mIngredients.size();
    }

    public void setIngredientsData(ArrayList<Ingredients> ingredientData){
        mIngredients = ingredientData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_ingredient) TextView tvIngredient;
        @BindView(R.id.tv_ingredient_number) TextView tvIngredientNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
