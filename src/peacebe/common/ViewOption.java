package peacebe.common;

import android.view.Menu;
import android.view.MenuItem;

public interface ViewOption {
	public abstract boolean onOptionsItemSelected(MenuItem item);
	public abstract void onPrepareOptionsMenu(Menu menu);
}
