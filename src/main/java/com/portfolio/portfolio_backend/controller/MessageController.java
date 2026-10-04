package com.portfolio.portfolio_backend.controller;

import com.portfolio.portfolio_backend.entity.Message;
import com.portfolio.portfolio_backend.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {

        if (message.getIsRead() == null) {
            message.setIsRead(false);
        }

        if (message.getCreatedAt() == null) {
            message.setCreatedAt(LocalDateTime.now());
        }

        return messageRepository.save(message);
    }

    @PutMapping("/{id}")
    public Message updateMessage(
            @PathVariable Long id,
            @RequestBody Message message) {

        message.setId(id);
        return messageRepository.save(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageRepository.deleteById(id);
    }
}
