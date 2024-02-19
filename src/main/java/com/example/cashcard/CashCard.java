package com.example.cashcard;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CashCard(@Id Long id, Double amount, String owner) {
} 
