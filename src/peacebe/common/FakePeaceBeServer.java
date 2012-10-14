package peacebe.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

public class FakePeaceBeServer extends PeaceBeServer {
	private JSONArray mProcesses;
	private int mIndex=0;
	private String mStringBitmap;
	private int mVote;
	public FakePeaceBeServer(){
		mProcesses = new JSONArray();
		JSONObject map = new JSONObject();
		try {
			map.put("app", "main");
    		map.put("state", "stop");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "grouping");
    		map.put("state", "painting");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "grouping");
    		map.put("state", "w_painting");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "grouping");
    		map.put("state", "voting");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "grouping");
    		map.put("state", "w_voting");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "grouping");
    		map.put("state", "result");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "main");
    		map.put("state", "stop");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "main");
    		map.put("state", "stop");
    		mProcesses.put(map);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public JSONObject getTeamState(){
		JSONObject m = new JSONObject();
		try {
			m.put("app", "main");
    		m.put("state", "stop");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	public JSONObject getGroupingResult() {
		// TODO Auto-generated method stub
		JSONObject m = new JSONObject();
		try {
			m.put("id", mVote);
    		m.put("name", "jack");
    		m.put("photo", Helper.getBitmapFromString(mStringBitmap));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	public void sendVote(int id) {
		// TODO Auto-generated method stub
		mVote = id;
		
	}
	public JSONArray getCandidate(){
		// TODO Auto-generated method stub
		//LinkedList<HashMap> candidate = new LinkedList();
		//HashMap map= new HashMap();
		JSONObject map = new JSONObject();
		JSONArray candidate = new JSONArray();
		try {
			map.put("paint", mStringBitmap);
			map.put("id", 5);
			candidate.put(map);
			map= new JSONObject();
			map.put("paint", mStringBitmap);
			map.put("id", 6);
			candidate.put(map);
			map= new JSONObject();
			map.put("paint", mStringBitmap);
			map.put("id", 7);
			candidate.put(map);
			map= new JSONObject();
			map.put("paint", mStringBitmap);
			map.put("id", 8);
			candidate.put(map);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidate;
	}
	public void sendPaint(Bitmap bitmap) {
		// TODO Auto-generated method stub
		mStringBitmap = Helper.getStringFromBitmap(bitmap);
	}
	public void setProcess(JSONArray processes){
		mProcesses = processes;
	}
	public JSONObject getState(){
		try {
			JSONObject result = mProcesses.getJSONObject(mIndex);
			mIndex ++;
			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
    boolean[][] isPaintFake =
    	{
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, true, false, false, false, false},
    		{false, false, false, true, false, false, false, false},
    		{false, false, false, true, false, false, true, false},
    		{false, false, false, true, false, true, true, false},
    		{true, false, false, true, false, true, true, false},
    		{true, false, false, true, false, true, true, false},
    		{true, false, false, true, false, true, true, false},
    		{true, true, false, true, false, true, true, false},
    		{true, true, false, true, false, true, true, false},
    		{true, true, false, true, true, true, true, false},
    		{true, true, true, true, true, true, true, false},
    		{true, true, true, true, true, true, true, true},
    	};
    boolean[][] _isPaintFake =
    	{
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, false, false, false, false, false},
    		{false, false, false, false, false, false, false, false},
    		{true, false, false, false, false, false, false, false},
    		{true, false, false, false, false, false, false, false},
    		{true, true, false, false, false, false, false, false},
    		{true, true, true, false, false, false, false, false},
    		{true, true, true, true, false, false, false, false},
    		{true, true, true, true, false, false, false, false},
    		{true, true, true, true, false, false, false, false},
    		{true, true, true, true, true, false, false, false},
    		{true, true, true, true, true, true, false, false},
    		{true, true, true, true, true, true, true, false},
    		{true, true, true, true, true, true, true, true},
    	};
    int ctrIsPaintFake = 0;
    int[][] voteFake =
    	{
    		{-1, -1, -1, -1, -1, -1, -1, -1},
    		{-1, -1, -1, -1, -1, -1, -1, -1},
    		{-1, -1, -1, -1, -1, -1, -1, -1},
    		{-1, -1, -1, -1, -1, -1, -1, -1},
    		{-1, -1, -1, -1, -1, -1, -1, -1},
    		{-1, -1, -1, -1, -1, -1, -1, -1},
    		{-1, -1, -1, 4, -1, 1, -1, -1},
    		{-1, -1, 1, 4, -1, 1, -1, -1},
    		{-1, -1, 1, 4, -1, 1, -1, -1},
    		{-1, -1, 1, 4, -1, 1, 2, 3},
    		{-1, -1, 1, 4, -1, 1, 2, 3},
    		{1, -1, 1, 4, -1, 1, 2, 3},
    		{1, 3, 1, 4, 1, 1, 2, 3}
    	};
    int ctrVoteFake = 0;
    int[] voteResultFake = {1, 3, 2, 4, 3, 1, 4, 2};
	public void StartVote() {
		// TODO Auto-generated method stub
		
	}
	public void StartGrouping() {
		// TODO Auto-generated method stub
		
	}
	public void StartResult() {
		// TODO Auto-generated method stub
		
	}
	public void StartProfiling(){
		
	}
	public void StartProfilingFinish(){
		
	}
	public JSONArray getProfiled(){
		// TODO Auto-generated method stub
		boolean [] result = isPaintFake[ctrIsPaintFake];
		if (ctrIsPaintFake < isPaintFake.length-1)
		{
			ctrIsPaintFake++;
		}
		JSONArray players = new JSONArray();
		try {
		JSONObject m = new JSONObject();
		m.put("id", 1);
		m.put("group", "boy");
		if (result[0]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 2);
		m.put("group", "boy");
		if (result[1]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 3);
		m.put("group", "boy");
		if (result[2]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 4);
		m.put("group", "boy");
		if (result[3]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 5);
		m.put("group", "girl");
		if (result[4]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 6);
		m.put("group", "girl");
		if (result[5]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 7);
		m.put("group", "girl");
		if (result[6]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 8);
		m.put("group", "girl");
		if (result[7]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
		players.put(m);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	public JSONArray getPainted() {
		// TODO Auto-generated method stub
		boolean [] result = isPaintFake[ctrIsPaintFake];
		if (ctrIsPaintFake < isPaintFake.length-1)
		{
			ctrIsPaintFake++;
		}
		JSONArray players = new JSONArray();
		try {
		JSONObject m = new JSONObject();
		m.put("id", 1);
		m.put("group", "boy");
		if (result[0]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 2);
		m.put("group", "boy");
		if (result[1]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 3);
		m.put("group", "boy");
		if (result[2]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 4);
		m.put("group", "boy");
		if (result[3]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 5);
		m.put("group", "girl");
		if (result[4]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 6);
		m.put("group", "girl");
		if (result[5]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 7);
		m.put("group", "girl");
		if (result[6]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
		players.put(m);
		m = new JSONObject();
		m.put("id", 8);
		m.put("group", "girl");
		if (result[7]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
		players.put(m);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
		
	}
	public void StartFinish() {
		// TODO Auto-generated method stub
		
	}
	public JSONArray getVoted() {
		// TODO Auto-generated method stub
		int[] result = voteFake[ctrVoteFake];
		if (ctrVoteFake < voteFake.length-1)
		{
			ctrVoteFake++;
		}
		JSONArray players = new JSONArray();
		try {
		JSONObject m = new JSONObject();
		m.put("id", 1);
		m.put("group", "boy");
		m.put("vote", result[0]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 2);
		m.put("group", "boy");
		m.put("vote", result[1]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 3);
		m.put("group", "boy");
		m.put("vote", result[2]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 4);
		m.put("group", "boy");
		m.put("vote", result[3]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 5);
		m.put("group", "girl");
		m.put("vote", result[4]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 6);
		m.put("group", "girl");
		m.put("vote", result[5]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 7);
		m.put("group", "girl");
		m.put("vote", result[6]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 8);
		m.put("group", "girl");
		m.put("vote", result[7]);
		players.put(m);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	public JSONArray getTotalResult() {
		// TODO Auto-generated method stub
		int[] result =  voteResultFake;
		JSONArray players = new JSONArray();
		try {
		JSONObject m = new JSONObject();
		m.put("id", 1);
		m.put("group", "boy");
		m.put("result", result[0]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 2);
		m.put("group", "boy");
		m.put("result", result[1]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 3);
		m.put("group", "boy");
		m.put("result", result[2]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 4);
		m.put("group", "boy");
		m.put("result", result[3]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 5);
		m.put("group", "girl");
		m.put("result", result[4]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 6);
		m.put("group", "girl");
		m.put("result", result[5]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 7);
		m.put("group", "girl");
		m.put("result", result[6]);
		players.put(m);
		m = new JSONObject();
		m.put("id", 8);
		m.put("group", "girl");
		m.put("result", result[7]);
		players.put(m);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
}
