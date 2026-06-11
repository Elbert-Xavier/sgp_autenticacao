const API_ENVIAR_GMAIL ='http://localhost:8000/email';
const API_BUSCAR_GMAIL ='http://localhost:8000/Usuario/BuscarPorEmail';

async function enviarCodigo() {
	
	let gmail = document.getElementById('emailDigitado').value;
	
	const response = await fetch(`${API_BUSCAR_GMAIL}/${gmail}`)
	const dados = await response.json;
	
	console.log(dados)
	
}