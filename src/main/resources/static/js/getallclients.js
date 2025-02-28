angular.module('demo', [])
.controller('Hello', function($scope, $http) {
//	alert("controller angular para get all");
	var vm=this;
	vm.clientes = {};
	vm.idCliente = 0;
	
	vm.detalle = function(idCli){
		alert("idCliente: "+idCli);
	}
	
	$http.get('/clientes').
        then(function(response) {
//        	alert(response.data);
//        	console.log("clientes "+response.data);
        	vm.clientes = response.data;
        	//console.log("clientes "+vm.clientes);
        });
//	vm.clientes = [{"idCliente":1,"nombre":"Katherine Baeza","telefono":"987741632","direccion":"Calti 2636 Los Pinos","fecha":null},{"idCliente":2,"nombre":"Fernanda Arriagada","telefono":"973379523","direccion":"Mesa de Aragón","fecha":"2017-03-27T00:00:00.000+0000"},{"idCliente":3,"nombre":"Sergio Poblete","telefono":"932457689","direccion":"Paseo Los Sauces 8431","fecha":"2019-01-30T00:00:00.000+0000"},{"idCliente":4,"nombre":"Juan Cortés","telefono":"919283746","direccion":"otra dirección","fecha":"2019-11-01T00:00:00.000+0000"},{"idCliente":5,"nombre":"Pablo Abraira","telefono":"971824589","direccion":"dirección de Pablo","fecha":"2019-04-25T00:00:00.000+0000"},{"idCliente":6,"nombre":"Fernando Ubiergo","telefono":"923457698","direccion":"dirección de Fernando","fecha":"2019-10-15T00:00:00.000+0000"},{"idCliente":7,"nombre":"Juan Jara","telefono":"987564312","direccion":"dirección de la casa","fecha":"2019-02-23T00:00:00.000+0000"},{"idCliente":8,"nombre":"José Gomez","telefono":"923548791","direccion":"camino a Lo Prado 1234","fecha":"2019-12-13T00:00:00.000+0000"},{"idCliente":9,"nombre":"Pedro Vargas","telefono":"945638719","direccion":"sierra Bella 4567","fecha":"2019-01-22T00:00:00.000+0000"},{"idCliente":10,"nombre":"Javier Solis","telefono":"923984761","direccion":"Santa Rosa 879","fecha":"2019-01-18T00:00:00.000+0000"},{"idCliente":11,"nombre":"Sara Lara","telefono":"956348923","direccion":"La Florida 9876","fecha":"2019-07-21T00:00:00.000+0000"}];
});