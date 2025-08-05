# Przykładowy projekt Spring


## Istotne pojęcia

### pom.xml 
pom = Project Object Model
pom.xml = główny plik konfiguracyjny projektu opartego na Maven. Formtu XML zawierający informacje o projekcie, jego zależnościach, pluginach, kompilatorze i innych ustawieniach.

### dependency
`spring-boot-starter-validation` obsługuje m.in. takie adnotację:

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