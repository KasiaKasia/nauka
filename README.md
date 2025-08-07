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
- [ ] `junit-jupiter` - dodaje do projektu JUnit Jupiter, czyli główny moduł JUnit 5, który służy do pisania i uruchamiania testów jednostkowych w Javie.

junit-jupiter to zestaw bibliotek testowych, który obejmuje:

1. API do pisania testów (adnotacje i asercje):
- @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, @DisplayName, @Disabled itp.
- Assertions.assertEquals(), Assertions.assertThrows() itd.

2. Engine testowy do uruchamiania testów Jupiter (czyli JUnit 5).

```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version> <!-- lub nowsza -->
    <scope>test</scope>
</dependency>
```

- [ ] `assertj-core` - to nowoczesna, rozszerzalna biblioteka do pisania asercji w testach jednostkowych w języku Java. 
Jest często używana razem z JUnit (głównie JUnit 5 – Jupiter) i zapewnia bardziej czytelne, płynne i rozbudowane asercje niż standardowe assertEquals, assertTrue itp.


 ✅ Bogaty zestaw metod porównujących dane:

- isEqualTo(), isNotEqualTo()
- isNull(), isNotNull()
- contains(...), doesNotContain(...) (dla kolekcji i ciągów znaków)
- startsWith(), endsWith(), matches(...) (dla tekstów)
- isEmpty(), hasSize(...), containsExactly(...) (dla kolekcji)
- hasFieldOrPropertyWithValue(...) (dla obiektów)
- extracting(...) – do mapowania wartości z obiektu

 ✅ Obsługuje wiele typów danych:

- Obiekty (POJO)
- Kolekcje (List, Set, Map)
- Tablice (int[], String[])
- Stringi (String)
- Strumienie (Stream)
- Daty (LocalDate, ZonedDateTime itd.)
- Wyjątki (assertThatThrownBy(...))

```
<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <version>3.24.2</version> <!-- lub inna wersja -->
    <scope>test</scope>
</dependency>

```
- [ ] `mockito-junit-jupiter` - służy do mackowania obiektów w testach jednostkowych. Makowanie to technika imitacji obiektów, które zastępują rzeczywistość w testach jednostkowych.


Zawartość biblioteki `mockito-junit-jupiter`

| Element                                   | Opis                                                      |
| :---         						 |     :--- |
|   `MockitoExtension`                        | Umożliwia integrację z JUnit 5 za pomocą `@ExtendWith`.   |
|   Obsługa `@Mock`, `@InjectMocks`           | Można używać bez ręcznego inicjowania (`initMocks`).      |
|   Integracja z silnikiem Jupiter (JUnit 5)  | Współpracuje z cyklem życia testów (`@BeforeEach`, itp.). | 

```
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.12.0</version> <!-- lub najnowsza dostępna wersja -->
    <scope>test</scope>
</dependency>
 
```

- [ ] `spring-boot-starter-cache` - zależność dodana do projektu Spring Boot wspiera cachowanie (pamięci podręcznej).

Poozwala używać adnotacji takich jak:

| Element 	| Opis                                                      |
| :--- 		|     :--- |
|`@EnableCaching`	| włącza mechanizm cache w aplikacji Spring |
|`@Cacheable` 		| oznacza, że wynik metody ma być cache’owany |
|`@CachePut` 		| aktualizuje dane w cache |
|`@CacheEvict` 		| usuwa dane z cache |


```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
```


## Piramida testów
 ✅ E2E
 ✅ Integracyjne
 ✅ Jednostkowe

Piramida testów - hierarchia różnych testów typów testów. 
Gdzie liczba testów zależy od kosztu ich wykonania. Im typ testu jest tańszy i szybszy w uruchomieniu, tym niżej znajduje się on w piramidzie testów.
Jednocześnie wielkość pola w piramidzie oznacza udział liczby testów w ogólnej liczbie wszystkich testów. 
Oznacza to, że testy jednostkowe są najbardziej stabilne, najtańsze i najszybsze w wykonaniu i tych testów powinno być najwięcej.

Nieco droższe w wykonaniu będą testy integracyjne, a najdroższe są testy E2E.

## Testy Black-box (testowanie czarnoskrzynkowe) i White-box (testowanie białoskrzynkowe)


🔳 White-box 

🧠 Opis:

✅ Tester ZNA kod źródłowy i jego logikę.

✅ Testy opierają się na wewnętrznej strukturze i przepływie kontroli.

✅Sprawdza się konkretne ścieżki, warunki, pętle itp.


🔳 Black-box

🧠 Opis:

✅ Tester NIE ZNA wewnętrznej implementacji kodu.

✅ Testuje się zewnętrzne zachowanie aplikacji: wejścia → wyjścia.

✅ Często stosowane przez testerów manualnych, QA, a także w testach integracyjnych i akceptacyjnych.