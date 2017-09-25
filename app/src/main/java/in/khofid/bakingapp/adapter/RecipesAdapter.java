package in.khofid.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.khofid.bakingapp.R;
import in.khofid.bakingapp.data.Recipes;

/**
 * Created by ofid on 9/25/17.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesAdapterViewHolder> {

    private Context mContext;
    private Recipes[] mRecipes;
    final private RecipesAdapterOnClickHandler mClickHandler;

    public RecipesAdapter(Context context,RecipesAdapterOnClickHandler clickHandler) {
        this.mContext = context;
        this.mClickHandler = clickHandler;
    }

    public interface RecipesAdapterOnClickHandler{
        void onClick(Recipes recipes);
    }

    @Override
    public RecipesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id = R.layout.recipe_item;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(id, parent, false);
        return  new RecipesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipesAdapterViewHolder holder, int position) {
        final Recipes entry = mRecipes[position];
        try {
            Picasso.with(mContext)
                    .load(entry.image)
                    .placeholder(R.drawable.img_recipes)
                    .error(R.drawable.img_recipes)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(holder.recipeImage);
        } catch (Exception e){
            holder.recipeImage.setImageResource(R.drawable.img_recipes);
        }
        holder.recipeTitle.setText(entry.name);
        holder.servingCount.setText(mContext.getString(R.string.servings_quantity, entry.servings));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        if(null == mRecipes) return 0;
        return mRecipes.length;
    }

    public void setRecipesData(Recipes[] recipes){
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    public class RecipesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.recipe_image) ImageView recipeImage;
        @BindView(R.id.tv_recipe_title) TextView recipeTitle;
        @BindView(R.id.tv_serving) TextView servingCount;

        public RecipesAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Recipes recipes = mRecipes[pos];
            mClickHandler.onClick(recipes);
        }
    }


}
