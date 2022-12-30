package jeff.felip.product_catalog.controllers;


import jeff.felip.product_catalog.models.Usuario;
import jeff.felip.product_catalog.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping(value = "buscarPorId")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@RequestParam Long id){
       return usuarioService.listarUsuarioPorId(id);
    }

    @GetMapping(value = "buscar-usuarios")
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios(){
        return usuarioService.listarTodosUsuarios();
    }

    @GetMapping(value = "buscarPorNome")
    public ResponseEntity<List<Usuario>> buscarUsuarioPorParteNome(@RequestParam String name){
        return usuarioService.consultarUsuarioPorParteNome(name);
    }

    @PostMapping(value = "salvar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @DeleteMapping(value = "deleteUser")
    public ResponseEntity<Usuario> deletarUsuario(@RequestParam Long id){
        System.out.println(id);
        return usuarioService.deletarusuarioPorId(id);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> AtualizarusuarioPorId(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.atualizarUsuario(id, usuario);
    }




}
