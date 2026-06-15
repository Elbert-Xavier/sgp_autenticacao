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

const API_ = '';

function mascaraCPF(input) {
    let cpf = input.value.replace(/\D/g, "");
	
    if (cpf.length > 3 && cpf.length <= 6) {
        cpf = cpf.replace(/^(\d{3})(\d)/, "$1.$2");
    } else if (cpf.length > 6 && cpf.length <= 9) {
        cpf = cpf.replace(/^(\d{3})(\d{3})(\d)/, "$1.$2.$3");
    } else if (cpf.length > 9) {
        cpf = cpf.replace(/^(\d{3})(\d{3})(\d{3})(\d)/, "$1.$2.$3-$4");
    }

    // Atualiza o valor do campo
    input.value = cpf;
}
function mascaraCEP(input) {
	let cep = input.value.replace(/\D/g, '');
	if (cep.length > 5) {
	        cep = cep.replace(/^(\d{5})(\d)/, '$1-$2');
	    }
	input.value = cep
}
function validadorCPF(cpf) {
	
	let cpfSemMascara = cpf.replace(/[.-]/g, '');
	
	if(TestaCPF(cpfSemMascara) == false){
		alert("cpf invalido")
	}else{
		input.className = "is-valid";
}}

async function buscarCep(cep) {
	
	let cepLimpo = cep.replace(/\D/g, '');
	try {
	const response = await fetch(`https://viacep.com.br/ws/${cepLimpo}/json/`);
	const dados = await response.json();
	
	console.log(dados)
	
	document.getElementById('rua').value = dados.logradouro;
	document.getElementById('estado').value = dados.uf;
	document.getElementById('bairro').value = dados.bairro;
	
	}catch {
		alert("cep invalido")
		
		document.getElementById('rua').value = "";
		document.getElementById('estado').value = "";
		document.getElementById('bairro').value = "";
		
	}

}
function TestaCPF(cpf) {
    let Soma;
    let Resto;
    Soma = 0;
	if (cpf.length != 11 ||
	            cpf == "00000000000" ||
	            cpf == "11111111111" ||
	            cpf == "22222222222" ||
	            cpf == "33333333333" ||
	            cpf == "44444444444" ||
	            cpf == "55555555555" ||
	            cpf == "66666666666" ||
	            cpf == "77777777777" ||
	            cpf == "88888888888" ||
	            cpf == "99999999999")
	            return false;

  for (i=1; i<=9; i++) Soma = Soma + parseInt(cpf.substring(i-1, i)) * (11 - i);
  Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(cpf.substring(9, 10)) ) return false;

  Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(cpf.substring(i-1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(cpf.substring(10, 11) ) ) return false;
    return true;
}