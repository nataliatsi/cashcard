package com.example.cashcard;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardRepository cardRepository;

    private CashCardController(CashCardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        Optional<CashCard> cOptional = cardRepository.findById(requestedId);
        if (cOptional.isPresent()) {
            return ResponseEntity.ok(cOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}