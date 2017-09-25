package in.khofid.bakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ofid on 9/25/17.
 */

public class Steps implements Parcelable {

    public int id;
    public String shortDescription;
    public String description;
    public String videoURL;
    public String thumbnailURL;

    public Steps() {
    }

    protected Steps(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };
}
