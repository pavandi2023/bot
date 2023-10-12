package com.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class BotService {
    private final BotRepository botRepository;
    @Autowired
    public BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    public Bot addQuestionAnswer(Bot bot) {
        String question =bot.getQuestion();
        final Optional<Bot> optionalBot = botRepository.findByQuestion(question);
        if(optionalBot.isEmpty()){
            return botRepository.save(bot);
        }
        else{
            throw new IllegalArgumentException("This question already exist");
        }
    }

    public Bot updateAnswer(String question, String answer) {
        final Optional<Bot> optionalBot = botRepository.findByQuestion(question);
        if(optionalBot.isEmpty()){
            throw new IllegalArgumentException("This question does not exist");
        }
        else {
            Bot bot = optionalBot.get();
            bot.setAnswer(answer);
            Bot updatedBot = botRepository.save(bot);
            return updatedBot;
        }
    }

    public String  getAnswer(String question) {
        final Optional<Bot> optionalBot = botRepository.findByQuestion(question);
        if(optionalBot.isEmpty()){
            throw new IllegalArgumentException("This question does not exist");
        }
        else {
            return optionalBot.get().getAnswer();
        }
    }
}
