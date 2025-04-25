package unoeste.fipp.controleacesso.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.controleacesso.entities.Usuario;
import unoeste.fipp.controleacesso.security.JWTTokenProvider;
import unoeste.fipp.controleacesso.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("apis/user")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<Object> getUsuarios() {
        // String token = httpServletRequest.getHeader("Authorization");
        //if(JWTTokenProvider.verifyToken(token)) {
            List<Usuario> usuariosLista = usuarioService.getAll();
            if (usuariosLista.isEmpty())
                return ResponseEntity.badRequest().body("Sem dados!");
            return ResponseEntity.ok(usuariosLista);
//        }
//        return ResponseEntity.badRequest().body("Acesso negado!");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(name = "id") Long id) {
        Usuario usuario;
        usuario = usuarioService.getById(id);
        if(usuario == null)
            return ResponseEntity.badRequest().body("Usuario não encontrado!");
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        if(novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);
        else
            return ResponseEntity.badRequest().body("Erro ao cadastrar novo usuario");
    }

    @PutMapping
    public ResponseEntity<Object> updUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        if(novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);
        else
            return ResponseEntity.badRequest().body("Erro ao cadastrar novo usuario");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delUsuario(@PathVariable Long id) {
        if(usuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("erro ao deletar usuario!");
    }

//    @GetMapping(value = "getUserByName/{name}")
//    public ResponseEntity<Object> getUser(@PathVariable(name = "name") String nome) {
//        Usuario usuario;
//        usuario = usuarioRepo.findByNome(nome);
//        if(usuario == null)
//            return ResponseEntity.badRequest().body("Usuario não encontrado!");
//        return ResponseEntity.ok(usuario);
//    }
}
