package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.IAppUsuarioRepository;

@Service
public class AppUsuarioService implements UserDetailsService {
    private final IAppUsuarioRepository usuarioRepository;

    @Autowired //la inyección de dependencias
    public AppUsuarioService(IAppUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).orElseThrow((()-> new UsernameNotFoundException("El email del usuario no ha sido encontrado")));
    }
}
