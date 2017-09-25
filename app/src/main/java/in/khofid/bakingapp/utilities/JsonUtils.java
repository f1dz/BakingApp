package in.khofid.bakingapp.utilities;

import com.google.gson.Gson;
import in.khofid.bakingapp.data.Recipes;

/**
 * Created by ofid on 9/25/17.
 */

public final class JsonUtils {

    public static Recipes[] getRecipes(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Recipes[].class);
    }

}
