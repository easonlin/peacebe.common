package peacebe.common;

import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Setting{
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	public Setting(Activity c){
		settings = c.getPreferences(Context.MODE_WORLD_WRITEABLE);
		editor = settings.edit();
	}
	private String getValueWithUUID(String key){
		String new_value = UUID.randomUUID().toString();
		String value=null;
		try {
			value = settings.getString(key, new_value);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		if(value==null || value.equals(new_value)){
			editor.putString(key, new_value);
			editor.commit();
		}
		return value;		
	}
	public String getTeam(){
		return getValueWithUUID("team");
	}
	public String getPlayer() {
		return getValueWithUUID("player");
	}
};