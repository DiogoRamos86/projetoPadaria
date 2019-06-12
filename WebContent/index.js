SENAI = new Object();



//inicio evento do botão
function usuario() {
	
	//criando variavel, e usando um metodo para
	//converter uma String para Base64
	var $SenhaCodificada = btoa($('#senha').val());
	var $Usuario = $('#usuarioLogin').val();
	
	var dados = {
			usuario: $Usuario,
			senha: $SenhaCodificada
};
	// enviando a resposta via POST
  $.post("login", dados,
       function(data, status) {
        		window.location = data.url;    	
               
         }
        );
    	
    	return false;
    	
    }