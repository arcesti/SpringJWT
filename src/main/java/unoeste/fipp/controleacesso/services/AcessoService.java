package unoeste.fipp.controleacesso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.controleacesso.entities.Usuario;
import unoeste.fipp.controleacesso.repositories.UsuarioRepository;
import unoeste.fipp.controleacesso.security.JWTTokenProvider;

@Service
public class AcessoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public String autenticar (String nome, String senha) {
        Usuario usuario = usuarioRepository.findByNome(nome);
        String token = null;
        if(usuario != null && usuario.getSenha().equals(senha)) {
            token = JWTTokenProvider.getToken(nome, ""+usuario.getNivel());
        }
        return token;
    }

    public boolean validarToken(String token) {
        try {
            return JWTTokenProvider.verifyToken(token);
        } catch (Exception e) {
            return false;
        }
    }
}
