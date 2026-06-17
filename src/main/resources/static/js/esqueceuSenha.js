const API_ENVIAR_GMAIL ='http://192.168.10.84:8010/email';
const API_BUSCAR_GMAIL ='http://192.168.10.84:8010/Usuario/BuscarPorEmail';

async function enviarCodigo() {
	
	let gmail = document.getElementById('emailDigitado').value;
	
	const response = await fetch(`${API_BUSCAR_GMAIL}/${gmail}`)
	const dados = await response.json();
	
	if(dados){
		
		const enviargmail = await fetch(`${API_ENVIAR_GMAIL}/${gmail}`)
		alert('Email enviado com recuperação de senha enviado')
	}else{
		alert('Email não encontrado')
	}
	
	
}