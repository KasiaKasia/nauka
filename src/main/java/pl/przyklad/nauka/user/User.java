package pl.przyklad.nauka.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String username;

    private String password;
    /*
     * @OneToMany – mówi, że User może mieć wiele obiektów Authority.
	 *	@JoinColumn(name = "username") – wskazuje, że w tabeli authorities jest kolumna username, 
	 *  która przechowuje klucz obcy wskazujący na User.username.	
     *  EAGER → pobiera dane natychmiast, przy wczytywaniu encji.
 	 *	LAZY → pobiera dane dopiero, gdy będą potrzebne (np. przy pierwszym wywołaniu getAuthorities()).
     * */

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private List<Authority> authorities;

    @Column(name="non_expired")
    private boolean isAccountNonExpired;

    @Column(name="credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name="non_locked")
    private boolean isAccountNonLocked;

    @Column(name="enabled")
    private boolean isEnabled;
}
