package com.Marcio.swipeapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.Marcio.swipeapp.builder.QuestionsBuilder;
import com.Marcio.swipeapp.entity.Question;
import com.Marcio.swipeapp.enums.OptionEnum;
import com.Marcio.swipeapp.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Question> questions = new ArrayList<>();
    private final QuestionsBuilder questionsBuilder = new QuestionsBuilder();
    private Question question = null;

    private int screenIndex = 0;
    private TextView tvQuestion;
    private EditText etAnswer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions = questionsBuilder.build();

        ConstraintLayout screen = findViewById(R.id.screen);
        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);

        screen.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            @Override
            public void onSwipeTop() { // Não
                super.onSwipeTop();
                if (question.getType() == QuestionTypeEnum.MULTIPLE && question.getAnswer() == null) {
                    question.setAnswer(OptionEnum.NO.getOption());
                    Toast.makeText(getApplicationContext(), getString(R.string.chosen_answer, getString(R.string.no)), Toast.LENGTH_LONG).show();
                } else if (question.getAnswer() != null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.questionAlreadyAnswered), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onSwipeBottom() { // Sim / Confirmar
                super.onSwipeBottom();
                if (question.getType() == QuestionTypeEnum.MULTIPLE && question.getAnswer() == null) {
                    question.setAnswer(OptionEnum.YES.getOption());
                    Toast.makeText(getApplicationContext(), getString(R.string.chosen_answer, getString(R.string.yes)), Toast.LENGTH_LONG).show();
                } else if (question.getType() == QuestionTypeEnum.SINGLE && question.getAnswer() == null){
                    String answer = etAnswer.getText().toString();
                    if (TextUtils.isEmpty(answer)) {
                        etAnswer.requestFocus();
                        etAnswer.setError(getString(R.string.answerEmpty));
                    } else {
                        question.setAnswer(answer);
                        Toast.makeText(getApplicationContext(), getString(R.string.chosen_answer, etAnswer.getText()), Toast.LENGTH_LONG).show();
                    }
                } else if (question.getAnswer() != null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.questionAlreadyAnswered), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                loadQuestion("right");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                loadQuestion("left");
            }
        });
    }

    /**
     * Função para carregar a pergunta na tela
     * @param direction Direção para o qual o usuário puxou a tela
     */
    private void loadQuestion(String direction) {
        resetIndexIfOut();

        question = questions.get(this.screenIndex);
        tvQuestion.setText(question.getQuestion());

        etAnswer.setVisibility(EditText.INVISIBLE);
        if (question.getType() == QuestionTypeEnum.SINGLE) {
            etAnswer.setVisibility(EditText.VISIBLE);
            if (question.getAnswer() != null) {
                etAnswer.setEnabled(false);
            }
        }

        if (direction.equals("right")) { // Verifica se foi puxado para a direita
            nextQuestion();
        }

        if (direction.equals("left")) { // Verifica se foi puxado para a esquerda
            prevQuestion();
        }

    }

    /**
     * Função para resetar o index das pergunras, caso o index estiver fora do tamanho da lista de perguntas
     */
    private void resetIndexIfOut() {
        if (screenIndex > (questions.size() - 1) || screenIndex < (questions.size() - 1)) {
            screenIndex = 0;
        }
    }

    /**
     * Função para voltar a pergunta
     */
    private void prevQuestion() {
        screenIndex--;
    }

    /**
     * Função para ir para a próxima pergunta
     */
    private void nextQuestion() {
        screenIndex++;
    }
}
