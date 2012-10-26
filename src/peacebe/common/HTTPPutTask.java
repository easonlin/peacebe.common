package peacebe.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import peacebe.common.Helper.URLPair;

public class HTTPPutTask extends AsyncTask<URLPair, Integer, JSONObject> {
	@Override 
	protected JSONObject doInBackground(URLPair... urls) {
		URLPair urlPair = urls[0];
		String url = urlPair.url;
		Log.i("HTTP", "httpPut " + url);
		JSONObject result = null;
		JSONObject content = urlPair.content;
		// TODO Auto-generated method stub			
		HttpClient httpClient = new DefaultHttpClient();    
        HttpPut httpPut = new HttpPut(url);
		try {
			httpPut.setEntity(new ByteArrayEntity(content.toString().getBytes("UTF8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		httpPut.setHeader("Content-Type", "application/json");
        HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpPut);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.i("NET","Failed to connect server");
			return null;
		}    	
		if(httpResponse.getStatusLine().getStatusCode() == 200){ 
    		String strResult = null;
			try {
				strResult = EntityUtils.toString(httpResponse.getEntity());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}		        	 		

			try {
				result = new JSONObject(strResult);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.i("JSON","Can't transfer json form respone");
				return new JSONObject();
			}	     
			return result;
    	} 
    	else 
    	{ 
    		Log.d("http", "Error Response: " + httpResponse.getStatusLine().toString());
    		return null;
    	}
     }
}
