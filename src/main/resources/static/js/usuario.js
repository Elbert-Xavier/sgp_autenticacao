const API_GRAVAR = 'http://localhost:8000/Usuario/gravar';

async function salvarUsuario() {
    
    const selectAtuacao = document.getElementById('atuacao');
    
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
        areaDeAtuacao: selectAtuacao.value 
    };

  
    let atuacaoTexto = "Usuário do Sistema";
    if (selectAtuacao && selectAtuacao.selectedIndex > 0) {
        atuacaoTexto = selectAtuacao.options[selectAtuacao.selectedIndex].text;
    }

    try {
        const resposta = await fetch(API_GRAVAR, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Usuario)
        });

        if (resposta.status === 409) {
            alert("Email já cadastrado. Digite outro Email");
            document.getElementById('email').focus(); // Coloca o cursor de volta no campo de e-mail
            return; // INTERROMPE a função aqui. Não salva sessão nem redireciona.
        }

        if (!resposta.ok) {
            throw new Error("Erro ao salvar no servidor.");
        }


        const usuarioSessao = {
            nome: Usuario.nome,
            email: Usuario.email,
            atuacao: atuacaoTexto 
        };
        
        localStorage.setItem('usuarioLogado', JSON.stringify(usuarioSessao));
        localStorage.setItem('estado', 'logado'); 
        
        window.location.href = "index.html";

    } catch (erro) {
        console.error("Erro na requisição:", erro);
        alert("Ocorreu um erro ao tentar conectar com o servidor.");
    }
}