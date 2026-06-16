let revelarValor = 1

document.addEventListener("DOMContentLoaded", function() {
    localStorage.removeItem(estado)
	localStorage.removeItem(usuarioLogado)
});

async function revelar() {
	
	const revela = document.getElementById('senha')
	console.log(revela)
	
	if(revelarValor == 1){
		revela.type = "text";
		revelarValor=2;
		
	}else if(revelarValor == 2) {
		revela.type = "password";
		revelarValor=1;
	}
}

async function logar(){
	const email = document.getElementById('email').value
	const senha = document.getElementById('senha').value
	
	const Usuario = {
			email: email,
			senha: senha
	};
	const response = await fetch("http://localhost:8010/Usuario/login", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(Usuario)
		
	});
	if(response.ok) {
		const data = await response.json();
		localStorage.setItem(
			"usuarioLogado",
			JSON.stringify(data)
		);
		localStorage.setItem('estado','logado')
		window.location.href = "dashboard.html";
	}else{
		alert("Email ou Senha invalidos!");
	}
}
