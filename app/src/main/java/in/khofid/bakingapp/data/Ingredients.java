package in.khofid.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ofid on 9/25/17.
 */

public class Ingredients implements Parcelable {

    public float quantity;
    public String measure;
    public String ingredient;

    public Ingredients() {
    }

    protected Ingredients(Parcel in) {
        quantity = in.readFloat();
        measure = in.readString();
        ingredient = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
