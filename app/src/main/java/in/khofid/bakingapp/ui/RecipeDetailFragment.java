package in.khofid.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.khofid.bakingapp.R;
import in.khofid.bakingapp.adapter.IngredientsAdapter;
import in.khofid.bakingapp.data.Ingredients;

/**
 * Created by ofid on 9/25/17.
 */

public class RecipeDetailFragment extends Fragment {

    @BindView(R.id.rv_ingredients) RecyclerView mRvIngredients;
    @BindView(R.id.rv_recipe_steps) RecyclerView mRvRecipeStep;
    public IngredientsAdapter mIngredientsAdapter;
    public Ingredients[] mIngredients;

    public static final String INGREDIENT_PARCEL = "ingredient_parcel";

    public RecipeDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, rootView);

        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mIngredientsAdapter = new IngredientsAdapter(getContext());
        Bundle bundle = this.getArguments();
        if(bundle != null && bundle.containsKey(INGREDIENT_PARCEL)){
            ArrayList<Ingredients> ing = bundle.getParcelableArrayList(INGREDIENT_PARCEL);
            mIngredientsAdapter.setIngredientsData(ing);
        }

        mRvIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvIngredients.setAdapter(mIngredientsAdapter);

        return rootView;
    }
}
