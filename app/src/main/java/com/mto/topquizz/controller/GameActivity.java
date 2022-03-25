package com.mto.topquizz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mto.topquizz.R;
import com.mto.topquizz.model.Question;
import com.mto.topquizz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textQuestion;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mNumberOfQuestion;

    private int mScore;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MediaPlayer music = MediaPlayer.create(GameActivity.this, R.raw.thejazzpiano);
        music.start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mNumberOfQuestion = 6;
        mQuestionBank = this.generateQuestions();

        mScore = 0;

        textQuestion = findViewById(R.id.textQuestion);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setTag(0);
        button2.setTag(1);
        button3.setTag(2);
        button4.setTag(3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);

    }

    @Override
    public void onClick(View view) {
        MediaPlayer click_applause = MediaPlayer.create(GameActivity.this, R.raw.applause);
        MediaPlayer click_laught = MediaPlayer.create(GameActivity.this, R.raw.laughter);
        int responseIndex = (int) view.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
            mCurrentQuestion = mQuestionBank.getQuestion();
            click_applause.start();
            this.displayQuestion(mCurrentQuestion);
        } else {
            // Wrong answer
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
            mCurrentQuestion = mQuestionBank.getQuestion();
            click_laught.start();
            this.displayQuestion(mCurrentQuestion);
        }

        if (--mNumberOfQuestion == 0) {
            // End of Game

            endGame();
        } else {

            mCurrentQuestion = mQuestionBank.getQuestion();
            this.displayQuestion(mCurrentQuestion);
        }
    }

    private void endGame() {
        // Display Dialog box with Builder library
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("C'est fini !")
                .setMessage("Ton score est de " +mScore+ " point(s)")
                // Positive button is generally button "Yes" or "OK"...
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // End of activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);

                        finish();
                    }
                })
                .create() // Create dialog box
                .show(); // Show Dialog Box
    }

    private void displayQuestion(final Question question) {
        textQuestion.setText(question.getQuestion());
        button1.setText(question.getChoiceList().get(0));
        button2.setText(question.getChoiceList().get(1));
        button3.setText(question.getChoiceList().get(2));
        button4.setText(question.getChoiceList().get(3));

    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("Qui a réalisé le film \"In the mood for love\" ?",
                Arrays.asList("Zhang Yimou", "Chan Feng Zhao", "Wong Kar-Wai", "Scorsese"),
                2);

        Question question2 = new Question("Quelle race d'animal est un briard ?",
                Arrays.asList("Un Chinchilla", "Un chat", "Un chien", "Un taureau"),
                2);

        Question question3 = new Question("Où est né Mozart ?",
                Arrays.asList("Salzbourg", "Turin", "Venise", "Vienne"),
                0);

        Question question4 = new Question("Quel état des Etats-Unis a pour capitale Montgomery?",
                Arrays.asList("L'Ohio", "La Californie", "L'Alabama", "Le Nouv. Mexique"),
                2);

        Question question5 = new Question("Qu'est-ce que le Prater à Vienne ?",
                Arrays.asList("Ce n'est pas à Vienne", "Un palais d'été", "Un parc d'attraction", "Un blvd périphèrique"),
                2);

        Question question6 = new Question("Le beaufort est un fromage...",
                Arrays.asList("normand", "savoyard", "bourguignon", "auvergnat"),
                1);

        Question question7 = new Question("Combien la France compte-t-elle de communes ?",
                Arrays.asList("~ 36 000", "~ 46 000", "~ 15 000", "~ 60 000"),
                0);

        Question question8 = new Question("Les habitants de Chamonix sont des...",
                Arrays.asList("chamoniards", "chamonixenais", "chameaux", "chamoniens"),
                0);

        Question question9 = new Question("Rimbaud était...",
                Arrays.asList("carolopolitain", "mezerocharlotain", "carolomacérien", "charlerien"),
                0);

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8, question9));


    }

}