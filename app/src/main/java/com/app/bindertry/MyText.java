package com.app.bindertry;

import android.os.Parcel;
import android.os.Parcelable;

public class MyText implements Parcelable {
    public String txt;
    public MyText(){}
    protected MyText(Parcel in){readFromParcel(in);}

    public static final Creator<MyText> CREATOR = new Creator<MyText>() {
        @Override
        public MyText createFromParcel(Parcel in) {
            return new MyText(in);
        }

        @Override
        public MyText[] newArray(int size) {
            return new MyText[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(txt);
    }
    public void readFromParcel(Parcel in){
        txt = in.readString();
    }

    public String retText(String text){
        this.txt = text;
        return txt;
    }
    public String retYoco(){
        txt = txt + "yoco.";
        return txt;
    }
}
