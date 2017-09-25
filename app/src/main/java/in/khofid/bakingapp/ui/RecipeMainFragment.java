package in.khofid.bakingapp.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public RecipesAdapter mRecipesAdapter;
    public Recipes[] mRecipes;

    public RecipeMainFragment() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mRecipesAdapter.setRecipesData(mRecipes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_main, container, false);
        ButterKnife.bind(this, rootView);

        mRecipesAdapter = new RecipesAdapter(getContext(), this);
        mRecyclerView = rootView.findViewById(R.id.rv_recipe_main);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerView.setAdapter(mRecipesAdapter);

        loadRecipesData();

        return rootView;
    }

    private void loadRecipesData(){
        new FetchRecipesTask().execute();
    }

    @Override
    public void onClick(Recipes recipes) {

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
        protected void onPostExecute(Recipes[] recipes) {
            if(null != recipes){
                mRecipesAdapter.setRecipesData(recipes);
            }
        }
    }


}
