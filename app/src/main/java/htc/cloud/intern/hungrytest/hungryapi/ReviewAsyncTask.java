package htc.cloud.intern.hungrytest.hungryapi;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by intern on 8/16/15.
 */
public class ReviewAsyncTask extends AsyncTask<String, Void, JSONArray> {

    public final static String apiURL = "https://recornot.herokuapp.com/";
    public final static String serviceName = "reviews";
    public final static String businessIdField = "business_id";
    public final static String jsonArrayName = "reviews";

    private AsyncResponse responseDelegate;
    private URL url;

    public void setResponseDelegate(AsyncResponse rd){
        responseDelegate = rd;
    }

    @Override
    protected JSONArray doInBackground(String... businessId) {

        URLConnection urlConnection;
        InputStream inputStream;
        StringWriter strWriter;
        String jsonString;
        JSONArray jsonArray = null;

        try {
            url = new URL(apiURL+serviceName+
                    "?"+businessIdField+"="+businessId[0]);

            urlConnection = (HttpsURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
            strWriter = new StringWriter();
            IOUtils.copy(inputStream, strWriter, (String) null);
            jsonString = strWriter.toString();
            jsonArray = (new JSONObject(jsonString)).getJSONArray(jsonArrayName);

            Log.i("hungry-api", "reviewAPI: " + jsonArray.length());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    protected void onPostExecute(JSONArray jsonArray) {
        responseDelegate.onPostExecute(jsonArray);
    }

}

