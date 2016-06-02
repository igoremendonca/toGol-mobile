package bpm.togol;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Igor on 27/05/2016.
 */
public class EventScreen extends Activity {

    private TextView competitionName;
    private TextView competitionPlace;
    private TextView competitionHour;
    private TextView teamsNames;

    private String competition;
    private String place;
    private String teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_screen);

        getParameters();

        competitionName = (TextView) findViewById(R.id.competitionName);
        competitionPlace = (TextView) findViewById(R.id.competitionPlace);
        competitionHour = (TextView) findViewById(R.id.competitionHour);
        teamsNames = (TextView) findViewById(R.id.teams);

        competitionName.setText(competition);
        competitionPlace.setText(place);
        teamsNames.setText(teams);

        getCompetitionInfo();
    }

    private void getParameters() {
        competition = getIntent().getExtras().getString("competitionName");
        place = getIntent().getExtras().getString("competitionPlace");
        teams = getIntent().getExtras().getString("times");
    }

    public void getCompetitionInfo() {
        //implementar a busca por informações sobre a competição e os jogadores

    }
}
