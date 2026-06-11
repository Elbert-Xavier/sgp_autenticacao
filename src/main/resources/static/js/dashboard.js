function conferirLogado() {
	
	let teste = localStorage.getItem('estado')
	
	if(teste != "logado"){
		window.location.href = "index.html"
	}
	
}
document.addEventListener("DOMContentLoaded",() =>{
	conferirLogado();
})