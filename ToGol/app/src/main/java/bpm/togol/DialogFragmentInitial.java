package bpm.togol;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static bpm.util.Constants.PLACE_KEY;

/**
 * Created by Igor on 25/04/2016.
 */
public class DialogFragmentInitial extends DialogFragment implements View.OnClickListener {

    private Context context;

    TextView place;

    public DialogFragmentInitial(){}

    public DialogFragmentInitial(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(true);

        View rootView = inflater.inflate(R.layout.inicial_screen, container, false);
        getDialog().setTitle("Title here");

        Button buttonGoTO = (Button) rootView.findViewById(R.id.buttonGoTo);
        buttonGoTO.setOnClickListener(this);

        place = (TextView) rootView.findViewById(R.id.etPlace);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        String localPlace = place.getText().toString();

        Intent main = new Intent(getContext(), MainActivity.class);
        main.putExtra(PLACE_KEY, localPlace);
        startActivity(main);
    }

    @Override
    public Context getContext() {
        return context;
    }
}
