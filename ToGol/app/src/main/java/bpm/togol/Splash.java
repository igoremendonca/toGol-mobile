package bpm.togol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Igor on 25/04/2016.
 */
public class Splash extends Activity {

    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        image = (ImageView) findViewById(R.id.imagem);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        image.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.setAnimation(animation2);
                finish();
                Bundle args = new Bundle();
                args.putString("key", "value");
                Intent main = new Intent(getBaseContext(), MainActivity.class);
                main.putExtra("key", "value");
                startActivity(main);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}
