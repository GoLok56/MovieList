package io.github.golok56.movielist.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Satria Adi Putra
 */
public class Movie implements Parcelable {

    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mImage;

    private boolean mIsHardCoded = false;

    public Movie(String title, String year, String imageUrl, boolean isHardCoded){
        mReleaseDate = year;
        mTitle = title;
        mImage = imageUrl;
        mIsHardCoded = isHardCoded;
    }

    public String getImage(){
        return mImage;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getYear(){
        return mReleaseDate.substring(0, 4);
    }

    public boolean isHardCoded(){
        return mIsHardCoded;
    }

    @Override
    public String toString() {
        return mTitle + " " + mReleaseDate + " " + mImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mTitle);
        dest.writeString(this.mImage);
        dest.writeByte(this.mIsHardCoded ? (byte) 1 : (byte) 0);
    }

    protected Movie(Parcel in) {
        this.mReleaseDate = in.readString();
        this.mTitle = in.readString();
        this.mImage = in.readString();
        this.mIsHardCoded = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
