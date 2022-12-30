package jeff.felip.product_catalog.services;

import jeff.felip.product_catalog.models.Usuario;
import jeff.felip.product_catalog.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<List<Usuario>> listarTodosUsuarios(){
        if (usuarioRepository.findAll() == null){
            return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Usuario> listarUsuarioPorId(Long id){
        var usurioConsultado = usuarioRepository.findById(id).get();
        if (usurioConsultado == null){
            return new ResponseEntity<>(usurioConsultado, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usurioConsultado, HttpStatus.OK);
    }

    public ResponseEntity<Usuario> salvarUsuario(Usuario usuario){
        if (usuarioRepository.save(usuario) == null){
            return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
    }

    public ResponseEntity<Usuario> deletarusuarioPorId(Long id){
        var userpegado = listarUsuarioPorId(id).getBody();
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(userpegado, HttpStatus.OK);
    }


    public ResponseEntity<Usuario> atualizarUsuario(Long id, Usuario usuario) {
        var usuarioAtualizado = listarUsuarioPorId(id).getBody();
        usuarioAtualizado.setAge(usuario.getAge());
        usuarioAtualizado.setName(usuario.getName());
        usuarioAtualizado = salvarUsuario(usuarioAtualizado).getBody();
        if(usuarioAtualizado == null){
            return new ResponseEntity (usuarioAtualizado, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (usuarioAtualizado, HttpStatus.OK);
    }

    public ResponseEntity<List<Usuario>> consultarUsuarioPorParteNome(String name) {
        if (usuarioRepository.findUsuarioByName(name) == null){
            return new ResponseEntity<>(usuarioRepository.findUsuarioByName(name), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioRepository.findUsuarioByName(name), HttpStatus.OK);
    }
}
