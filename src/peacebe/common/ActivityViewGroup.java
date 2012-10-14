package peacebe.common;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public abstract class ActivityViewGroup extends ViewGroup {

	public ActivityViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public abstract boolean onOptionsItemSelected(MenuItem item);
	public abstract void onPrepareOptionsMenu(Menu menu);
}
