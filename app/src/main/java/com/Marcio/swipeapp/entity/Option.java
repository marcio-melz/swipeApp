package com.Marcio.swipeapp.entity;

/**
 * Classe de opção da pergunta
 */
public class Option {

    private String text;
    private boolean  isCorrect = false;

    /**
     * Função para obter o texto da opção
     * @return
     */
    public String getText() {
        return text;
    }

    public Option(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }
}
