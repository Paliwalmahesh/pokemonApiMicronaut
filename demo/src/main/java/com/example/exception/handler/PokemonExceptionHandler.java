package com.example.exception.handler;

import com.example.exception.PokemonValidationException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Produces
@Requires(classes = {PokemonValidationException.class})
public class PokemonExceptionHandler implements ExceptionHandler< PokemonValidationException, HttpResponse<PokemonValidationException>> {
    @Override
    public HttpResponse<PokemonValidationException> handle(HttpRequest request, PokemonValidationException exception) {
        return HttpResponse.badRequest(exception);
    }
}
