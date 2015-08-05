package htc.cloud.intern.hungrytest;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by intern on 7/31/15.
 */
public class PlaceState {

    private String mName;
    private LatLng mLatLng;
    private String mImgSrc;
    private String mCategory;
    private String mPhoneNum;

    public PlaceState(String name, LatLng latlng, String imgSrc,
                      String category, String phoneNum) {
        mName = name;
        mLatLng = latlng;
        mImgSrc = imgSrc;
        mCategory = category;
        mPhoneNum = phoneNum;
    }

    public String getName() {
        return mName;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public String getImgSrc() {
        return mImgSrc;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getPhoneNum() {
        return mPhoneNum;
    }
}
