package br.com.implantacao.sgp_autenticacao.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.implantacao.sgp_autenticacao.entity.UsuarioEntity;
import br.com.implantacao.sgp_autenticacao.repository.UsuarioRepository;


@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioContoller.class)
public class UsuarioContollerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UsuarioRepository usuarioRepository;
	@Test
	public void ListarUsuarios() throws Exception {
		
		UsuarioEntity Usuario1 = new UsuarioEntity();
		Usuario1.setId(1);
		Usuario1.setNome("Alex");
		Usuario1.setCpf("123.456.789-00");
		Usuario1.setEmail("Alex@gmail.com");
		Usuario1.setSenha("senhaSegura321");
		Usuario1.setCep("10010-000");
		Usuario1.setRua("Praça da Sé");
		Usuario1.setNumero("321");
		Usuario1.setComplemento("Apto 42");
		Usuario1.setBairro("Sé");
		Usuario1.setCidade("São Paulo");
		Usuario1.setEstado("SP");
		Usuario1.setAreaDeAtuacao("Tecnologia da Informação");
		Usuario1.setDataHoraCadastro(LocalDate.now());
		
		UsuarioEntity Usuario2 = new UsuarioEntity();
		Usuario2.setId(2);
		Usuario2.setNome("Maria Silva");
		Usuario2.setCpf("123.456.789-00");
		Usuario2.setEmail("maria.silva@gmail.com");
		Usuario2.setSenha("senhaSegura123");
		Usuario2.setCep("01001-000");
		Usuario2.setRua("Praça da Sé");
		Usuario2.setNumero("123");
		Usuario2.setComplemento("Apto 42");
		Usuario2.setBairro("Sé");
		Usuario2.setCidade("São Paulo");
		Usuario2.setEstado("SP");
		Usuario2.setAreaDeAtuacao("Tecnologia da Informação");
		Usuario2.setDataHoraCadastro(LocalDate.now());
	
		when(usuarioRepository.findAll())
			.thenReturn(Arrays.asList(Usuario1,Usuario2));
		mockMvc.perform(get("/Usuario/listarTodos"))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void ListarUsuarioPorID() throws Exception {
		
		UsuarioEntity Usuario = new UsuarioEntity();
		Usuario.setId(1);
		Usuario.setNome("Maria Silva");
		Usuario.setCpf("123.456.789-00");
		Usuario.setEmail("maria.silva@gmail.com");
		Usuario.setSenha("senhaSegura123");
		Usuario.setCep("01001-000");
		Usuario.setRua("Praça da Sé");
		Usuario.setNumero("123");
		Usuario.setComplemento("Apto 42");
		Usuario.setBairro("Sé");
		Usuario.setCidade("São Paulo");
		Usuario.setEstado("SP");
		Usuario.setAreaDeAtuacao("Tecnologia da Informação");
		Usuario.setDataHoraCadastro(LocalDate.now());
		
		when(usuarioRepository.findById(1))
			.thenReturn(Optional.of(Usuario));
		
		mockMvc.perform(get("/Usuario/listarUsuarioPorID/1"))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void DeletarUsuarioPorID() throws Exception {
		
		mockMvc.perform(delete("/Usuario/deletar/1"))
			.andDo(print())
			.andExpect(status().isNoContent());
		verify(usuarioRepository).deleteById(1);
		
	}
	
	@Test
	public void SalvarUsuario() throws Exception {
		
		UsuarioEntity Usuario = new UsuarioEntity();
		Usuario.setId(1);
		Usuario.setNome("Maria Silva");
		Usuario.setCpf("123.456.789-00");
		Usuario.setEmail("maria.silva@gmail.com");
		Usuario.setSenha("senhaSegura123");
		Usuario.setCep("01001-000");
		Usuario.setRua("Praça da Sé");
		Usuario.setNumero("123");
		Usuario.setComplemento("Apto 42");
		Usuario.setBairro("Sé");
		Usuario.setCidade("São Paulo");
		Usuario.setEstado("SP");
		Usuario.setAreaDeAtuacao("Tecnologia da Informação");
		Usuario.setDataHoraCadastro(LocalDate.now());
		
		when(usuarioRepository.save(any(UsuarioEntity.class)))
			.thenReturn(Usuario);
		String json = "{"
				+ "\"id\":1,"
				+ "\"nome\":\"Maria Silva\","
				+ "\"cpf\":\"123.456.789-00\","
				+ "\"email\":\"maria.silva@gmail.com\","
				+ "\"senha\":\"senhaSegura123\","
				+ "\"cep\":\"01001-000\","
				+ "\"rua\":\"Praça da Sé\","
				+ "\"numero\":\"123\","
				+ "\"complemento\":\"Apto 42\","
				+ "\"bairro\":\"Sé\","
				+ "\"cidade\":\"São Paulo\","
				+ "\"estado\":\"SP\","
				+ "\"areaDeAtuacao\":\"Tecnologia da Informação\","
				+ "\"dataHoraCadastro\":\"2026-06-16\""
				+ "}";
		
		mockMvc.perform(post("/Usuario/gravar")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andDo(print())
			.andExpect(status().isCreated());
	}
	@Test
	public void AtualizarUsuario() throws Exception {
		
		UsuarioEntity UsuarioAtualizado = new UsuarioEntity();
		UsuarioAtualizado.setId(1);
		UsuarioAtualizado.setNome("Maria Silva");
		UsuarioAtualizado.setCpf("123.456.789-00");
		UsuarioAtualizado.setEmail("maria.silva@gmail.com");
		UsuarioAtualizado.setSenha("senhaSegura123");
		UsuarioAtualizado.setCep("01001-000");
		UsuarioAtualizado.setRua("Praça da Sé");
		UsuarioAtualizado.setNumero("123");
		UsuarioAtualizado.setComplemento("Apto 42");
		UsuarioAtualizado.setBairro("Sé");
		UsuarioAtualizado.setCidade("São Paulo");
		UsuarioAtualizado.setEstado("SP");
		UsuarioAtualizado.setAreaDeAtuacao("Tecnologia da Informação");
		UsuarioAtualizado.setDataHoraCadastro(LocalDate.now());
		
		when(usuarioRepository.existsById(1))
				.thenReturn(true);
		
		when(usuarioRepository.save(any(UsuarioEntity.class)))
				.thenReturn(UsuarioAtualizado);
		
		String json = "{"
				+ "\"id\":1,"
				+ "\"nome\":\"Maria Silva\","
				+ "\"cpf\":\"123.456.789-00\","
				+ "\"email\":\"maria.silva@gmail.com\","
				+ "\"senha\":\"senhaSegura123\","
				+ "\"cep\":\"01001-000\","
				+ "\"rua\":\"Praça da Sé\","
				+ "\"numero\":\"123\","
				+ "\"complemento\":\"Apto 42\","
				+ "\"bairro\":\"Sé\","
				+ "\"cidade\":\"São Paulo\","
				+ "\"estado\":\"SP\","
				+ "\"areaDeAtuacao\":\"Tecnologia da Informação\","
				+ "\"dataHoraCadastro\":\"2026-06-16\""
				+ "}";
		
		mockMvc.perform(put("/Usuario/atualizar/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(print())
				.andExpect(status().isOk());
		
	}
	
}
