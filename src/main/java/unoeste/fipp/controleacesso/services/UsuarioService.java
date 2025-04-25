package unoeste.fipp.controleacesso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.controleacesso.entities.Usuario;
import unoeste.fipp.controleacesso.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<Usuario> getAll() {
        return usuarioRepo.findAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        try {
            return usuarioRepo.save(usuario);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(Long id) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        try {
            usuarioRepo.delete(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
