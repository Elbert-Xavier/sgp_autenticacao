package br.com.implantacao.sgp_autenticacao.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.implantacao.sgp_autenticacao.entity.UsuarioEntity;
import br.com.implantacao.sgp_autenticacao.repository.UsuarioRepository;

@RestController
@RequestMapping("/Usuario")
@CrossOrigin("*")
public class UsuarioContoller {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listarTodos")
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioEntity>BuscarUsuario(){
		return usuarioRepository.findAll();
	}
	@GetMapping("/listarUsuarioPorID/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<UsuarioEntity>BuscarUsuarioPorID(@PathVariable Integer id){
		return usuarioRepository.findById(id);
	}
	
	@PostMapping("/gravar")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioEntity gravarUsuario(@RequestBody UsuarioEntity Usuario) {
		return usuarioRepository.save(Usuario);
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deletarUsuario(@PathVariable Integer id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return "Usuario Deletado";
		}
			return "Usuario Não Encontrado";
		
	}
	
	@PutMapping("/atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioEntity atualizarUsuario(@PathVariable Integer id,@RequestBody UsuarioEntity Usuario) {
		if (usuarioRepository.existsById(id)) {
			Usuario.setId(id);
			return usuarioRepository.save(Usuario);
		}return null;
	
}

}

