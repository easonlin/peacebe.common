package peacebe.common;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PeaceBeServer {
	private int mPlayer = 1;
	private int mTeam = 1;
	final private String mBaseURL = "http://175.41.156.14";	
	public static PeaceBeServer factoryGet(){
		return new FakePeaceBeServer();
		//return new PeaceBeServer();
	}
	public void setPlayer(int player){
		mPlayer = player;
	}
	public JSONObject getState() {
		// TODO Auto-generated method stub
		return Helper.httpGet(mBaseURL+"/app/main/player/"+mPlayer+"/state");
	}
	public JSONArray getCandidate() {
		// TODO Auto-generated method stub
		try {
			return Helper.httpGet(mBaseURL+"/app/grouping/player/"+mPlayer+"/candidate").getJSONArray("players");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public JSONObject getGroupingResult() {
		// TODO Auto-generated method stub
		return Helper.httpGet(mBaseURL+"/app/grouping/player/"+mPlayer+"/result");
	}
	public void sendPaint(Bitmap bitmap) {
		// TODO Auto-generated method stub
		String stringBitmap = Helper.getStringFromBitmap(bitmap);
		JSONObject content = new JSONObject();
		try {
			content.put("paint", stringBitmap);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.httpPut(mBaseURL+"/app/grouping/player/"+mPlayer+"/paint", content);
	}
	public void sendVote(int id) {
		// TODO Auto-generated method stub
		JSONObject content = new JSONObject();
		try {
			content.put("player", id);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.httpPut(mBaseURL+"/app/grouping/player/"+mPlayer+"/vote", content);		
	}

	public void StartProfiling() {
		// TODO Auto-generated method stub
		JSONObject content = new JSONObject();
        try {
        	content.put("app", "profiling");
        	content.put("state", "start");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("RUN", "startprofiling");
        Helper.httpPut(mBaseURL+"/app/main/team/"+mTeam+"/state", content);
	}
	public void StartProfilingFinish(){
		// TODO Auto-generated method stub
		JSONObject content = new JSONObject();
        try {
        	content.put("app", "profiling");
        	content.put("state", "stop");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        Helper.httpPut(mBaseURL+"/app/main/team/"+mTeam+"/state", content);
	}
	public JSONArray getProfiled() {
		// TODO Auto-generated method stub
		JSONObject result = Helper.httpGet(mBaseURL+"/app/profiling/team/"+mTeam+"/photo");
		JSONArray players = null;
		try {
			players = result.getJSONArray("players");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	public void StartGrouping() {
		// TODO Auto-generated method stub
		JSONObject content = new JSONObject();
        try {
        	content.put("app", "grouping");
        	content.put("state", "start");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("RUN", "startgrouping");
        Helper.httpPut(mBaseURL+"/app/main/team/"+mTeam+"/state", content);
	}
	public void StartVote() {
		// TODO Auto-generated method stub
		JSONObject content = new JSONObject();
        try {
        	content.put("app", "grouping");
        	content.put("state", "vote");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        Helper.httpPut(mBaseURL+"/app/main/team/"+mTeam+"/state", content);
	}
	public void StartResult() {
		// TODO Auto-generated method stub
		JSONObject content = new JSONObject();
        try {
        	content.put("app", "grouping");
        	content.put("state", "result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        Helper.httpPut(mBaseURL+"/app/main/team/"+mTeam+"/state", content);
	}
	public void StartFinish() {
		// TODO Auto-generated method stub
		JSONObject content = new JSONObject();
        try {
        	content.put("app", "grouping");
        	content.put("state", "stop");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        Helper.httpPut(mBaseURL+"/app/main/team/"+mTeam+"/state", content);
	}
	public JSONArray getPainted() {
		// TODO Auto-generated method stub
		JSONObject result = Helper.httpGet(mBaseURL+"/app/grouping/team/"+mTeam+"/paint");
		JSONArray players = null;
		try {
			players = result.getJSONArray("players");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	public JSONArray getVoted() {
		// TODO Auto-generated method stub
		JSONObject result = Helper.httpGet(mBaseURL+"/app/grouping/team/"+mTeam+"/vote");
		JSONArray players = null;
		try {
			players = result.getJSONArray("players");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	public JSONArray getTotalResult() {
		// TODO Auto-generated method stub
		JSONObject result = Helper.httpGet(mBaseURL+"/app/grouping/team/"+mTeam+"/result");
		JSONArray players = null;
		try {
			players = result.getJSONArray("players");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	public JSONObject getTeamState() {
		// TODO Auto-generated method stub
		return Helper.httpGet(mBaseURL+"/app/main/team/"+mTeam+"/state");
	}
}
