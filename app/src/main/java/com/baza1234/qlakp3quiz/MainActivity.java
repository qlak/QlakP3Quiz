package com.baza1234.qlakp3quiz;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;



public class MainActivity extends AppCompatActivity {

    // Sets maximum number of Questions in the Quiz that random number generator uses as max range.
    int numberOfQuestions = 40;
    // Adds up to show the score when Quiz is done.
    int points = 0;
    // Fills the progress bars.
    int progressBars = 0;

    // Saves the Randomly Generated Questions, to avoid repeating same question 2 times.
    int savequestion1;
    int savequestion2;
    int savequestion3;
    int savequestion4;
    int savequestion5;

    // State of the Quiz.
    boolean quizdone = false;

    // Checks if the question was already answered - if not it fills the progress bar.
    boolean fillcheck1 = false;
    boolean fillcheck2 = false;
    boolean fillcheck3 = false;
    boolean fillcheck4 = false;
    boolean fillcheck5 = false;

    // Rank of the player based on points.
    String rank = "";

    // Saves the correct answers to compare them with answers provided by the Player.
    String[] correctanswer = new String[6];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Starts the cloudy logo animation.
        logoAnimateStart();

        // Sets new random set of questions.
        randomizeQuestions();
    }


    /**
     *   Starts the basic cloudy animation of the logo.
     */
    public void logoAnimateStart() {
        final AnimationDrawable logoAnimation = new AnimationDrawable();
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo00), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo01), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo02), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo03), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo04), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo05), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo06), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo07), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo08), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo09), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo10), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo11), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo12), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo13), 2000);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo14), 2000);
        ImageView logo = (ImageView) findViewById(R.id.steam);
        logo.setBackgroundDrawable(logoAnimation);
        // Start delayed to overwrite onCreate ImageView
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                logoAnimation.start();
            }
        }, 200);
    }


    /**
     *   Fills the bars by taking progressBars int.
     *   @param progressBars says how many answers were already checked in order to fill the progress bars.
     */
    public void updateBars(int progressBars){
        ProgressBar progress_bar_mid1 = (ProgressBar)findViewById(R.id.progress_bar_mid1);
        progress_bar_mid1.setProgress(progressBars);
        ProgressBar progress_bar_mid2 = (ProgressBar)findViewById(R.id.progress_bar_mid2);
        progress_bar_mid2.setProgress(progressBars);

        ProgressBar progress_bar_low1 = (ProgressBar)findViewById(R.id.progress_bar_low1);
        progress_bar_low1.setProgress(progressBars);
        ProgressBar progress_bar_low2 = (ProgressBar)findViewById(R.id.progress_bar_low2);
        progress_bar_low2.setProgress(progressBars);

        ProgressBar progress_bar_high1 = (ProgressBar)findViewById(R.id.progress_bar_high1);
        progress_bar_high1.setProgress(progressBars);
        ProgressBar progress_bar_high2 = (ProgressBar)findViewById(R.id.progress_bar_high2);
        progress_bar_high2.setProgress(progressBars);
    }


    /**
     *   Checks if the answer button is checked and fills the progress bars
     *   using progressBars parameter and updateBars method.
     */
    public void fillBars(View view){

        RadioButton q1a = (RadioButton) findViewById(R.id.q1a);
        RadioButton q1b = (RadioButton) findViewById(R.id.q1b);
        RadioButton q1c = (RadioButton) findViewById(R.id.q1c);
        RadioButton q1d = (RadioButton) findViewById(R.id.q1d);

        RadioButton q2a = (RadioButton) findViewById(R.id.q2a);
        RadioButton q2b = (RadioButton) findViewById(R.id.q2b);
        RadioButton q2c = (RadioButton) findViewById(R.id.q2c);
        RadioButton q2d = (RadioButton) findViewById(R.id.q2d);

        RadioButton q3a = (RadioButton) findViewById(R.id.q3a);
        RadioButton q3b = (RadioButton) findViewById(R.id.q3b);
        RadioButton q3c = (RadioButton) findViewById(R.id.q3c);
        RadioButton q3d = (RadioButton) findViewById(R.id.q3d);

        RadioButton q4a = (RadioButton) findViewById(R.id.q4a);
        RadioButton q4b = (RadioButton) findViewById(R.id.q4b);
        RadioButton q4c = (RadioButton) findViewById(R.id.q4c);
        RadioButton q4d = (RadioButton) findViewById(R.id.q4d);

        RadioButton q5a = (RadioButton) findViewById(R.id.q5a);
        RadioButton q5b = (RadioButton) findViewById(R.id.q5b);
        RadioButton q5c = (RadioButton) findViewById(R.id.q5c);
        RadioButton q5d = (RadioButton) findViewById(R.id.q5d);

        if(q1a.isChecked() && !fillcheck1 || q1b.isChecked() && !fillcheck1 || q1c.isChecked() && !fillcheck1 || q1d.isChecked() && !fillcheck1){
            fillcheck1 = true;
            progressBars++;
        }
        if(q2a.isChecked() && !fillcheck2 || q2b.isChecked() && !fillcheck2 || q2c.isChecked() && !fillcheck2 || q2d.isChecked() && !fillcheck2){
            fillcheck2 = true;
            progressBars++;
        }
        if(q3a.isChecked() && !fillcheck3 || q3b.isChecked() && !fillcheck3 || q3c.isChecked() && !fillcheck3 || q3d.isChecked() && !fillcheck3){
            fillcheck3 = true;
            progressBars++;
        }
        if(q4a.isChecked() && !fillcheck4 || q4b.isChecked() && !fillcheck4 || q4c.isChecked() && !fillcheck4 || q4d.isChecked() && !fillcheck4){
            fillcheck4 = true;
            progressBars++;
        }
        if(q5a.isChecked() && !fillcheck5 || q5b.isChecked() && !fillcheck5 || q5c.isChecked() && !fillcheck5 || q5d.isChecked() && !fillcheck5){
            fillcheck5 = true;
            progressBars++;
        }

        updateBars(progressBars);
    }


    /**
     *   Shows the Help Toast Message after pressing the middle Help Button.
     *   Uses layout>help_toast.xml file for custom toast.
     */
    public void showHelp(View view){

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.help_toast, (ViewGroup) findViewById(R.id.help_toast));

        final Toast helptoast = new Toast(getApplicationContext());
        helptoast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 35);
        helptoast.setDuration(Toast.LENGTH_SHORT);
        helptoast.setView(layout);

        // Make it last longer.
        new CountDownTimer(2000, 1000)
        {
            public void onTick(long millisUntilFinished) {helptoast.show();}
            public void onFinish() {helptoast.show();}

        }.start();
    }


    /**
     *   Shows the Toast Message after finishing the Quiz and pressing the Ready Button on the Right.
     *   Uses layout>done_toast.xml file for custom toast.
     */
    public void quizDoneToast(){

        int stringrank = getResources().getIdentifier("rank"+points, "string", this.getPackageName());
        rank = getString(stringrank);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.done_toast, (ViewGroup) findViewById(R.id.done_toast));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("The Quiz-Time is over!\nCheck out your answers.\n\nYou got " + points + " out of 5 points in this round.\n Your Rank:\n" + rank + "\n\nHit the Reset Button and try new questions!");

        final Toast donetoast = new Toast(getApplicationContext());
        donetoast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 35);
        donetoast.setDuration(Toast.LENGTH_SHORT);
        donetoast.setView(layout);

        // Make it last longer.
        new CountDownTimer(4000, 1000)
        {
            public void onTick(long millisUntilFinished) {donetoast.show();}
            public void onFinish() {donetoast.show();}

        }.start();

    }


    /**
     *   Resets the game state after pressing the Reset Button on the Left.
     *   - Unchecks Radio Buttons
     *   - Resets Progress Bars
     *   - Changes Quiz status and resets the Points
     *   - Calls new Questions Set from the pool.
     */
    public void questionsReset(View view){

        fastClouds();

        // Change back the answer colors.
        for (int i = 1; i <= 5; i++) {
            RadioButton answera = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "a", "id", this.getPackageName()));
            RadioButton answerb = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "b", "id", this.getPackageName()));
            RadioButton answerc = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "c", "id", this.getPackageName()));
            RadioButton answerd = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "d", "id", this.getPackageName()));

            answera.setTextColor(Color.parseColor("#000000"));
            answerb.setTextColor(Color.parseColor("#000000"));
            answerc.setTextColor(Color.parseColor("#000000"));
            answerd.setTextColor(Color.parseColor("#000000"));

            answera.setClickable(true);
            answerb.setClickable(true);
            answerc.setClickable(true);
            answerd.setClickable(true);
        }

        RadioGroup q1group = (RadioGroup) findViewById(R.id.q1group);
        RadioGroup q2group = (RadioGroup) findViewById(R.id.q2group);
        RadioGroup q3group = (RadioGroup) findViewById(R.id.q3group);
        RadioGroup q4group = (RadioGroup) findViewById(R.id.q4group);
        RadioGroup q5group = (RadioGroup) findViewById(R.id.q5group);

        fillcheck1 = false;
        fillcheck2 = false;
        fillcheck3 = false;
        fillcheck4 = false;
        fillcheck5 = false;

        // Clear radio buttons.
        q1group.clearCheck();
        q2group.clearCheck();
        q3group.clearCheck();
        q4group.clearCheck();
        q5group.clearCheck();

        quizdone = false;
        points = 0;
        progressBars = 0;
        updateBars(progressBars);

        randomizeQuestions();
    }


    /**
     *   Random number generator to generate random question number.
     */
    public int randomizeNumbers(){
        int min = 1;

        Random randomNumber = new Random();
        int randomQuestion = randomNumber.nextInt((numberOfQuestions - min) + 1) + min;

        return randomQuestion;
    }


    /**
     *   Shuffle and displays new random set of questions and answers.
     *   Checks if the chosen question was not aready displayed, if so then it chooses different one.
     */
    public void randomizeQuestions(){

        int randomQuestion = 0;

        for (int i = 1; i <= 5; i++) {
            int goodanswer1;
            int goodanswer2;
            int goodanswer3;
            int goodanswer4;
            int goodanswer5;

            int questiontext;
            int answertexta;
            int answertextb;
            int answertextc;
            int answertextd;

            TextView question = (TextView) findViewById(getResources().getIdentifier("q" + i + "q", "id", this.getPackageName()));
            RadioButton answera = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "a", "id", this.getPackageName()));
            RadioButton answerb = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "b", "id", this.getPackageName()));
            RadioButton answerc = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "c", "id", this.getPackageName()));
            RadioButton answerd = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "d", "id", this.getPackageName()));


            randomQuestion = randomizeNumbers();

            if(i == 1){
                savequestion1 = randomQuestion;

                questiontext = getResources().getIdentifier("q"+randomQuestion, "string", this.getPackageName());
                question.setText(questiontext);

                answertexta = getResources().getIdentifier("a"+randomQuestion, "string", this.getPackageName());
                answera.setText(answertexta);
                answertextb = getResources().getIdentifier("b"+randomQuestion, "string", this.getPackageName());
                answerb.setText(answertextb);
                answertextc = getResources().getIdentifier("c"+randomQuestion, "string", this.getPackageName());
                answerc.setText(answertextc);
                answertextd = getResources().getIdentifier("d"+randomQuestion, "string", this.getPackageName());
                answerd.setText(answertextd);

                goodanswer1 = getResources().getIdentifier("q"+randomQuestion+"answer", "string", this.getPackageName());
                correctanswer[1] = getString(goodanswer1);

            }

            if(i == 2){
                while(savequestion1 == randomQuestion){
                    randomQuestion = randomizeNumbers();
                }
                savequestion2 = randomQuestion;

                questiontext = getResources().getIdentifier("q"+randomQuestion, "string", this.getPackageName());
                question.setText(questiontext);

                answertexta = getResources().getIdentifier("a"+randomQuestion, "string", this.getPackageName());
                answera.setText(answertexta);
                answertextb = getResources().getIdentifier("b"+randomQuestion, "string", this.getPackageName());
                answerb.setText(answertextb);
                answertextc = getResources().getIdentifier("c"+randomQuestion, "string", this.getPackageName());
                answerc.setText(answertextc);
                answertextd = getResources().getIdentifier("d"+randomQuestion, "string", this.getPackageName());
                answerd.setText(answertextd);

                goodanswer2 = getResources().getIdentifier("q"+randomQuestion+"answer", "string", this.getPackageName());
                correctanswer[2] = getString(goodanswer2);

            }

            if(i == 3){
                while(savequestion1 == randomQuestion || savequestion2 == randomQuestion){
                    randomQuestion = randomizeNumbers();
                }
                savequestion3 = randomQuestion;

                questiontext = getResources().getIdentifier("q"+randomQuestion, "string", this.getPackageName());
                question.setText(questiontext);

                answertexta = getResources().getIdentifier("a"+randomQuestion, "string", this.getPackageName());
                answera.setText(answertexta);
                answertextb = getResources().getIdentifier("b"+randomQuestion, "string", this.getPackageName());
                answerb.setText(answertextb);
                answertextc = getResources().getIdentifier("c"+randomQuestion, "string", this.getPackageName());
                answerc.setText(answertextc);
                answertextd = getResources().getIdentifier("d"+randomQuestion, "string", this.getPackageName());
                answerd.setText(answertextd);

                goodanswer3 = getResources().getIdentifier("q"+randomQuestion+"answer", "string", this.getPackageName());
                correctanswer[3] = getString(goodanswer3);

            }

            if(i == 4){
                while(savequestion1 == randomQuestion || savequestion2 == randomQuestion || savequestion3 == randomQuestion){
                    randomQuestion = randomizeNumbers();
                }
                savequestion4 = randomQuestion;

                questiontext = getResources().getIdentifier("q"+randomQuestion, "string", this.getPackageName());
                question.setText(questiontext);

                answertexta = getResources().getIdentifier("a"+randomQuestion, "string", this.getPackageName());
                answera.setText(answertexta);
                answertextb = getResources().getIdentifier("b"+randomQuestion, "string", this.getPackageName());
                answerb.setText(answertextb);
                answertextc = getResources().getIdentifier("c"+randomQuestion, "string", this.getPackageName());
                answerc.setText(answertextc);
                answertextd = getResources().getIdentifier("d"+randomQuestion, "string", this.getPackageName());
                answerd.setText(answertextd);

                goodanswer4 = getResources().getIdentifier("q"+randomQuestion+"answer", "string", this.getPackageName());
                correctanswer[4] = getString(goodanswer4);

            }

            if(i == 5){
                while(savequestion1 == randomQuestion || savequestion2 == randomQuestion || savequestion3 == randomQuestion || savequestion4 == randomQuestion){
                    randomQuestion = randomizeNumbers();
                }
                savequestion5 = randomQuestion;

                questiontext = getResources().getIdentifier("q"+randomQuestion, "string", this.getPackageName());
                question.setText(questiontext);

                answertexta = getResources().getIdentifier("a"+randomQuestion, "string", this.getPackageName());
                answera.setText(answertexta);
                answertextb = getResources().getIdentifier("b"+randomQuestion, "string", this.getPackageName());
                answerb.setText(answertextb);
                answertextc = getResources().getIdentifier("c"+randomQuestion, "string", this.getPackageName());
                answerc.setText(answertextc);
                answertextd = getResources().getIdentifier("d"+randomQuestion, "string", this.getPackageName());
                answerd.setText(answertextd);

                goodanswer5 = getResources().getIdentifier("q"+randomQuestion+"answer", "string", this.getPackageName());
                correctanswer[5] = getString(goodanswer5);

            }
        }
    }


    /**
     *   Checks if player answered all the questions and finishes the quiz.
     *   Tells the player if he or she forgot to check an answer.
     *   Shows the results of the quiz - correct answers, points and rank.
     */
    public void setReady(View view){

        if(quizdone){

            fastClouds();
            quizDoneToast();
        }

        // Shows Toast Message if the player missed and skipped one of the questions.
        if(progressBars < 5 && !quizdone){

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.not_ready_toast, (ViewGroup) findViewById(R.id.not_ready_toast));

            final Toast notreadytoast = new Toast(getApplicationContext());
            notreadytoast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 35);
            notreadytoast.setDuration(Toast.LENGTH_SHORT);
            notreadytoast.setView(layout);

            // Make it last longer.
            new CountDownTimer(2000, 1000)
            {
                public void onTick(long millisUntilFinished) {notreadytoast.show();}
                public void onFinish() {notreadytoast.show();}

            }.start();
        }

        if(progressBars == 5 && !quizdone) {

            quizdone = true;

            checkAnswers();
            fastClouds();
            quizDoneToast();
        }

    }


    /**
     *   Checks the correct answers, marks them with green color.
     *   Checks the wrong answers and marks them with red color.
     *   Changes the Radio Buttons to be Uncheckable after showing the correct answers to avoid cheating.
     */
    public void checkAnswers(){
        points = 0;


        for (int i = 1; i <= 5; i++) {
            RadioButton answera = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "a", "id", this.getPackageName()));
            RadioButton answerb = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "b", "id", this.getPackageName()));
            RadioButton answerc = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "c", "id", this.getPackageName()));
            RadioButton answerd = (RadioButton) findViewById(getResources().getIdentifier("q" + i + "d", "id", this.getPackageName()));

            // If the answer is right.
            if (answera.isChecked() && correctanswer[i].equals("A")) {
                points++;
                answera.setTextColor(Color.parseColor("#056E0A"));
            }
            if (answerb.isChecked() && correctanswer[i].equals("B")) {
                points++;
                answerb.setTextColor(Color.parseColor("#056E0A"));
            }
            if (answerc.isChecked() && correctanswer[i].equals("C")) {
                points++;
                answerc.setTextColor(Color.parseColor("#056E0A"));
            }
            if (answerd.isChecked() && correctanswer[i].equals("D")) {
                points++;
                answerd.setTextColor(Color.parseColor("#056E0A"));
            }

            // If the answer is wrong.
            if (answera.isChecked() && correctanswer[i].equals("B") || answera.isChecked() && correctanswer[i].equals("C") || answera.isChecked() && correctanswer[i].equals("D")) {

                answera.setTextColor(Color.parseColor("#640505"));

                if(correctanswer[i].equals("B")){
                    answerc.setTextColor(Color.parseColor("#640505"));
                    answerd.setTextColor(Color.parseColor("#640505"));
                }
                if(correctanswer[i].equals("C")){
                    answerb.setTextColor(Color.parseColor("#640505"));
                    answerd.setTextColor(Color.parseColor("#640505"));
                }
                if(correctanswer[i].equals("D")){
                    answerb.setTextColor(Color.parseColor("#640505"));
                    answerc.setTextColor(Color.parseColor("#640505"));
                }
            }
            if (answerb.isChecked() && correctanswer[i].equals("A") || answerb.isChecked() && correctanswer[i].equals("C") || answerb.isChecked() && correctanswer[i].equals("D")) {

                answerb.setTextColor(Color.parseColor("#640505"));

                if(correctanswer[i].equals("A")){
                    answerc.setTextColor(Color.parseColor("#640505"));
                    answerd.setTextColor(Color.parseColor("#640505"));
                }
                if(correctanswer[i].equals("C")){
                    answera.setTextColor(Color.parseColor("#640505"));
                    answerd.setTextColor(Color.parseColor("#640505"));
                }
                if(correctanswer[i].equals("D")){
                    answera.setTextColor(Color.parseColor("#640505"));
                    answerc.setTextColor(Color.parseColor("#640505"));
                }

            }
            if (answerc.isChecked() && correctanswer[i].equals("A") || answerc.isChecked() && correctanswer[i].equals("B") || answerc.isChecked() && correctanswer[i].equals("D")) {

                answerc.setTextColor(Color.parseColor("#640505"));

                if(correctanswer[i].equals("A")){
                    answerb.setTextColor(Color.parseColor("#640505"));
                    answerd.setTextColor(Color.parseColor("#640505"));
                }
                if(correctanswer[i].equals("B")){
                    answera.setTextColor(Color.parseColor("#640505"));
                    answerd.setTextColor(Color.parseColor("#640505"));
                }
                if(correctanswer[i].equals("D")){
                    answera.setTextColor(Color.parseColor("#640505"));
                    answerb.setTextColor(Color.parseColor("#640505"));
                }

            }
            if (answerd.isChecked() && correctanswer[i].equals("A") || answerd.isChecked() && correctanswer[i].equals("B") || answerd.isChecked() && correctanswer[i].equals("C")) {

                answerd.setTextColor(Color.parseColor("#640505"));

                if(correctanswer[i].equals("A")){
                    answerb.setTextColor(Color.parseColor("#640505"));
                    answerc.setTextColor(Color.parseColor("#640505"));
                }
                if(correctanswer[i].equals("B")){
                    answera.setTextColor(Color.parseColor("#640505"));
                    answerc.setTextColor(Color.parseColor("#640505"));
                }
                if(correctanswer[i].equals("C")){
                    answera.setTextColor(Color.parseColor("#640505"));
                    answerb.setTextColor(Color.parseColor("#640505"));
                }

            }
            answera.setClickable(false);
            answerb.setClickable(false);
            answerc.setClickable(false);
            answerd.setClickable(false);
        }



    }


    /**
     *   Makes the Clouds on the Logo go crazy in particular moments.
     *   Example: When Reset Button is hit and new Questions are being randomly taken from the pool.
     */
    public void fastClouds(){

        final AnimationDrawable logoAnimation = new AnimationDrawable();
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo00), 65);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo01), 65);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo02), 60);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo03), 60);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo04), 55);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo05), 55);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo06), 55);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo07), 50);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo08), 50);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo09), 50);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo10), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo11), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo12), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo13), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo14), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo00), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo01), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo02), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo03), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo04), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo05), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo06), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo07), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo08), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo09), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo10), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo11), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo12), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo13), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo14), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo00), 40);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo01), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo02), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo03), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo04), 45);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo05), 50);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo06), 50);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo07), 50);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo08), 55);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo09), 55);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo10), 55);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo11), 60);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo12), 60);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo13), 65);
        logoAnimation.addFrame(getResources().getDrawable(R.drawable.logo14), 65);
        ImageView logo = (ImageView) findViewById(R.id.steam);
        logo.setBackgroundDrawable(logoAnimation);
        logoAnimation.start();

        // Start delayed to overwrite onCreate ImageView
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                logoAnimateStart();
            }
        }, 2130);
    }

}





