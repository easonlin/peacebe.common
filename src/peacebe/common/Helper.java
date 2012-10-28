package peacebe.common;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;

public class Helper {
	public static class URLPair {
		public String url;
		public JSONObject content;
	}

	public static JSONObject httpGet(String url)  {
		JSONObject result = null;
		try {
			 result =  new HTTPGetTask().execute(url).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	public static JSONObject httpPut(String url, JSONObject content){
		URLPair urlPair = new URLPair();
		urlPair.url = url;
		urlPair.content = content;
		JSONObject obj=null;
		for(int i=0;i<3;i++){
			try {
				obj = new HTTPPutTask().execute(urlPair).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (obj != null){
				break;
			}
			SystemClock.sleep(5000);
		}
		return obj;
	}
	public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}
	public static String getStringFromBitmap(Bitmap bitmapPicture) {
		 /*
		 * This functions converts Bitmap picture to a string which can be
		 * JSONified.
		 * */
		 final int COMPRESSION_QUALITY = 100;
		 String encodedImage;
		 ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
		 bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY, byteArrayBitmapStream);
		 byte[] b = byteArrayBitmapStream.toByteArray();
		 encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
		 Log.i("bitmap","[ENCODE]" + encodedImage);
		 return encodedImage;
	}
	public static Bitmap getBitmapFromString(String stringPicture) {
		/*
		* This Function converts the String back to Bitmap
		* */
		Log.i("bitmap","[ENCODE LOAD]" + stringPicture);
		byte[] decodedString = Base64.decode(stringPicture, Base64.DEFAULT);
		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		return decodedByte;
	}
}
