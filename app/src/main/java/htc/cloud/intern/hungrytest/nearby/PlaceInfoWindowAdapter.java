package htc.cloud.intern.hungrytest.nearby;

import android.app.Activity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

import htc.cloud.intern.hungrytest.PlaceState;
import htc.cloud.intern.hungrytest.R;

/**
 * Created by intern on 8/5/15.
 */
public class PlaceInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


    private Activity mActivity;
    private View mContent;
    private HashMap<Marker, PlaceState> mMarkerInfo;

    public PlaceInfoWindowAdapter(Activity activity, HashMap<Marker, PlaceState> markerInfo) {

        mActivity = activity;
        mContent = mActivity.getLayoutInflater().inflate(R.layout.info_window_map, null);
        mMarkerInfo = markerInfo;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        PlaceState placeState = mMarkerInfo.get(marker);

        if (placeState == null) {   //mCurrentMarker
            return null;
        }

        ((TextView) mContent.findViewById(R.id.tv_title)).setText(marker.getTitle());
        ((TextView) mContent.findViewById(R.id.tv_desc)).setText(placeState.getCategory());
        ((RatingBar) mContent.findViewById(R.id.rating_bar)).setRating((float)placeState.getRating());

        return mContent;
    }
}
