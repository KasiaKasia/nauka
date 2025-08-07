package pl.przyklad.nauka.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
/*
 * @EnableWebSecurity - adnotacja informuje Spring , że ta klasa jest odpowiedzialna za konfigurację mechanizmów 
 * zabezpieczeń webowych
 * */
@Configuration
@EnableWebSecurity
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
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/bezUwierzytelniania", "/bezHaslaINazwyUzytkownika")
				.permitAll().anyRequest().authenticated()).httpBasic(withDefaults());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
        var root = User.builder()
                .username("root")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        var janek = User.builder()
                .username("janek")
                .password(passwordEncoder().encode("pass"))
                .roles("CUSTOMER")
                .build();


		return new InMemoryUserDetailsManager(root, janek);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
