package peacebe.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import peacebe.common.Helper.URLPair;

public class HTTPPutTask extends AsyncTask<URLPair, Integer, Integer> {
	@Override 
	protected Integer doInBackground(URLPair... urls) {
		URLPair urlPair = urls[0];
		String url = urlPair.url;
		Log.i("HTTP", "httpPut " + url);
		JSONObject content = urlPair.content;
		// TODO Auto-generated method stub			
		HttpClient httpClient = new DefaultHttpClient();    
        HttpPut httpPut = new HttpPut(url);
		try {
			httpPut.setEntity(new ByteArrayEntity(content.toString().getBytes("UTF8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPut.setHeader("Content-Type", "application/json");
        HttpResponse response = null;
		try {
			response = httpClient.execute(httpPut);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("http", Integer.toString(response.getStatusLine().getStatusCode()));
		return 0;
     }
}
