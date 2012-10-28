package peacebe.common;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;

public interface IPeaceBeServer {
	
	public abstract JSONObject getTeamByGUID(String guid);
	
	public abstract JSONObject getPlayerByGUID(String guid);
	
	public abstract void setTeam(String team);
	
	public abstract void setPlayer(String player);

	public abstract JSONObject getState();

	public abstract JSONArray getCandidate();

	public abstract JSONObject getGroupingResult();

	public abstract void sendPaint(Bitmap bitmap);

	public abstract void sendProfilePhoto(Bitmap bitmap);
	
	public abstract void sendProfile(Bitmap photo, String male, String name);

	public abstract void sendVote(String id);

	public abstract JSONObject StartProfiling();

	public abstract void StartProfilingFinish();

	public abstract JSONArray getProfiled();

	public abstract JSONObject StartGrouping();

	public abstract void StartVote();

	public abstract void StartResult();

	public abstract void StartFinish();

	public abstract JSONArray getPainted();

	public abstract JSONArray getVoted();

	public abstract JSONArray getTotalResult();

	public abstract JSONObject getTeamState();
	
	public abstract JSONArray getOpenedTeams();

	public abstract void sendProfileVerifyOk(String id);
	
	public abstract void sendProfileVerifyDeny(String id);
	
	public abstract void sendJoin(String tid);
	
	public abstract void registerPlayer();
	
	public abstract void registerTeam();

}