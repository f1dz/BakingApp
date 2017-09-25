package in.khofid.bakingapp.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import in.khofid.bakingapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fm = getSupportFragmentManager();
//
//        RecipeMainFragment recipeMainFragment = new RecipeMainFragment();
//        fm.beginTransaction()
//                .add(R.id.recipe_list_fragment, recipeMainFragment)
//                .commit();
    }
}
