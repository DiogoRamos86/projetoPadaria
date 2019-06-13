SENAI.funcionario = new Object();

$(document)
.ready(
		function() {
			
			SENAI.funcionario.cadastrar = function() {
			

				if (document.getElementById("fname").value == ""
						|| document.getElementById("lname").value == ""
						|| document.getElementById("email").value == "")
						

				{
					alert("Todos os campos são obrigatórios de preenchimento!");
					document.getElementById("fname").focus();
					return false;
					
				} else {
					
					$.ajax({
						
						type : "POST",
						url : "../../cadastrarFuncionario",
						data : $("#cadastrarFuncionario").serialize(), 
						success : function(msg) {
							var cfg = {
								title : "Mensagem",
								heigth : 250,
								width : 400,
								modal : true,
								buttons : {
									"Ok" : function() {
										$(this).dialog("close");

									}
								}
							};
							$("#msg").html(msg.msg);
							$("#msg").dialog(cfg);
							
							document.getElementById("fname").value = "";
							document.getElementById("lname").value = "";
							document.getElementById("email").value = "";
							
							// Atualiza a tabela de contatos
							SENAI.funcionario.buscar();
						},
						error : function(rest) {
							alert("Erro ao cadastrar um novo contato");
						}
					});

				}
			};   
		});