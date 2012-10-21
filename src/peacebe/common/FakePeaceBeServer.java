package peacebe.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

public class FakePeaceBeServer implements IPeaceBeServer {
	private JSONArray mProcesses;
	private String mStringBitmap;
	private String mVote;
	private String mStringProfileBitmap;
	private int stateCountBound = 10;
	private int stateCount = 0;
	private int ctrState=0;
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
	boolean[][] isProfiledFake =
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
	int ctrIsProfiledFake = 0;
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
	public FakePeaceBeServer(){
		mProcesses = new JSONArray();
		JSONObject map;
		try {
			map = new JSONObject();
			map.put("app", "main");
    		map.put("state", "stop");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "profiling");
    		map.put("state", "profiling");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "profiling");
    		map.put("state", "profiling");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "profiling");
    		map.put("state", "profiling");
    		mProcesses.put(map);
			map = new JSONObject();
			map.put("app", "main");
    		map.put("state", "stop");
    		mProcesses.put(map);
    		map = new JSONObject();
    		map.put("app", "grouping");
    		map.put("state", "painting");
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
    		map.put("app", "grouping");
    		map.put("state", "result");
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

	private void nextState(){
		stateCount = 0;
		if (ctrState < mProcesses.length()-1){
			ctrState ++;
		}
	}
	@Override
	public void sendVote(String id) {
		// TODO Auto-generated method stub
		mVote = id;
		nextState();
		
	}
	@Override
	public void sendProfile(Bitmap bitmap) {
		mStringProfileBitmap = Helper.getStringFromBitmap(bitmap);
		nextState();
	}
	@Override
	public void sendPaint(Bitmap bitmap) {
		// TODO Auto-generated method stub
		mStringBitmap = Helper.getStringFromBitmap(bitmap);
		nextState();
	}
	public void setProcess(JSONArray processes){
		mProcesses = processes;
	}
	@Override
	public void StartVote() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void StartGrouping() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void StartResult() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void StartProfiling(){
		
	}
	@Override
	public void StartFinish() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void StartProfilingFinish(){
		
	}

	@Override
	public JSONObject getState(){
		if (stateCount < stateCountBound){
			stateCount ++;
		} else {
			nextState();
		}
		JSONObject result = null;
		try {
			result = mProcesses.getJSONObject(ctrState);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	@Override
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
	@Override
	public JSONObject getGroupingResult() {
		// TODO Auto-generated method stub
		JSONObject m = new JSONObject();
		try {
			m.put("id", mVote);
			m.put("name", "jack");
			m.put("photo", mStringProfileBitmap);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	@Override
	public JSONArray getCandidate(){
		// TODO Auto-generated method stub
		//LinkedList<HashMap> candidate = new LinkedList();
		//HashMap map= new HashMap();
		JSONObject map = new JSONObject();
		JSONArray candidate = new JSONArray();
		try {
			for (int i = 4; i < 8; i++){
				map = new JSONObject();
				map.put("paint", mStringBitmap);
				map.put("id", Integer.toString(i));
				candidate.put(map);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidate;
	}
	@Override
	public JSONArray getProfiled(){
		// TODO Auto-generated method stub
		boolean [] result = isProfiledFake[ctrIsProfiledFake];
		if (ctrIsProfiledFake < isProfiledFake.length-1)
		{
			ctrIsProfiledFake++;
		}
		JSONArray players = new JSONArray();
		try {
			JSONObject m;
			for (int i = 0; i < 4 ; i++){
				m = new JSONObject();
				m.put("id", Integer.toString(i));
				m.put("group", "boy");
				if (result[i]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
				players.put(m);		
			}
			for (int i = 4; i < 8; i++){
				m = new JSONObject();
				m.put("id", Integer.toString(i));
				m.put("group", "girl");
				if (result[4]==true){m.put("state", "w_profiling");} else {m.put("state", "profiling");}
				players.put(m);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	@Override
	public JSONArray getPainted() {
		// TODO Auto-generated method stub
		boolean [] result = isPaintFake[ctrIsPaintFake];
		if (ctrIsPaintFake < isPaintFake.length-1)
		{
			ctrIsPaintFake++;
		}
		JSONArray players = new JSONArray();
		try {
			JSONObject m;
			for (int i = 0; i < 4 ; i++){
				m = new JSONObject();
				m.put("id", Integer.toString(i));
				m.put("group", "boy");
				if (result[i]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
				players.put(m);		
			}
			for (int i = 4; i < 8; i++){
				m = new JSONObject();
				m.put("id", Integer.toString(i));
				m.put("group", "girl");
				if (result[4]==true){m.put("state", "w_painting");} else {m.put("state", "painting");}
				players.put(m);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
		
	}
	@Override
	public JSONArray getVoted() {
		// TODO Auto-generated method stub
		int[] result = voteFake[ctrVoteFake];
		if (ctrVoteFake < voteFake.length-1)
		{
			ctrVoteFake++;
		}
		JSONArray players = new JSONArray();
		try {
			JSONObject m;
			for (int i = 0; i < 4 ; i++){
				m = new JSONObject();
				m.put("id", Integer.toString(i));
				m.put("group", "boy");
				m.put("vote", result[i]);
				players.put(m);		
			}
			for (int i = 4; i < 8; i++){
				m = new JSONObject();
				m.put("id", Integer.toString(i));
				m.put("group", "girl");
				m.put("vote", result[i]);
				players.put(m);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	@Override
	public JSONArray getTotalResult() {
		// TODO Auto-generated method stub
		int[] result =  voteResultFake;
		JSONArray players = new JSONArray();
		try {
			JSONObject m;
			for (int i = 0; i < 4 ; i++){
				m = new JSONObject();
				m.put("id", Integer.toString(i));
				m.put("group", "boy");
				m.put("result", result[i]);
				players.put(m);		
			}
			for (int i = 4; i < 8; i++){
				m = new JSONObject();
				m.put("id", Integer.toString(i));
				m.put("group", "girl");
				m.put("result", result[i]);
				players.put(m);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	@Override
	public void setPlayer(String player) {
		// TODO Auto-generated method stub
	}
	@Override
	public JSONObject getTeamByGUID(String guid) {
		// TODO Auto-generated method stub
		JSONObject m = new JSONObject();
		try {
			m.put("id", "1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	@Override
	public void setTeam(String team) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public JSONObject getPlayerByGUID(String guid) {
		JSONObject m = new JSONObject();
		try {
			m.put("id", "1");
			m.put("tid", "1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
}
