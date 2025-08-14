package pl.przyklad.nauka.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import pl.przyklad.nauka.uczen.Uczen;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/*
 * @EnableWebSecurity - adnotacja informuje Spring , że ta klasa jest odpowiedzialna za konfigurację mechanizmów 
 * zabezpieczeń webowych
 * */
@Configuration
@EnableWebSecurity
/*
 * @EnableMethodSecurity - adnotacja jest używana w Spring do włączenia zabezpieczeń na poziomie metod dla aplikacji
 * */
@EnableMethodSecurity(proxyTargetClass=true)
public class SecurityConfig {

	/*
	 * securityFilterChain - definuje filtr bezpieczeństwa , który przetwarza
	 * ządania HTTP Konfiguruje się tu uwierzytelnienie i autoryzację Gdzie
	 * mechanizmem uwierzytelniania jest mechanizm HTTP basic, ktory wymaga podania
	 * nazwy użytkowwnika i hasła w formie nagłówka HTTP authorization Czyli haslo i
	 * nazwa uzytkownika musi być wysłana z kazdym zapytaniem
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

// standardowa nazwa użytkownika to: user, a hasło wyswietla się w konsoli

		// httpBasic - każde request ma być uwierzytelniane przez HTTP basic
		// dla endpoint "http://localhost:8081/bezUwierzytelniania",
		// "http://localhost:8081/bezHaslaINazwyUzytkownika" będzie wyłaczona
		// autoryzacja
		http
		.cors(cors -> cors.disable()) // włącza obsługę CORS (możesz później skonfigurować np. CorsConfigurationSource jako bean).
        .csrf(csrf -> csrf.disable()) //  wyłącza CSRF, co przy REST API + stateless JWT/basic auth jest standardem.
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ustawia tryb sesji na stateless, czyli Spring Security nie tworzy i nie utrzymuje sesji HTTP.
        ).authorizeHttpRequests(auth -> auth.requestMatchers("/bezUwierzytelniania", "/bezHaslaINazwyUzytkownika")
				.permitAll()
				.anyRequest()
				.authenticated())
        .httpBasic(withDefaults()); //  ustawia HTTP Basic jako mechanizm uwierzytelniania.

		return http.build();
	}
// to juz mamy jako UserService
//	@Bean
//	public UserDetailsService userDetailsService() {
//        var root = User.builder()
//                .username("root")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        var janek = User.builder()
//                .username("janek")
//                .password(passwordEncoder().encode("pass"))
//                .roles("CUSTOMER")
//                .build();
//
//
//		return new InMemoryUserDetailsManager(root, janek);
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
/*
 * AuthenticationManager - to interface w Spring Security, ktory odpowiada za proces uwierzytelniania użytkowników
 * Jego głownym zadaniem jest przyjmowanie obiektu authentication i próba uwierzyteniania użytkownika z wykorzystaniem 1 lub wielu dostępnych 
 * authentication providerów . Authentication provider w tym przykłądzie to: DaoAuthenticationProvider - to implementacja prowadera, 
 * która właśnie używa UserDetailsService do pobrania informacji o użytkowniku i porównania hasła z hasłem przekazanym w procesie uwierzytelniania
 * */
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		
		/*
		 * W Spring Boot, wzorzec Data Access Object (DAO) służy do oddzielenia logiki dostępu do danych od reszty aplikacji.
		 * */
		var provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(provider);
	}

}
