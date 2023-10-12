package com.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/bot")
public class BotController {

    private final BotService botService;
    @Autowired
    public BotController(BotService botService) {
        this.botService = botService;
    }
    @PostMapping
    public ResponseEntity<Bot> addQuestionAnswer(@RequestBody Bot bot){
        return ResponseEntity.ok(botService.addQuestionAnswer(bot));
    }
    @PutMapping
    public ResponseEntity<Bot> updateAnswer(@RequestParam("question") String question,
                                            @RequestParam("answer") String answer){
        return ResponseEntity.ok(botService.updateAnswer(question,answer));
    }
    @GetMapping
    public ResponseEntity<String> getAnswer(@RequestParam("question") String question){
        return ResponseEntity.ok(botService.getAnswer(question).toString());
    }
}
