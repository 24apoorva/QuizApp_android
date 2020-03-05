package com.example.android.quizapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
//import android.widget.RadioButton;
import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int answer_1 = R.id.tottenham_hotspur_radio;
    int answer_3 = R.id.episkyros_radio;
    int answer_4 = R.id.year1_radio;
    int score = 0;
    int noAnswer = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This checks first question and returns true if correctly answered.
     *
     * @return if the answer is correct or no.
     */
    private boolean checkQuestionOne(boolean reset) {
        RadioGroup rgOne = findViewById(R.id.question1_radio_group);
        if (reset) rgOne.clearCheck();
        if (rgOne.getCheckedRadioButtonId() == -1) noAnswer--;
        return rgOne.getCheckedRadioButtonId() == answer_1;
    }

    /**
     * This method checks 2nd question answers and returns the result.
     *
     * @return boolean value .True if answer is correct and false if wrong.
     */
    private boolean checkQuestionTwo(boolean reset) {
        CheckBox c1 = findViewById(R.id.Messi_checkbox);
        CheckBox c2 = findViewById(R.id.Luis_Suárez_checkbox);
        CheckBox c3 = findViewById(R.id.Neymar_checkbox);
        CheckBox c4 = findViewById(R.id.paulo_dybala_checkbox);
        if (reset) {
            c1.setChecked(false);
            c2.setChecked(false);
            c3.setChecked(false);
            c4.setChecked(false);
        }
        if (!c1.isChecked() && !c2.isChecked() && !c3.isChecked()&& !c4.isChecked())
            noAnswer--;
        return c1.isChecked() && c4.isChecked();
    }

    /**
     * This method checks 3rd question if the answer is correct or not.
     *
     * @return true if the answer is correct else false.
     */
    private boolean checkQuestionThree(Boolean reset) {
        RadioGroup rgThree = findViewById(R.id.question3_radio_group);
        if (reset) rgThree.clearCheck();
        if (rgThree.getCheckedRadioButtonId() == -1) noAnswer--;
        return rgThree.getCheckedRadioButtonId() == answer_3;
    }

    /**
     * This method checks 4th question if the answer is correct or not.
     *
     * @return true if the answer is correct else false.
     */
    private boolean checkQuestionFour(boolean reset) {
        RadioGroup rgFour = findViewById(R.id.question4_radio_group);
        if (reset) rgFour.clearCheck();
        if (rgFour.getCheckedRadioButtonId() == -1) noAnswer--;
        return rgFour.getCheckedRadioButtonId() == answer_4;
    }

    /**
     * This method checks 5th question if the answer is correct or not.
     *
     * @return true if the answer is correct else false.
     */
    private boolean checkQuestionFive(Boolean reset) {
        EditText fifthQuestionAns = findViewById(R.id.answer_fifth_question);
        if (reset) fifthQuestionAns.setText("");
        String answer5 = fifthQuestionAns.getText().toString().toUpperCase().trim();
        if (answer5.isEmpty()) {
            noAnswer--;
        }
        return answer5.contentEquals(getString(R.string.France_text));
    }

    /**
     * This method calculates the score for all the questions.
     *
     * @return total score.
     */
    private int calculateScore() {
        if (checkQuestionOne(false)) {
            score++;
        }
        if (checkQuestionTwo(false)) {
            score++;
        }
        if (checkQuestionThree(false)) {
            score++;
        }
        if (checkQuestionFour(false)) {
            score++;
        }
        if (checkQuestionFive(false)) {
            score++;
        }
        return score;
    }


    /**
     * onClickSubmit will be called when submit button is clicked.
     *
     * @param view displays result
     */
    public void onclickSubmit(View view) {
        calculateScore();
        result();
    }

    private void result() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage(getString(R.string.alert_text1a) + " " + noAnswer + " " + getString(R.string.alert_text1b) + "\n" + getString(R.string.alert_text2a) + " " + score + " " + getString(R.string.alert_text2b) + "\n" + getString(R.string.alert_text3));

        alertDialogBuilder.setPositiveButton(getString(R.string.alert_yes_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        reset();
                    }
                }
        );
        alertDialogBuilder.setNegativeButton(getString(R.string.alert_Exit_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // if this button is clicked, close
                // current activity
                MainActivity.this.finish();
            }
        });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    private void reset() {
        checkQuestionOne(true);
        checkQuestionTwo(true);
        checkQuestionThree(true);
        checkQuestionFour(true);
        checkQuestionFive(true);
        score = 0;
        noAnswer = 5;

    }

}


