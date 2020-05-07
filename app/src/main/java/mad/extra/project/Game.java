package mad.extra.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Game extends AppCompatActivity {

    ///////////////////////////////////////////////////
    public void initRotateBgImg() {
        RotateAnimation animR = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animR.setInterpolator(new LinearInterpolator());
        animR.setRepeatCount(Animation.INFINITE);
        animR.setDuration(60000);

        RotateAnimation animL = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animL.setInterpolator(new LinearInterpolator());
        animL.setRepeatCount(Animation.INFINITE);
        animL.setDuration(60000);

        ImageView imgV = (findViewById(R.id.guess));
        ImageView imgU = (findViewById(R.id.boom));

        imgV.setScaleX(2); imgV.setScaleY(2);
        imgU.setScaleX(2); imgU.setScaleY(2);

        imgV.startAnimation(animR);
        imgU.startAnimation(animL);
    }
    //////////////////////////////////////////////////

    private Integer heart = 0;

    public Integer globalNumber;
    private Button returnButton;

    private Integer min = 0;
    private Integer max = 100;

    private void showSuccess() {
        ImageView myImgView = findViewById(R.id.success);
        myImgView.setVisibility(View.VISIBLE);
        myImgView.setImageResource(R.drawable.success);

        returnButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initRotateBgImg();

        returnButton = findViewById(R.id.homepage2);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHomePage();
            }
        });

        Random rand  = new Random();
        globalNumber = rand.nextInt(100);
    }

    private String compareInput(String input) {
        int parseNumber;
        try {
            parseNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return "Enter Number Only! Don't Enter other STRINGS!";
        }
        if(parseNumber > 100 || parseNumber < 0) {
            return "Guessing Number is Smaller than 100 & Larger than 0!";
        }
        if(parseNumber == globalNumber) {
            showSuccess();
            return "Congratulation! You Got It. Result = " + parseNumber;
        } else if(parseNumber > globalNumber) {
            max = parseNumber;
            String string = "Your Input " + parseNumber + " is too Big. The Guess Should in " + min + " to " + max;
            return string;
        } else {
            min = parseNumber;
            return "Your Input " + parseNumber + " is too Small. The Guess Should in " + min + " to " + max;
        }
    }

    public void processCheck(View view) {
        EditText input = findViewById(R.id.inputNumber);
        TextView showInput = findViewById(R.id.showInput);

        String iString = String.valueOf(input.getText());
        showInput.setText("Your Input: " + iString);

        TextView result = findViewById(R.id.result);
        result.setText(compareInput(iString));
    }

    public void backHomePage() {
        Intent hIntent = new Intent(this, MainActivity.class);
        startActivity(hIntent);
    }
}