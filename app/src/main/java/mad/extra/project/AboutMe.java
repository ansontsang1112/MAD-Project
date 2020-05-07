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

public class AboutMe extends AppCompatActivity {

    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        returnButton = findViewById(R.id.homepage1);
        initRotateBgImg();
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHomePage();
            }
        });
    }

    public void backHomePage() {
        Intent hIntent = new Intent(this, MainActivity.class);
        startActivity(hIntent);
    }

    public void initRotateBgImg() {
        RotateAnimation animR = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animR.setInterpolator(new LinearInterpolator());
        animR.setRepeatCount(Animation.INFINITE);
        animR.setDuration(60000);

        ImageView imgV = (findViewById(R.id.header_img));

        imgV.setScaleX(2); imgV.setScaleY(2);

        imgV.startAnimation(animR);
    }
}