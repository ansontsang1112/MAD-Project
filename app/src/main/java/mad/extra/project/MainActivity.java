package mad.extra.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Button startGame;
    private Button about;
    private Button explainGame;

    MediaPlayer myMus = null;
    @Override
    protected void onResume(){
        super.onResume();
        if (myMus != null) myMus.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        if (myMus != null) myMus.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeMoveThread();
        initRotateBgImg();

        myMus = MediaPlayer.create(this, R.raw.green);
        myMus.setLooping(true);

        Switch soundSw = findViewById(R.id.Sounds);
        soundSw.setChecked(true);
        soundSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (myMus != null) myMus.start();
                } else {
                    if (myMus != null) myMus.pause();
                }
            }
        });

        startGame = findViewById(R.id.game);
        about = findViewById(R.id.aboutme);
        explainGame = findViewById(R.id.explainGame);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutMe();
            }
        });
        explainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExplainGame();
            }
        });
    }

    public void openGame() {
        Intent sIntent = new Intent(this, Game.class);
        startActivity(sIntent);
    }

    public void openAboutMe() {
        Intent aIntent = new Intent(this, AboutMe.class);
        startActivity(aIntent);
    }

    public void openExplainGame() {
        Intent aIntent = new Intent(this, ExplainTheGame.class);
        startActivity(aIntent);
    }

    public void initRotateBgImg() {
        RotateAnimation animR = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animR.setInterpolator(new LinearInterpolator());
        animR.setRepeatCount(Animation.INFINITE);
        animR.setDuration(60000);

        RotateAnimation animL = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animL.setInterpolator(new LinearInterpolator());
        animL.setRepeatCount(Animation.INFINITE);
        animL.setDuration(60000);


        ImageView imgV = (findViewById(R.id.header_img));
        ImageView imgX = (findViewById(R.id.backgound));

        imgV.setScaleX(2); imgV.setScaleY(2);
        imgX.setScaleX(2); imgX.setScaleY(2);

        imgV.startAnimation(animR);
        imgX.startAnimation(animL);
    }

    void makeMoveThread() {
        final int DEF_SLEEP_GAP = 1000;
        final int DEF_MAX = 200;
        final int DEF_BASE = - DEF_MAX/2;
        new Thread(new Runnable() {
            @Override
            public void run(){
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        Thread.sleep(DEF_SLEEP_GAP);
                        final ImageButton imgB=findViewById(R.id.blackhole_png);
                        if (imgB==null) break;
                        imgB.post(new Runnable() {
                            public void run() {
                                int diffX = (DEF_BASE + (int) (Math.random() * DEF_MAX));
                                int diffY = (DEF_BASE + (int) (Math.random() * DEF_MAX));
                                imgB.setX(imgB.getX() + diffX);
                                imgB.setY(imgB.getY() + diffY);
                                imgB.setImageAlpha(128 + (int) (Math.random()*128));
                            }
                        });
                    } catch (InterruptedException ie) {}
                }
            }
        }).start();
    }
}