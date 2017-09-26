package in.khofid.bakingapp.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.khofid.bakingapp.R;
import in.khofid.bakingapp.adapter.RecipesAdapter;
import in.khofid.bakingapp.data.Recipes;
import in.khofid.bakingapp.utilities.JsonUtils;
import in.khofid.bakingapp.utilities.NetworkUtils;

/**
 * Created by ofid on 9/25/17.
 */

public class RecipeMainFragment extends Fragment implements RecipesAdapter.RecipesAdapterOnClickHandler{

    @BindView(R.id.rv_recipe_main) RecyclerView mRecyclerView;
    @BindView(R.id.tv_error_message) TextView mTvErrorMessage;
    @BindView(R.id.pb_loading_indicator) ProgressBar mProgress;
    public RecipesAdapter mRecipesAdapter;
    Recipes[] mRecipes;

    public static final String RECIPE_KEY = "recipe_key";

    public RecipeMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_main, container, false);
        ButterKnife.bind(this, rootView);
        getActivity().setTitle(R.string.app_name);

        mRecipesAdapter = new RecipesAdapter(getContext(), this);
        int columnsNumber = getResources().getInteger(R.integer.columns_number);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnsNumber));
        mRecyclerView.setAdapter(mRecipesAdapter);

        loadRecipesData();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(RECIPE_KEY, mRecipes);
    }

    private void loadRecipesData(){
        mProgress.setVisibility(View.VISIBLE);
        new FetchRecipesTask().execute();
    }

    private void showError(){
        mTvErrorMessage.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(Recipes recipes) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(RecipeDetailFragment.INGREDIENT_PARCEL, recipes.ingredients);
        recipeDetailFragment.setArguments(bundle);
        getActivity().setTitle(recipes.name);
        transaction.replace(R.id.fragment_container, recipeDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private class FetchRecipesTask extends AsyncTask<String, Void, Recipes[]>{

        @Override
        protected Recipes[] doInBackground(String... params) {
            if(params.length == 0) {
                URL recipesUrl = NetworkUtils.buildUrl();

                try{
                    String jsonMovies = NetworkUtils.getResponseFromHttpUrl(recipesUrl);
                    return JsonUtils.getRecipes(jsonMovies);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(!NetworkUtils.isOnline(getContext())){
                showError();
                Toast.makeText(getActivity(), getString(R.string.error_no_internet), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPostExecute(Recipes[] recipes) {
            mProgress.setVisibility(View.INVISIBLE);
            if(null != recipes){
                mRecipes = recipes;
                mRecipesAdapter.setRecipesData(recipes);
            }
        }
    }


}
