function conferirLogado() {
	
	let teste = localStorage.getItem('estado')
	
	if(teste != "logado"){
		window.location.href = "index.html"
	}
	
}
document.addEventListener("DOMContentLoaded", function() {
    carregarDadosUsuario();
});

function carregarDadosUsuario() {
    // Busca os dados do usuário no localStorage (definidos na tela de login)
    const usuarioString = localStorage.getItem('usuarioLogado');

    // Se não tiver usuário logado, redireciona para o login por segurança
    if (!usuarioString) {
        window.location.href = 'index.html';
        return;
    }


    const usuario = JSON.parse(usuarioString);
	
    const primeiroNome = usuario.nome.split(' ')[0]; // Pega só o primeiro nome para o "Olá"
    document.getElementById('boas-vindas-nome').textContent = `Olá, ${primeiroNome}!`;
    document.getElementById('nome-usuario').textContent = usuario.nome;

    document.getElementById('email-usuario').textContent = usuario.email;

    let funcaoTexto = usuario.atuacao || usuario.funcao || "Usuário do Sistema";

    funcaoTexto = funcaoTexto.charAt(0).toUpperCase() + funcaoTexto.slice(1);
    document.getElementById('funcao-usuario').textContent = funcaoTexto;

    const partesNome = usuario.nome.split(' ');
    let iniciais = '';
    if (partesNome.length >= 2) {
        iniciais = partesNome[0].charAt(0) + partesNome[1].charAt(0);
    } else {
        iniciais = partesNome[0].charAt(0) + partesNome[0].charAt(1);
    }
    document.getElementById('avatar-iniciais').textContent = iniciais.toUpperCase();

    const dataAtual = new Date();
    const dataFormatada = dataAtual.toLocaleDateString('pt-BR');
    const horaFormatada = dataAtual.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
    document.getElementById('ultimo-acesso').textContent = `${dataFormatada} às ${horaFormatada}`;
}

function realizarLogout(event) {
    event.preventDefault();
    localStorage.removeItem('usuarioLogado');
	localStorage.removeItem('estado'); // Limpa a sessão
    window.location.href = 'index.html'; // Volta pro login
}

window.addEventListener('load', () => {
    const btnSair = document.querySelector('.btn-logout');
    if(btnSair) {
        btnSair.addEventListener('click', realizarLogout);
    }
});