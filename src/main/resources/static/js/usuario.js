const API_LISTAR_TODOS='http://localhost:8000/Usuario/listarTodos';
const API_LISTAR_USUARIO_POR_ID='http://localhost:8000/Usuario/listarUsuarioPorID';
const API_GRAVAR='http://localhost:8000/Usuario/gravar';
const API_DELETAR='http://localhost:8000/Usuario/deletar';
const API_ATUALIZAR_ID='http://localhost:8000/Usuario/atualizar';


// Mapeando os elementos do formulário HTML
const formUsuario  = document.getElementById('formUsuario');
const inputNome    = document.getElementById('nome');
const inputCpf     = document.getElementById('cpf');
const inputEmail   = document.getElementById('email');
const inputSenha   = document.getElementById('senha');
const inputCep     = document.getElementById('cep');
const inputRua     = document.getElementById('rua');
const inputNumero  = document.getElementById('numero');
const inputComple  = document.getElementById('complemento');
const inputBairro  = document.getElementById('bairro');
const inputCidade  = document.getElementById('cidade');
const selectEstado = document.getElementById('estado');
const selectAtuaca = document.getElementById('atuacao');

// Escutando o evento de envio (Submit) do formulário
formUsuario.addEventListener('submit', async (event) => {
    event.preventDefault(); // Impede a página de recarregar

    // 1. Captura os valores dos campos do HTML
    const dadosUsuario = {
        nome: inputNome.value,
        cpf: inputCpf.value,
        email: inputEmail.value,
        senha: inputSenha.value,
        cep: inputCep.value,
        rua: inputRua.value,
        numero: inputNumero.value,
        complemento: inputComple.value,
        bairro: inputBairro.value,
        cidade: inputCidade.value,
        estado: selectEstado.value,
        atuacao: selectAtuaca.value
    };

    // 2. Envia os dados para a API de Gravar
    try {
        const response = await fetch(API_GRAVAR, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosUsuario) // Converte o objeto JS para JSON
        });

        if (response.ok) {
            const resultado = await response.json();
            alert('Usuário cadastrado com sucesso!');
            formUsuario.reset(); // Limpa o formulário após o sucesso
        } else {
            const erro = await response.text();
            alert('Erro ao cadastrar usuário: ' + erro);
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert('Não foi possível conectar ao servidor backend.');
    }
});


// Exemplo de como Buscar todos os usuários
async function listarTodosUsuarios() {
    try {
        const response = await fetch(API_LISTAR_TODOS);
        const usuarios = await response.json();
        console.log(usuarios);
        return usuarios;
    } catch (error) {
        console.error('Erro ao listar:', error);
    }
}

// Exemplo de como Deletar um usuário por ID
async function deletarUsuario(id) {
    try {
        const response = await fetch(`${API_DELETAR}?id=${id}`, { method: 'DELETE' });
        if (response.ok) alert('Usuário deletado!');
    } catch (error) {
        console.error('Erro ao deletar:', error);
    }
}