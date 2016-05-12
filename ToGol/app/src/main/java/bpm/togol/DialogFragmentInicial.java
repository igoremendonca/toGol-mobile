package bpm.togol;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;

/**
 * Created by Igor on 25/04/2016.
 */
public class DialogFragmentInicial extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);

        View rootView = inflater.inflate(R.layout.inicial_screen, container, false);
        getDialog().setTitle("Simple Dialog");

        return rootView;
    }

    public void onResume()
    {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(1000, 1300);
        window.setGravity(Gravity.CENTER);
        //TODO:
    }
}
