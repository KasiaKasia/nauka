package pl.przyklad.nauka.uczen;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/*
 * Tworzy konstruktor tylko dla pól oznaczonych jako final lub @NonNull
 * Dzięki @RequiredArgsConstructor, Lombok automatycznie wygeneruje:
 * public UczenController(UczenService uczenService) {
 *  this.uczenService = uczenService;
 * }
 * */
@RequiredArgsConstructor
/*
 * Adnotacja , która oznacza, że klasa jest kontrolerem REST i obsługuje
 * zapytania HTTP Rejestruje klasę jako bean kontrolera Lączy działanie 2
 * adnotacji: @Controller (oznacza klasę jako kontroler) i @ResponseBody
 * (automatycznie serializuje obiekty Java do JSON (i odwrotnie))
 */
@RestController
@RequestMapping("/uczniowie")
public class UczenController {
	private final UczenService service;

	/*
	 * Adnotacja GetMapping obsługuje żądania HTTP GET Wynik jest zwracany w
	 * formacie JSON bo jest @RestController
	 */
	@GetMapping
	public List<UczenResponse> getAll(@RequestParam(required = false) String klasa) {
		if (Objects.nonNull(klasa)) {
			return service.findByKlasa(klasa);
		}
		return service.findAll();
	}

	@GetMapping("/{id}")
	public UczenResponse getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	/*
	 * @Valid - uruchomił automatyczną walidację (np. @NotBlank, @Size, itp.) na
	 * polach obiektu.)
	 * 
	 * @RequestBody - przyjmuje dane żądania np.: w formacie JSON i mapuje je na
	 * obiekt Java W naszym przypadku mapuje na obiekt UczenRequest
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	/*
	 * @PreAuthorize - to adnotacja w Spring Security ktora umożliwa ustawianie warunków , 
	 * ktore muszą być spełnione przed wykonaniem metody.
	 * W momecie wykonania metody z tą adnotacją Spring sprawdza czy dany użytkownik ma uprawnienia
	 * do wykonania tej operacji na podstawie zdefiniowanych przez nas reguł.
	 * Adnotacja @PreAuthorize działa w ten sposób, że jesli warunek nie jest spełniony metoda nie zostanie wywołana 
	 * Warunki mogą sie odnosić się do różnych cech użytkownika takich jak role, uprawnienia, atrybuty konta a także argumenty metody oraz wartość zwracana z tej metody.
	 * My te warunki w przypadku createUczen okreslimy sobie za pomocą klauzuli 'hasAuthority('ADMIN')' i wymagane uprzwnienie ADMIN
	 * Te warunki w hasAuthority('ADMIN') określa się za pomocą spela czyli Spring Expression Language 
	 * aby to zadziałało nalezy dodac adnotację @EnableMethodSecurity na klasie SecurityConfig
	 * */
	@PreAuthorize("hasAuthority('ADMIN')")
	public UczenResponse createUczen(@RequestBody @Valid UczenRequest newCustomer) {
		return service.save(newCustomer);
	}

	@PutMapping("/{id}")
	public UczenResponse updateUczen(@PathVariable Integer id, @Valid @RequestBody UczenRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	/*
	 * Adnotacja @ResponseStatus(HttpStatus.NO_CONTENT) informuje Springa, że metoda
	 * ma zwracać określony status HTTP, nawet jeśli nie zwróci żadnego obiektu.
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUczenrById(@PathVariable Integer id) {
		service.delete(id);
	}
}
