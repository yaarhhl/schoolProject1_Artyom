package com.example.school_project1;

import static android.widget.Toast.LENGTH_LONG;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Physics_activity extends AppCompatActivity {

    private ImageView main_question;
    private EditText user_answer;
    private Button main_button;
    private Button text_button;
    private Button nextQuestionButton;
    private Button previousQuestionButton;
    private TextView logo_question;
    private String userAnswer;
    double trueAnswers = 0;
    //баллы за все правильные ответы
    double allAnswers() {
        double abc = 0;
        for (int n = 0; n < questions.length; n++) {
            if (questions[n].answer != null) {
                abc += questions[n].ball;
            }
        }
        return abc;
    }
    //вспомогательная функция для добавлкния ответов в массив TrueAnswersArray
    void addToTrueAnswersArray() {
        for (int n = 0; n < questions.length; n++) {
            if (questions[n].answer != null) {
                trueAnswersArray.add(n, questions[n].answer);
            }
        }
    }
    //вспомогательная функция для добавлкния ответов в массив UserAnswersArray
    void addToUserAnswersArray() {
        for (int n = 0; n < questions.length; n++) {
            if (questions[n].answer != null) {
                userAnswersArray.add(n, "");
            }
        }
    }
    //

    Question[] questions;
    int result;
    int i = -1;
    int mainButtonClicks = 0;
    int nextQuestionClicks = 0;
    ArrayList<String> textAnswers;
    ArrayList<String> trueAnswersArray;
    ArrayList<String> userAnswersArray;
    //

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_physics);

        //массив вопросов типа Question
        questions = new Question[11];
        //массив ответов пользователя для вопросов с неразвернутым ответом
        userAnswersArray = new ArrayList<>();
        //массив правильных ответов для вопросов с неразвернутым ответом
        trueAnswersArray = new ArrayList<>();
        //массив развернутых ответов пользователя
        textAnswers = new ArrayList<>();
        //
        user_answer = findViewById(R.id.user_answer);
        main_question = findViewById(R.id.main_question);
        logo_question = findViewById(R.id.logo_question);
        main_button = findViewById(R.id.main_button);
        text_button = findViewById(R.id.text_button);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);
        previousQuestionButton = findViewById(R.id.previousQuestionButton);
        //массив типа Question
        questions[0] = new Question(1, "351", R.drawable.null1);
        questions[1] = new Question(1, "24", R.drawable.one);
        questions[2] = new Question(1, "1", R.drawable.two);
        questions[3] = new Question(1, "1375", R.drawable.three);
        questions[4] = new Question(1, "2", R.drawable.four);
        questions[5] = new Question(1, "0.3", R.drawable.five);
        questions[6] = new Question(1, "0.2", R.drawable.six);
        questions[7] = new Question(1, "1.5", R.drawable.seven);
        questions[8] = new Question(1, "3", R.drawable.eight);
        questions[9] = new Question(1, "6", R.drawable.nine);
        questions[10] = new Question(R.drawable.ten);
        addToTrueAnswersArray();
        addToUserAnswersArray();

        //получение строки пользовательского ответа
        user_answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userAnswer = user_answer.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //
    }
    //следующий вопрос
    public void nextQuestion (View view) {
        if (mainButtonClicks == 1) {
            if (i >= -1 && i < questions.length) {
                    user_answer.setText("");
                    nextQuestionClicks++;
                    i++;
                    if (i == questions.length) i--;
                    main_question.setImageResource(questions[i].imageId);
                    logo_question.setText("Вопрос: " + i);
            }
        }
    }
    //предыдущий вопрос
    public void previousQuestion (View view) {
        if (mainButtonClicks == 1) {
            if (i > 0) {
                    user_answer.setText("");
                    i--;
                    main_question.setImageResource(questions[i].imageId);
                    logo_question.setText("Вопрос: " + i);
            }
        }
    }
    //начать игру, отправить результат
    public void startGame(View view) {
        mainButtonClicks ++;
        if (mainButtonClicks == 1) {Toast toast = Toast.makeText(this, "Не нажимайте данную кнопку, пока не ответите на все вопросы", Toast.LENGTH_SHORT); toast.show();}
        if (i == -1) {setText1();}
        if (mainButtonClicks == 2) {
            main_question.setVisibility(View.GONE);
            competition();
            if (((trueAnswers/allAnswers()) * 100) < 50) result = 2;
            if (((trueAnswers/allAnswers()) * 100) >= 50) result = 3;
            if (((trueAnswers/allAnswers()) * 100) >= 70) result = 4;
            if (((trueAnswers/allAnswers()) * 100) >= 90) result = 5;
            String format = String.format("%.2f",((trueAnswers/allAnswers()) * 100));
            String a = ("Средний процент правильных ответов: " + format + " Ваша оценка: " + result + System.lineSeparator() + "//" + System.lineSeparator() + "Вопросы с развернутым ответом: " + textAnswers);
            main_question.setImageDrawable(null);
            logo_question.setText(a);
            main_button.setText("Закончить тестирование");
            previousQuestionButton.setText("");
            nextQuestionButton.setText("");
            text_button.setText("");
            user_answer.setHint("");
        }

    }
    //вспомогательная функция
    public void setText1 () {
        text_button.setText("Утвердить текст");
        nextQuestionButton.setText("Следующий вопрос");
        previousQuestionButton.setText("Предыдущий вопрос");
        user_answer.setHint("Введите ответ:");
        main_button.setText("Отправить результат");
    }
    //подтвердить текст
    public void approveTheText(View view) {
        if (mainButtonClicks == 1) {
            if (nextQuestionClicks >= 1) {
                try {
                        if (questions[i].answer != null) {
                               userAnswersArray.set(i, userAnswer); //""
                        }
                        else  {
                            String str = ("Вопрос " + i + ": --- " + userAnswer);
                            textAnswers.add(str);
                        }
                } catch (NullPointerException n) {
                    Toast toast = Toast.makeText(this, "Вы не ввели текст", Toast.LENGTH_SHORT); toast.show();
                }
            }
        }
        //Log.v("абоба", trueAnswersArray.toString());
        //Log.v("абоба", userAnswersArray.toString());
    }
    //функция сравнения ответов для получения trueAnswers
    void competition() {
        for (int n = 0; n < trueAnswersArray.toArray().length; n++) {
            if (userAnswersArray.get(n).equals(trueAnswersArray.get(n))) {
                trueAnswers += questions[n].ball;
            }
        }
    }
    // userAnswersArray- вопросы пользователя с неразвернутым ответом
    // trueAnswersArray - ответы на вопросы с неразвернутым ответом
    // textAnswers - ответы пользователя на вопросы с развернутым ответом
    // String *** - данные о ученике
    // DB


}