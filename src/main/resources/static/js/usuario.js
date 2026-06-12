const API_GRAVAR='http://localhost:8000/Usuario/gravar';

async function salvarUsuario() {
	
	const Usuario = {
	    nome: document.getElementById('nome').value,
	    cpf: document.getElementById('cpf').value,
	    email: document.getElementById('email').value,
	    senha: document.getElementById('senha').value,
	    cep: document.getElementById('cep').value,
	    rua: document.getElementById('rua').value,
	    numero: document.getElementById('numero').value,
	    complemento: document.getElementById('complemento').value,
	    bairro: document.getElementById('bairro').value,
	    cidade: document.getElementById('cidade').value,
	    estado: document.getElementById('estado').value,
	    areaDeAtuacao: document.getElementById('atuacao').value
	};
		
		await fetch(API_GRAVAR,{
			method : 'POST',
			headers : {
				'Content-Type' : 'application/json'
			},
			body : JSON.stringify(Usuario)
		})
		
		window.location.href = "index.html";
	}