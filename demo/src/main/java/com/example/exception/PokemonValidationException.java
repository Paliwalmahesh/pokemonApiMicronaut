package com.example.exception;

public class PokemonValidationException extends InvalidDataException {
  public PokemonValidationException(String message) {
    super(message);
  }
}
