package htc.cloud.intern.hungrytest.nearby;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import htc.cloud.intern.hungrytest.R;

/**
 * Created by intern on 7/28/15.
 */
public class MapViewFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final LatLng TAIPEI_LAT_LNG = new LatLng(25.0, 121.6);
    private GoogleMap mMap;
    protected LatLng mCurrentLocation;
    protected Marker mCurrentMarker;

    public static MapViewFragment newInstance(int sectionNumber) {
        MapViewFragment fragment = new MapViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MapViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map_mapview, container, false);
        mMap = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TAIPEI_LAT_LNG, 10));
        return rootView;

    }


    public void onLocationChanged(LatLng location, PlaceLikelihoodBuffer likelyPlaces){

        mCurrentLocation = location;
        if (mCurrentMarker == null) {
            mCurrentMarker = mMap.addMarker(new MarkerOptions().position(mCurrentLocation));
        }
        else {
            mCurrentMarker.setPosition(mCurrentLocation);
        }
        mCurrentMarker.setTitle("My Location");

        for (PlaceLikelihood placeLikelihood : likelyPlaces) {
            Log.i("location-mapview", String.format("Place '%s' has likelihood: %g",
                    placeLikelihood.getPlace().getName(),
                    placeLikelihood.getLikelihood()));
            String placeName = placeLikelihood.getPlace().getName().toString();
            LatLng placeLatLng = placeLikelihood.getPlace().getLatLng();
            mMap.addMarker(new MarkerOptions()
                    .position(placeLatLng)
                    .title(placeName)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLocation, 17));
    }

    @Override
    public void onStop(){
        Toast.makeText(getActivity(), "MapViewFragment onStop().", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    public void onDestroyView(){
        Toast.makeText(getActivity(), "MapViewFragment onDestroyView().", Toast.LENGTH_LONG).show();
        super.onDestroyView();
    }

    @Override
    public void onResume(){
        super.onResume();
        Toast.makeText(getActivity(), "MapViewFragment onResume().", Toast.LENGTH_LONG).show();

    }

}