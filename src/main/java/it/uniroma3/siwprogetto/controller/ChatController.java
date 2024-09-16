package it.uniroma3.siwprogetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.uniroma3.siwprogetto.service.ChatBotService;


@Controller
public class ChatController {

    @Autowired
    private ChatBotService chatBotService;

    @PostMapping("/chat")
    public @ResponseBody ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
    	String response = chatBotService.handleUserMessage(message);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/chat")
    public String ChatMessage() {
        return "chat.html";
    }
    
}