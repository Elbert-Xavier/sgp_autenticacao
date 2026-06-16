const API_BUSCAR_GMAIL ='http://localhost:8010/Usuario/BuscarPorEmail';
const API_NOVA_SENHA = 'http://localhost:8010/Usuario/novaSenha';

async function novasenha() {
	
	let senhanova = document.getElementById('nova-senha').value
	let confirmacao = document.getElementById('confirmar-senha').value
	
	if(senhanova == confirmacao) {
		
		alert("Senha redefinida Com sucesso")
	
	const gmailUsuario = new URLSearchParams(window.location.search);
	const gmail = gmailUsuario.get('email');
	
	const response = await fetch(`${API_BUSCAR_GMAIL}/${gmail}`)
	const dados = await response.json();
	
	console.log(dados)
	
	
		
	const salvar = await fetch(`${API_NOVA_SENHA}/${senhanova}/${confirmacao}`,{
			method : 'PUT',
			headers : {
				'Content-Type' : 'application/json'
			},
			body : JSON.stringify(dados)
		})
		
		window.location.href ="index.html"
		
		console.log(salvar)
	}else{
		alert("Senhas digitadas nao conferem")
		document.getElementById('confirmar-senha').value = "";
		
	}
	
}
