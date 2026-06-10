package br.com.implantacao.sgp_autenticacao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table (name = "Usuario")

public class UsuarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int Id;
	private String Nome;
	private String Cpf;
	private String Email;
	private String EnderecoCompleto;
	private String AreaDeAtuacao;
	private LocalDateTime DataHoraCadastro;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCpf() {
		return Cpf;
	}
	public void setCpf(String cpf) {
		Cpf = cpf;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getEnderecoCompleto() {
		return EnderecoCompleto;
	}
	public void setEnderecoCompleto(String enderecoCompleto) {
		EnderecoCompleto = enderecoCompleto;
	}
	public String getAreaDeAtuacao() {
		return AreaDeAtuacao;
	}
	public void setAreaDeAtuacao(String areaDeAtuacao) {
		AreaDeAtuacao = areaDeAtuacao;
	}
	public LocalDateTime getDataHoraCadastro() {
		return DataHoraCadastro;
	}
	public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
		DataHoraCadastro = dataHoraCadastro;
	}
	
	
	
}
