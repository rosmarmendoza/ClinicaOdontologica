package models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Setter
@Getter

public class AppUsuario implements UserDetails {
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Id
    private Long id;
    private String usuarioNombre;
    private String email;
    private String contrasenia;
    @Enumerated(EnumType.STRING)
    private AppUsuarioRoles usuarioRol;



    public AppUsuario() {

    }

    public AppUsuario(Long id, String usuarioNombre, String email, String contrasenia, AppUsuarioRoles usuarioRol) {
        this.id = id;
        this.usuarioNombre = usuarioNombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.usuarioRol = usuarioRol;
    }

    public AppUsuario(Serializable administrador, String s, String contrasenia2, AppUsuarioRoles admin) {
    }

    //Sobrescritura de métodos de la interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioRol.name());
        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return usuarioNombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
