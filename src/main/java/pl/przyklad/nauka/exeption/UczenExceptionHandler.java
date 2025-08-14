package pl.przyklad.nauka.exeption;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.access.AccessDeniedException;
/*
 * Klasy oznaczone adnotacją @RestControllerAdvice działają jako globalny punkt przechowywania wyjątków w aplikacji webowej.
 * Moga przetwarzać wyjątki zgłaszane w kontrolerach i zwracać niestandardowe odpowiedzi HTTP
 * @RestControllerAdvice - adnotacja jest powiązana z programowaniem aspektowym
 * W spring framework adnotacje takie jak RestControllerAdvice oraz ExeptionHandler są  obsługiwane przez mechanizm aspektowy AOP.
 * AOP - Aspect Oriental Programming 
 * 
 * */

@RestControllerAdvice
public class UczenExceptionHandler extends ResponseEntityExceptionHandler {

	@Value("${uczen.application.error.should.print.stacktrace}")
	private boolean czyWypisacSladBledu;

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
		var errorResponseBuilder = errorResponseBuilder(e).build();

//				ErrorResponse.builder()
//				.wiadomosc(e.getMessage())
//				.dataCzas(ZonedDateTime.now())
//				.build();
		/*
		 * ResponseEntity.status - to metoda która tworzy odpowiedź HTTP z określonym
		 * statusem HTTP NOT_FOUND - to stała reprezentujaca kod odpowiedzi HTTP 404 Not
		 * Found
		 */
		return ResponseEntity.status(NOT_FOUND).body(errorResponseBuilder);
	}
	
	/*
	 * @ExceptionHandler(AccessDeniedException.class)
	 * 
	 * Mówi Springowi: „Jeśli gdziekolwiek w trakcie obsługi żądania pojawi się
	 * wyjątek AccessDeniedException, to nie pokazuj domyślnej strony błędu Spring
	 * Security, tylko uruchom tę moją metodę”.
	 * 
	 * 
	 */
	@ExceptionHandler(AccessDeniedException.class)
	   public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(FORBIDDEN).body(errorResponseBuilder(e).build());
    }

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		var validationErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(fe -> new ValidationError(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());

		var errorResponseBuilder = errorResponseBuilder(ex).walidacjaBledu(validationErrors).build();
//				ErrorResponse.builder()
//				.wiadomosc(ex.getMessage())
//				.dataCzas(ZonedDateTime.now())
//				.walidacjaBledu(validationErrors)
//				.build();
		/*
		 * UNPROCESSABLE_ENTITY - to stała reprezentujaca kod odpowiedzi HTTP 422
		 * Unprocessable Entity Serwer zrozumiał żądanie, ale nie może go przetworzyć,
		 * ponieważ zawiera semantyczne błędy. Dla przykładu wysyłamy imie i nazwisko
		 * jako puste , czyli jest poprawne składiowo, ale nie przechodzi
		 * validacji @NotBlank
		 */
		return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(errorResponseBuilder);

	}

	private ErrorResponse.ErrorResponseBuilder errorResponseBuilder(Exception e) {
		var errorResponseBuilder = ErrorResponse.builder().wiadomosc(e.getMessage()).dataCzas(ZonedDateTime.now());

		if (czyWypisacSladBledu) {
			errorResponseBuilder.sciezkaBledu(getStackTrace(e));
		}

		return errorResponseBuilder;
	}

}
