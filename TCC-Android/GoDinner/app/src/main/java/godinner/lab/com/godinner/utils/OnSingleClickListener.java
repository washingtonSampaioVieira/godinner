package godinner.lab.com.godinner.utils;

import android.view.View;

public abstract class OnSingleClickListener implements View.OnClickListener {

    private boolean mClickable = true;

    @Override
    public void onClick(View v) {
        if (mClickable) {
            mClickable = false;
            onSingleClick(v);
        }
    }

    public abstract void onSingleClick(View v);

    protected void reset() {
        mClickable = true;
    }
}
