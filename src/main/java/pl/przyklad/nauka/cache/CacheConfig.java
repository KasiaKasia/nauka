package pl.przyklad.nauka.cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
 * @EnableCaching - adnotacja włacza obsługę mechanizmu cache'owania dla bina odpowiedzialnego z cache
 * Mozna skonfigurować w niej dodatkowe opcje miin. wybór konkretnego dostawcy cache np.: E-cache czy Redis
 * oraz można ustawić konfigurację zachowannia cache np.: czas trwania itp
 * */
@EnableCaching
public class CacheConfig {

}
