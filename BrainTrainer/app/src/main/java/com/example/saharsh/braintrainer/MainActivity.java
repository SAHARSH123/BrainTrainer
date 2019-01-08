package com.example.saharsh.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView timer,score,question,myanswer;
    private Button option1,option2,option3,option4;
    private ArrayList<Integer> answers=new ArrayList<Integer>();
    private int correct_answer;
    private int marks=0;
    private int totalquestion=0;
    MediaPlayer mediaPlayer;
    Random random;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        timer=(TextView) findViewById(R.id.timer);
        score=(TextView)findViewById(R.id.score);
        myanswer=(TextView)findViewById(R.id.myanswer);
        question=(TextView)findViewById(R.id.question);
        option1=(Button)findViewById(R.id.option1);
        option2=(Button)findViewById(R.id.option2);
        option3=(Button)findViewById(R.id.option3);
        option4=(Button)findViewById(R.id.option4);

        option1.setVisibility(View.INVISIBLE);
        option2.setVisibility(View.INVISIBLE);
        option3.setVisibility(View.INVISIBLE);
        option4.setVisibility(View.INVISIBLE);
        timer.setText("Timer");
        score.setText("Score");
        question.setText("Question");



    }

    public void gameStart(View view) {

        button.setVisibility(View.INVISIBLE);
        myanswer.setText("Answer");
        marks=0;
        totalquestion=0;
        random=new Random();
        int a=random.nextInt(2);
        if(a==0)
        generateQuestion();
        else
        generateSubtraction();
        timerStart();
    }

    private void generateSubtraction() {
        random=new Random();
        int a=random.nextInt(15);
        int b=random.nextInt(15);

        question.setText(Integer.toString(a)+" - "+Integer.toString(b));
        correct_answer=random.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==correct_answer){
                answers.add(a-b);
            }
            else {
                int no=random.nextInt(14+1+14)-14;
                while(no==a-b){
                    no=random.nextInt(14+1+14)-14;
                }
                answers.add(no);
            }
        }

        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));
    }

    private void generateQuestion() {
        random=new Random();
        int a=random.nextInt(25);
        int b=random.nextInt(25);

        question.setText(Integer.toString(a)+" + "+Integer.toString(b));
        correct_answer=random.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==correct_answer){
                answers.add(a+b);
            }
            else {
                int no=random.nextInt(49);
                while(no==a+b){
                    no=random.nextInt(49);
                }
                answers.add(no);
            }
        }

        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));


    }

    private void timerStart() {

        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);

        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Integer.toString((int)millisUntilFinished/1000));


            }

            @Override
            public void onFinish() {

                timer.setText("Done!");
                option1.setVisibility(View.INVISIBLE);
                option2.setVisibility(View.INVISIBLE);
                option3.setVisibility(View.INVISIBLE);
                option4.setVisibility(View.INVISIBLE);
                button.setText("Play Again");
                button.setVisibility(View.VISIBLE);
                timer.setText("Timer");
                score.setText("Score");
                question.setText("Question");
                myanswer.setText("Your Score "+Integer.toString(marks));
                Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
                mediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.gameover);
                mediaPlayer.start();






            }
        }.start();
    }

    public void checkAnswer(View view){
        totalquestion++;
        if(view.getTag().toString().equals(Integer.toString(correct_answer))){
            marks++;
            myanswer.setText("Correct!!");
            score.setText(Integer.toString(marks)+"/"+Integer.toString(totalquestion));

        }
        else {
            myanswer.setText("Wrong!!");
            score.setText(Integer.toString(marks)+"/"+Integer.toString(totalquestion));
        }
        random=new Random();
        int a=random.nextInt(2);
        if(a==0)
            generateQuestion();
        else
            generateSubtraction();


    }
}
