package com.Marcio.swipeapp.entity;

import android.util.Log;

import com.Marcio.swipeapp.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe da pergunta
 */
public class Question {

    private String question;
    private List<Option> options = new ArrayList<>();
    private QuestionTypeEnum type;
    private Answer answer;

    public String getQuestion() {
        return question;
    }

    public List<Option> getOptions() {
        return options;
    }

    public QuestionTypeEnum getType() {
        return type;
    }

    public Question(String question, QuestionTypeEnum type) {
        this.question = question;
        this.type = type;
    }

    /**
     * Função para adicionar um opção de resposta para a pergunta
     * @param text Valor da opção
     * @param isCorrect Informa se esta opção é a correta
     * @return
     */
    public Question addOption(String text, boolean isCorrect) {
        if (this.options.size() > 2) {
            Log.e("Pergunta", "A pergunta excedeu o limite de duas opções");
        }
        this.options.add(new Option(text, isCorrect));
        return this;
    }

    /**
     * Função para setar a resposta da pergunta
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = new Answer(answer);
    }

    /**
     * Função para obter a resposta da pergunta
     * @return
     */
    public Answer getAnswer() {
        return this.answer;
    }

    /**
     * Função para verificar se a resposta que o usuário selecionou ou digitou está correta
     * @param answer
     * @return
     */
    public boolean verifyAnswer(String answer) {
        if (options.isEmpty()) {
            return false;
        }

        return options.contains(new Option(answer, true));
    }
}
