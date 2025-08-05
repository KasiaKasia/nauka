# Przykładowy projekt Spring


## Istotne pojęcia

### pom.xml ( pom = Project Object Model )

pom.xml = główny plik konfiguracyjny projektu opartego na Maven. Formtu XML zawierający informacje o projekcie, jego zależnościach, pluginach, kompilatorze i innych ustawieniach.

### dependency
- [ ] `spring-boot-starter-validation` obsługuje m.in. takie adnotację:

| <strong>Adnotacja</strong> | <strong>	Opis</strong>  |
| :---         					|     :---      |
| @Valid						| Włącza walidację obiektu (np. w kontrolerze lub w klasie)| 
| @NotNull						| Pole nie może być null| 
| @NotBlank						| String nie może być null, pusty ("") ani zawierać tylko spacji| 
| @NotEmpty						| String, kolekcja lub tablica nie może być pusta (ale może być null)| 
| @Size(min, max)				| Długość tekstu / rozmiar kolekcji musi być w zakresie| 
| @Min, @Max					| Minimalna / maksymalna wartość liczby| 
| @Positive						| Wartość musi być dodatnia| 
| @PositiveOrZero				| Wartość ≥ 0| 
| @Negative, @NegativeOrZero	| Analogiczne jak wyżej| 
| @Email						| Sprawdza poprawność adresu email| 
| @Pattern(regexp)				| Dopasowanie do wyrażenia regularnego| 
| @Digits(integer, fraction)	| Liczba z określoną liczbą cyfr całkowitych i po przecinku| 
| @AssertTrue, @AssertFalse		| Wartość musi być odpowiednio true lub false| 
| @Past, @Future, @PastOrPresent, @FutureOrPresent	| Walidacja daty| 


```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
- [ ] `commons-lang3` - to rozszerzenie standardowych klas Javy, które dostarcza dodatkowe narzędzia i utilsy do pracy z: String, Number, Date, Object, Class, Enum itp.,

Klasy pomocnicze, np.:

| <strong>Klasa</strong> 			 | <strong>Zastosowanie</strong>                                                                                                                         |
| :---         						 |     :--- |
| `StringUtils`                      | Zaawansowana manipulacja łańcuchami znaków (np. `isBlank`, `capitalize`, `join`, `split`, `remove`, `reverse`, `containsIgnoreCase`) |
| `ObjectUtils`                      | Ułatwienia przy operowaniu na obiektach, np. `ObjectUtils.defaultIfNull(...)`                                                        |
| `ArrayUtils`                       | Dodatkowe metody dla tablic                                                                                                          |
| `RandomStringUtils`                | Generowanie losowych stringów                                                                                                        |
| `Validate`                         | Walidacja argumentów z automatycznym rzucaniem wyjątków                                                                              |
| `StopWatch`                        | Pomiar czasu trwania operacji                                                                                                        |
| `ExceptionUtils`                   | Obsługa wyjątków (np. wyciąganie przyczyn, getStackTrace(e))                                                                                           |
| `ToStringBuilder`, `EqualsBuilder` | Ułatwienia do implementacji metod `toString()`, `equals()` i `hashCode()`                                                            |

```
<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-lang3</artifactId>
	<version>3.13.0</version> <!-- lub najnowsza wersja -->
</dependency>
```		