package com.example.cashcard;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardRepository cardRepository;

    private CashCardController(CashCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCard, UriComponentsBuilder ucb) {
        CashCard savedCashCard = cardRepository.save(newCashCard);
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
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

    @GetMapping
    private ResponseEntity<List<CashCard>> findAll(Pageable pageable) {
        Page<CashCard> page = cardRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                        ));
        return ResponseEntity.ok(page.getContent());
    }

    //TODO: (lab) lição 09 -> att repository e controller (uso do Principal)
}