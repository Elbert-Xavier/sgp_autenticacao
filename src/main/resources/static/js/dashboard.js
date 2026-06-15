document.addEventListener("DOMContentLoaded", function() {
    carregarDadosUsuario();
});

function carregarDadosUsuario() {
	
    const usuarioString = localStorage.getItem('usuarioLogado');

    if (!usuarioString) {
        window.location.href = 'index.html';
        return;
    }

    const usuario = JSON.parse(usuarioString);
	
    const nomeCompleto = usuario.nome || "Visitante";
    const primeiroNome = nomeCompleto.split(' ')[0]; 
    document.getElementById('boas-vindas-nome').textContent = `Olá, ${primeiroNome}!`;
    document.getElementById('nome-usuario').textContent = nomeCompleto;

    document.getElementById('email-usuario').textContent = usuario.email || "E-mail não informado";

 
    let valorFuncaoDB = usuario.areaDeAtuacao || usuario.atuacao || usuario.funcao || "";
    let funcaoTexto = "Usuário do Sistema"; // Valor padrão caso venha vazio do banco

    // Tradutor: Transforma o valor do banco no texto bonito para a tela
    if (valorFuncaoDB === "vendas" || valorFuncaoDB === "Vendas de Peças") {
        funcaoTexto = "Vendas de Peças";
    } else if (valorFuncaoDB === "mecanica" || valorFuncaoDB === "Mecânica") {
        funcaoTexto = "Mecânica";
    } else if (valorFuncaoDB === "logistica" || valorFuncaoDB === "Logística") {
        funcaoTexto = "Logística";
    } else if (valorFuncaoDB !== "") {
        // Se for outra coisa, apenas coloca a primeira letra maiúscula
        funcaoTexto = valorFuncaoDB.charAt(0).toUpperCase() + valorFuncaoDB.slice(1);
    }

    document.getElementById('funcao-usuario').textContent = funcaoTexto;

    const partesNome = nomeCompleto.split(' ');
    let iniciais = '';
    if (partesNome.length >= 2) {
        iniciais = partesNome[0].charAt(0) + partesNome[1].charAt(0);
    } else {
        iniciais = partesNome[0].charAt(0) + (partesNome[0].length > 1 ? partesNome[0].charAt(1) : '');
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
    localStorage.removeItem('estado'); // Limpa o estado
    
    window.location.replace('index.html'); 
}

window.addEventListener('load', () => {
    const btnSair = document.querySelector('.btn-logout');
    if(btnSair) {
        btnSair.addEventListener('click', realizarLogout);
    }
});