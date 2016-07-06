package bpm.togol;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import bpm.model.Event;
import bpm.model.Game;
import bpm.util.DateUtil;
import bpm.util.JsonConverter;

import static bpm.util.Constants.EVENT_KEY;

/**
 * Created by Igor on 27/05/2016.
 */
public class EventScreen extends Activity {

    private TextView competitionName;
    private TextView competitionPlace;
    private TextView competitionHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_screen);

        competitionName = (TextView) findViewById(R.id.competitionName);
        competitionPlace = (TextView) findViewById(R.id.competitionPlace);
        competitionHour = (TextView) findViewById(R.id.competitionHour);

        getParameters();
//        getCompetitionInfo();
    }

    private void getParameters() {
        String competitionString = getIntent().getExtras().getString(EVENT_KEY);
        Event event = JsonConverter.fromJson(competitionString, Event.class);

        competitionName.setText(event.getCompetition().getName());
        competitionPlace.setText(event.getEventLocation().getPlace());
        loadGames(event.getGames());
    }

    private void loadGames(List<Game> games) {

        LinearLayout containerItems = (LinearLayout) findViewById(R.id.eventsLayout);
        for (Game game : games) {
            View item = getLayoutInflater().inflate(R.layout.item_team, null);
            TextView hour = (TextView) item.findViewById(R.id.hour);
            TextView team1 = (TextView) item.findViewById(R.id.teams1);
            TextView team2 = (TextView) item.findViewById(R.id.teams2);
            TextView versus = (TextView) item.findViewById(R.id.versus);

            hour.setText(game.getHour());
            team1.setText(game.getTeams().get(0).getName());
            team2.setText(game.getTeams().get(1).getName());
            versus.setText("X");

            containerItems.addView(item);
        }

    }

//    public void getCompetitionInfo() {
//        //implementar a busca por informações sobre a competição e os jogadores
//
//    }
}
