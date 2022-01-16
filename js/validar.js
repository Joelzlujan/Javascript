function iniciar(){
	nombre1=document.getElementById('nombre');
	nombre1.addEventListener('input', validacion, false);
	mail1=document.getElementById('mail')
	mail1.addEventListener('input', mailrequerido, false);
	validacion();
}
function validacion(){
if(nombre1.value==''){
	nombre1.setCustomValidity('Por favor debe ingresar su nombre');
	nombre1.style.backgroundColor= '#ff1a1a';
	}else{
	nombre1.setCustomValidity('');
	nombre1.style.backgroundColor= '#ffffff';
}
}

function mailrequerido(){
	if (mail1.value==''){
		mail1.setCustomValidity('Por favor ingrese su mail');
		mail1.style.backgroundColor= '#ff1a1a';
	}else{
		mail1.setCustomValidity('');
		mail1.style.backgroundColor= '#ffffff';
	}
}
window.addEventListener('load', iniciar, false)
