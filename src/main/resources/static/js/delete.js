//alert("controller angular para el delete");
angular
	.module('app', [])
	.controller('appCtrl', ['$http', controladorPrincipal]);

function controladorPrincipal($http){
	var vm=this;
	//inicializo un objeto en los datos de formulario
	vm.fdatos = {};
//	vm.idCliente = 0;
	vm.idCliente;
	var url = "/clientes/";
	
	vm.delete2 = function(){
		if((vm.idCliente == null) || vm.idCliente == "") {
			alert("vm.idCliente es nulo  ");
		} else {
			alert("vm.idCliente no es nulo: "+vm.idCliente);
		}
		
	}
	// declaro la función enviar
	vm.delete = function(){
		if((vm.idCliente == null) || vm.idCliente == "") {
			alert("Debe ingresar el ID del Cliente !!!");		
		} else {
//			url = url+vm.idCliente;
			$http.delete(url+vm.idCliente)
				.then(function(res){
					url = "/clientes/";
					console.log(res);
					//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
					});				
		}

	}
}