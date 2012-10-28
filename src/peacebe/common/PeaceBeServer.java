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
		JSONObject obj = Helper.httpGet(mBaseURL+"/app/grouping/player/"+mPlayer+"/candidate");
		JSONArray player = null;
		if(obj == null){
			return null;
		}
		try {
			player = obj.getJSONArray("players");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return player;
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
	public void sendProfilePhoto(Bitmap bitmap) {
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
	@Override
	public void sendProfile(Bitmap photo, String male, String name){
		JSONObject content = new JSONObject();
		if(photo!=null){
			try {
				String stringPhoto = Helper.getStringFromBitmap(photo);
				content.put("photo", stringPhoto);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			content.put("boy", male);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			content.put("name", name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.httpPut(mBaseURL+"/app/profiling/player/"+mPlayer+"/fill", content);
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
	public JSONObject StartProfiling() {
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
        return Helper.httpPut(mBaseURL+"/app/main/team/"+mTeam+"/state", content);
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
		///opt/trendmicro/sbin/saas_sd_ds_app_cli.py -s ec2-175-41-156-14.ap-southeast-1.compute.amazonaws.com \
		//-i /app/profiling/team/$COMMANDERID/player -m GET
		JSONObject result = Helper.httpGet(mBaseURL+"/app/profiling/team/"+mTeam+"/player");
		if (result == null){
			return null;
		}
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
	public JSONObject StartGrouping() {
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
        return Helper.httpPut(mBaseURL+"/app/main/team/"+mTeam+"/state", content);
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
		if (result == null){
			return null;
		}
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
		if(result==null){
			return null;
		}
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
		if (result==null){
			return null;
		}
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
	@Override
	public void sendProfileVerifyOk(String id) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		try {
			obj.put("pid", id);
			obj.put("verify", "ok");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.httpPut(mBaseURL+"/app/profiling/team/"+mTeam+"/verify", obj);
	}

	@Override
	public void sendProfileVerifyDeny(String id) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		try {
			obj.put("pid", id);
			obj.put("verify", "deny");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.httpPut(mBaseURL+"/app/profiling/team/"+mTeam+"/verify", obj);
	}
	@Override
	public void sendJoin(String tid) {
		// TODO Auto-generated method stub
		JSONObject content = new JSONObject();
		try {
			content.put("tid", tid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.httpPut(mBaseURL+"/app/profiling/player/"+mPlayer+"/join", content);
	}
	@Override
	public JSONArray getOpenedTeams() {
		// TODO Auto-generated method stub
		JSONObject result = Helper.httpGet(mBaseURL+"/app/profiling/player/"+mPlayer+"/team");
		if(result==null){
			return null;
		}
		JSONArray teams = null;
		try {
			teams = result.getJSONArray("teams");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teams;
	}
	@Override
	public void registerPlayer() {
		// TODO Auto-generated method stub
		///opt/trendmicro/sbin/saas_sd_ds_app_cli.py -s ec2-175-41-156-14.ap-southeast-1.compute.amazonaws.com \
		//-i /app/profiling/player/$PLAYERID/register -m PUT -c '{}' -o
		JSONObject content = new JSONObject();
		Helper.httpPut(mBaseURL+"/app/profiling/player/"+mPlayer+"/register", content);	
	}

	@Override
	public void registerTeam() {
		// TODO Auto-generated method stub
		///opt/trendmicro/sbin/saas_sd_ds_app_cli.py -s ec2-175-41-156-14.ap-southeast-1.compute.amazonaws.com \
		//-i /app/profiling/team/$COMMANDERID/register -m PUT -c '{}' -o
		JSONObject content = new JSONObject();
		Helper.httpPut(mBaseURL+"/app/profiling/team/"+mTeam+"/register", content);		
	}
}
