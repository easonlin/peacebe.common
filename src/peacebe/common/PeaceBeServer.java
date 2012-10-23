package peacebe.common;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PeaceBeServer extends FakePeaceBeServer {
	private String mPlayer = "1";
	private String mTeam = "1";
	final private String mBaseURL = "http://175.41.156.14";	
	public static IPeaceBeServer factoryGet(){
		//return new FakePeaceBeServer();
		return new PeaceBeServer();
	}
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#setPlayer(int)
	 */
	@Override
	public void setPlayer(String player){
		mPlayer = player;
	}
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#getState()
	 */
	@Override
	public JSONObject getState() {
		// TODO Auto-generated method stub
		return Helper.httpGet(mBaseURL+"/app/main/player/"+mPlayer+"/state");
	}
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#getCandidate()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#getGroupingResult()
	 */
	@Override
	public JSONObject getGroupingResult() {
		// TODO Auto-generated method stub
		return Helper.httpGet(mBaseURL+"/app/grouping/player/"+mPlayer+"/result");
	}
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#sendPaint(android.graphics.Bitmap)
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#sendProfile(android.graphics.Bitmap)
	 */
	@Override
	public void sendProfile(Bitmap bitmap) {
		// TODO Auto-generated method stub
		String stringBitmap = Helper.getStringFromBitmap(bitmap);
		JSONObject content = new JSONObject();
		try {
			content.put("photo", stringBitmap);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.httpPut(mBaseURL+"/app/profiling/player/"+mPlayer+"/photo", content);
	}
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#sendVote(int)
	 */
	@Override
	public void sendVote(String id) {
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

	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#StartProfiling()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#StartProfilingFinish()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#getProfiled()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#StartGrouping()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#StartVote()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#StartResult()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#StartFinish()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#getPainted()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#getVoted()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#getTotalResult()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see peacebe.common.IPeaceBeServer#getTeamState()
	 */
	@Override
	public JSONObject getTeamState() {
		// TODO Auto-generated method stub
		return Helper.httpGet(mBaseURL+"/app/main/team/"+mTeam+"/state");
	}
	@Override
	public void setTeam(String team) {
		// TODO Auto-generated method stub
		mTeam = team;
	}
}
