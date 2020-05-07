package mad.extra.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class ExplainTheGame extends AppCompatActivity {

    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain_the_game);
        returnButton = findViewById(R.id.homepage3);
        initRotateBgImg();
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHomePage();
            }
        });
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
        ImageView imgM = (findViewById(R.id.header_img));
        ImageView imgX = (findViewById(R.id.backgound2));

        imgV.setScaleX(2);
        imgV.setScaleY(2);
        imgM.setScaleX(2);
        imgM.setScaleY(2);
        imgX.setScaleX(2);
        imgX.setScaleY(2);

        imgM.startAnimation(animR);
        imgV.startAnimation(animR);
        imgX.startAnimation(animL);
    }

    public void backHomePage() {
        Intent hIntent = new Intent(this, MainActivity.class);
        startActivity(hIntent);
    }
}