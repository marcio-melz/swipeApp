package com.Marcio.swipeapp.builder;

import com.Marcio.swipeapp.entity.Question;
import com.Marcio.swipeapp.enums.OptionEnum;
import com.Marcio.swipeapp.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe construtora de perguntas
 */
public class QuestionsBuilder {

    public List<Question> build() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("Qual é seu nome?", QuestionTypeEnum.SINGLE).addOption("Marcio", false));
        questions.add(new Question("O céu é Azul?", QuestionTypeEnum.MULTIPLE)
            .addOption(OptionEnum.YES.getOption(), true)
            .addOption(OptionEnum.NO.getOption(), false)
        );
        questions.add(new Question("O triciculo tem 3 rodas?", QuestionTypeEnum.MULTIPLE)
            .addOption(OptionEnum.YES.getOption(), true)
            .addOption(OptionEnum.NO.getOption(), false)

        );
        questions.add(new Question("Você esta namorando?", QuestionTypeEnum.MULTIPLE)
            .addOption(OptionEnum.YES.getOption(), true)
            .addOption(OptionEnum.NO.getOption(), false)
        );

        return questions;
    }
}
