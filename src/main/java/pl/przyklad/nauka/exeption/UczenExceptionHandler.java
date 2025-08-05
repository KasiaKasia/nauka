package pl.przyklad.nauka.exeption;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import jakarta.persistence.EntityNotFoundException;


/*
 * Klasy oznaczone adnotacją @RestControllerAdvice działają jako globalny punkt przechowywania wyjątków w aplikacji webowej.
 * Moga przetwarzać wyjątki zgłaszane w kontrolerach i zwracać niestandardowe odpowiedzi HTTP
 * @RestControllerAdvice - adnotacja jest powiązana z programowaniem aspektowym
 * W spring framework adnotacje takie jak RestControllerAdvice oraz ExeptionHandler są  obsługiwane przez mechanizm aspektowy AOP.
 * AOP - Aspect Oriental Programming 
 * 
 * */


@RestControllerAdvice
public class UczenExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	   public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
	        var errorResponse = ErrorResponse.builder().message(e.getMessage()).timestamp(ZonedDateTime.now()).build();
	        return ResponseEntity.status(NOT_FOUND).body(errorResponse);
	    }

}
