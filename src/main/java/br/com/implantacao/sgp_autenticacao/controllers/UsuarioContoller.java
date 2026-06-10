package br.com.implantacao.sgp_autenticacao.controllers;

import java.util.List;

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

@RestController
@RequestMapping("Usuario")
@CrossOrigin("*")

public class UsuarioContoller {

	@Autowired
	
private br.com.implantacao.sgp_autenticacao.repository.UsuarioRepository Usu;
	
	@GetMapping("/listarTodos")
	@ResponseStatus(HttpStatus.OK)
	public List<br.com.implantacao.sgp_autenticacao.entity.UsuarioEntity>BuscarCadastro(){
		return Usu.findAll();
		
	}
	
	@PostMapping("/gravar")
	@ResponseStatus(HttpStatus.CREATED)
	public br.com.implantacao.sgp_autenticacao.entity.UsuarioEntity gravarUsuario(@RequestBody br.com.implantacao.sgp_autenticacao.entity.UsuarioEntity Usuar) {
		return Usu.save(Usuar);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/deletar/{id}")
	public String deletarUsuario(@PathVariable Integer id) {
		return "Usuario Deletado";
		
	}
	
	@PutMapping("/atualizar")
	@ResponseStatus(HttpStatus.OK)
	public String atualizartabela(@PathVariable Integer id) {
		return "Atualizado";
	
}

}

