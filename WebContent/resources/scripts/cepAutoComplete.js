function findCEP() {

	$.ajax({
		url : "http://apps.widenet.com.br/busca-cep/api/cep.json",
		type : "GET",
		data : {
			"code" : document.getElementById("cep").value
		},
		dataType : "json",
		success : function(data) {
			$("#cep").val(data.code);
			$("#cidade").val(data.city);
			$("#logradouro").val(data.address);
			$("#bairro").val(data.district);
			$("#uf").val(data.state);
			$("#numero").focus();
		},
		error : function() {
			alert(result.message || "Houve um erro desconhecido");
		}
	});

}

function clickSuccess() {
	$('#modalOutroEndereco').modal('hide');
}

function pedidoAncora() {
	this.location = "#pedidoInfo";
}

function pedidoEmpresaAncora() {
	this.location = "#formInfoPedido";
}