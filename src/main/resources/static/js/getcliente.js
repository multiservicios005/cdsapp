//alert("controller angular para obtener un cliente");
angular
	.module('app', [])
	.controller('appCtrl', ['$http', controladorPrincipal]);

function controladorPrincipal($http){
	var vm=this;
	vm.cliente = {};
	var url = "/clientes/";
	//inicializo un objeto en los datos de formulario
	vm.idCliente;
	vm.respuesta = "";
	vm.cliente.ordenes_de_trabajo = [];
	alert("vm.idCliente"+vm.idCliente);
	
//	===============================
//	vm.idCliente = 3;
	$http.get(url+vm.idCliente)
	.then(function(res){
		console.log(res.data);
		//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
		vm.cliente = res.data;
//		alert("fecha: "+vm.cliente.fecha);
//		document.getElementById("fechacli").value="1973-09-11";
//		document.getElementById("fechacli").value=vm.cliente.fecha;
		if(res.data == null) {
			vm.respuesta = "El cliente con id = "+vm.idCliente+" no existe";
		} else {
//			vm.respuesta = "Nombre: "+vm.cliente.nombre;
//			vm.respuesta = "OT: "+vm.cliente.ordenes_de_trabajo;
			vm.respuesta = "";
		}
		
		});	
//	===============================
	
	vm.enviar22 = function(num){
//		alert("vm.cliente.nombre: "+vm.cliente.nombre);
		alert("vm.cliente.nombre.OT.observación: "+vm.cliente.ordenes_de_trabajo[0].observacion);
//		vm.cliente.ordenes_de_trabajo[0].observacion="observación modificada en el controller";
		alert("vm.cliente.nombre.OT.observación: "+vm.cliente.ordenes_de_trabajo[0].observacion);
//		agregar una fila a la tabla de Servicios
		vm.cliente.ordenes_de_trabajo[0].servicios.push({"tipoServicio":{"idServicio": 2, "nombre": "Lavado de Alfombras Sueltas"}, "detalleServicio":"Living de 5x5", "monto":46000});
	}
	
	vm.enviar3 = function(num){
		alert(" num = "+num);
	}
	
	esImpar = function( x ) {
		  return ( x & 1 );
	}
	
	vm.test = function() {
		var idxTable;
		var idxOT = 0;
		var total = 0;
		var idxSelect = 0;
		var myNodelist = document.querySelectorAll("table");
		alert(" nro de OT´s = "+myNodelist[0].rows.length);
//		ciclo para recorrer la grilla con las OT´s.
//		la grilla de servicios de la OT es considerada, por el DOM,  como una fila de la grilla de las OT´s.
//		Por eso si el cliente tiene 2 OT´s el DOM considera que esa grilla de las OT´s tiene 5 filas: la de los header, la fila de cada OT y la fila con la grilla correspondiente a los servicios de esa OT.
		for (var i = 1; i < myNodelist[0].rows.length; i++) {
//			recorrer la grilla de servicios de la OT
//			if (i == 1 || i == 3) {
			if (esImpar(i)) {
//				llenar los datos de la OT
//				despliega la obsevación de cada OT del cliente
				alert("OT: "+i+"  observación: "+myNodelist[0].rows[i].cells[3].innerHTML);
				vm.cliente.ordenes_de_trabajo[idxOT].total = myNodelist[0].rows[i].cells[1].innerHTML;
				vm.cliente.ordenes_de_trabajo[idxOT].fecha = myNodelist[0].rows[i].cells[2].innerHTML;
				vm.cliente.ordenes_de_trabajo[idxOT].observacion = myNodelist[0].rows[i].cells[3].innerHTML;
			} else {
//				recorrer la grilla de servicios de la OT
//				idxTable corresponde al índice de la tabla de servicio de la OT correspondiente
				idxTable = i/2;
//				console.log(myNodelist[0].rows[i]);
//				alert(idxTable);
//				despliega el monto del primer servicio de cada OT del cliente
				alert("OT: "+i+"  monto: "+myNodelist[idxTable].rows[1].cells[3].innerHTML);
				total = 0;
				//recorrer cada uno de los servicios de la nueva OT
				for(var k = 1; k < myNodelist[idxTable].rows.length; k++) {
//					  alert("tabla servicios fila "+k+" "+vm.cliente.ordenes_de_trabajo[0].servicios[k-1].detalleServicio);
					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio = myNodelist[idxTable].rows[k].cells[0].innerHTML;
					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.nombre = myNodelist[idxTable].rows[k].cells[1].innerHTML;
					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].detalleServicio = myNodelist[idxTable].rows[k].cells[2].innerHTML;
					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].monto = myNodelist[idxTable].rows[k].cells[3].innerHTML;
					  total = total + Number(myNodelist[idxTable].rows[k].cells[3].innerHTML);
					  alert(document.getElementsByTagName("select")[idxSelect].value);
					  idxSelect = idxSelect + 1;
				}
				 vm.cliente.ordenes_de_trabajo[idxOT].total = total;
				 idxOT = idxOT + 1;
			}
			
		}
	}
	
	// función getClient que recupera todos los datos del cliente
	vm.getClient = function(){
		//$http.put("http://localhost:8080/clientes", vm.fdatos)
		if(vm.idCliente) {
			//alert("vm.idCliente no es nulo: "+vm.idCliente);
			$http.get(url+vm.idCliente)
				.then(function(res){
					console.log(res.data);
					//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
					vm.cliente = res.data;
//					alert("fecha: "+vm.cliente.fecha);
//					document.getElementById("fechacli").value="1973-09-11";
//					document.getElementById("fechacli").value=vm.cliente.fecha;
					if(res.data == null) {
						vm.respuesta = "El cliente con id = "+vm.idCliente+" no existe";
					} else {
//						vm.respuesta = "Nombre: "+vm.cliente.nombre;
//						vm.respuesta = "OT: "+vm.cliente.ordenes_de_trabajo;
						vm.respuesta = "";
					}
					
					});			
		} else {
			alert(" Ingrese el ID de un Cliente !!! ");
		}
	}
	
//	=====================================================================
	
	// función que actualiza en la base de datos los datos modificados
	vm.modifyclient = function(){
//		if(vm.fdatos.idCliente && vm.fdatos.nombre) {
		if(vm.idCliente && vm.cliente.nombre) {
			
			if(vm.cliente.ordenes_de_trabajo.length > 0) {
				llenarDatosCliente();
			}
			
			//$http.put("/clientes", vm.fdatos)
			$http.put("/clientes", vm.cliente)
				.then(function(res){
					console.log(res);
					//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
					});			
		} else {
			alert("Debe ingresar un ID de Cliente y un nombre !!!");
		}
	}
	
//	=====================================================================
//	código reusado desde el insert.js
	
	vm.addOT = function(){
//		alert("Agregando una OT");
//		vm.cliente.ordenes_de_trabajo.push({"total":125000, "fecha":"2019-11-10", "observacion":"algo interesante", "servicios":[{"tipoServicio":{"idServicio": 2, "nombre": "Lavado de Alfombras Sueltas"}, "detalleServicio":"Living de 2x4", "monto":55000}]});
		vm.cliente.ordenes_de_trabajo.push({total:125000, fecha:"2019-11-10", observacion:"algo interesante", servicios:[{tipoServicio:{idServicio: 2, nombre: "Lavado de Alfombras Sueltas"}, detalleServicio:"Living de 2x4", monto:55000}]});
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo[0].servicios);
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo.length);
	}
	
	vm.addService = function(ot){
//		alert("Agregando una Servicio a la OT");
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo.servicios);
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo[0].servicios);
//		vm.cliente.ordenes_de_trabajo[0].servicios.push({"tipoServicio":{"idServicio": 2, "nombre": "Lavado de Alfombras Sueltas"}, "detalleServicio":"Living de 5x3", "monto":78000});
		vm.cliente.ordenes_de_trabajo[ot].servicios.push({tipoServicio:{idServicio: 2, nombre: "Lavado de Alfombras Sueltas"}, detalleServicio:"Living de 5x3", monto:78000});
	}
	
//	este método se encuentra repetido casi tal cual en insertclient.js. Hay que reestructurar el código para que el método sea el mismo en lo posible.
	llenarDatosClienteOld = function(){
//		alert("Llenar los datos del cliente");
//		var ot = new Object();
		var myNodelist = document.querySelectorAll("table");
//		llenar la OT
		vm.cliente.ordenes_de_trabajo[0].total = myNodelist[0].rows[1].cells[1].innerHTML;
		vm.cliente.ordenes_de_trabajo[0].fecha = myNodelist[0].rows[1].cells[2].innerHTML;
		vm.cliente.ordenes_de_trabajo[0].observacion = myNodelist[0].rows[1].cells[3].innerHTML;
		
//		llenar los servicios de la OT
//		var i;
//		recorrer cada una de las tablas de la página
//		for (i = 1; i < myNodelist.length; i++) {
//		  alert("TABLA  "+i+"  length = "+myNodelist[i].rows.length);
		  var total = 0;
		  var k;
//		  recorrer cada uno de los servicios de la nueva OT
		  for(k = 1; k < myNodelist[1].rows.length; k++) {
//			  alert("tabla servicios fila "+k+" "+vm.cliente.ordenes_de_trabajo[0].servicios[k-1].detalleServicio);
			  vm.cliente.ordenes_de_trabajo[0].servicios[k-1].tipoServicio.idServicio = myNodelist[1].rows[k].cells[0].innerHTML;
			  vm.cliente.ordenes_de_trabajo[0].servicios[k-1].tipoServicio.nombre = myNodelist[1].rows[k].cells[1].innerHTML;
			  vm.cliente.ordenes_de_trabajo[0].servicios[k-1].detalleServicio = myNodelist[1].rows[k].cells[2].innerHTML;
			  vm.cliente.ordenes_de_trabajo[0].servicios[k-1].monto = myNodelist[1].rows[k].cells[3].innerHTML;
			  total = total + Number(myNodelist[1].rows[k].cells[3].innerHTML);
		  }
		  vm.cliente.ordenes_de_trabajo[0].total = total;
//		}  fin for i
	}
	
	llenarDatosCliente = function() {
		var idxTable;
		var idxOT = 0;
		var total = 0;
		var idxSelect = 0;
		var myNodelist = document.querySelectorAll("table");
		alert(" nro de filas de la grilla principal. Cada grilla de servicios corresponde a una de estas filas. = "+myNodelist[0].rows.length);
//		ciclo para recorrer la grilla con las OT´s.
//		la grilla de servicios de la OT es considerada, por el DOM,  como una fila de la grilla de las OT´s.
//		Por eso si el cliente tiene 2 OT´s el DOM considera que esa grilla de las OT´s tiene 5 filas: la de los header, la fila de cada OT y la fila con la grilla correspondiente a los servicios de esa OT.
		for (var i = 1; i < myNodelist[0].rows.length; i++) {
//			if (i == 1 || i == 3) {
			alert("fila: "+ i);
			if (esImpar(i)) {
//				llenar los datos de la OT
//				despliega la obsevación de cada OT del cliente
				alert("OT: "+i+"  observación: "+myNodelist[0].rows[i].cells[3].innerHTML);
				vm.cliente.ordenes_de_trabajo[idxOT].total = myNodelist[0].rows[i].cells[1].innerHTML;
				vm.cliente.ordenes_de_trabajo[idxOT].fecha = myNodelist[0].rows[i].cells[2].innerHTML;
				vm.cliente.ordenes_de_trabajo[idxOT].observacion = myNodelist[0].rows[i].cells[3].innerHTML;
			} else {
//				recorrer la grilla de servicios de la OT
//				idxTable corresponde al índice de la tabla de servicio de la OT correspondiente
				idxTable = i/2;
//				console.log(myNodelist[0].rows[i]);
//				alert(idxTable);
//				despliega el monto del primer servicio de cada OT del cliente
//				alert("OT: "+i+"  monto: "+myNodelist[idxTable].rows[1].cells[3].innerHTML);
				total = 0;
				//recorrer cada uno de los servicios de la nueva OT
				for(var k = 1; k < myNodelist[idxTable].rows.length; k++) {
//					  alert("tabla servicios fila "+k+" "+vm.cliente.ordenes_de_trabajo[0].servicios[k-1].detalleServicio);
//					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio = myNodelist[idxTable].rows[k].cells[0].innerHTML;
//					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.nombre = myNodelist[idxTable].rows[k].cells[1].innerHTML;
					  alert(document.getElementsByTagName("select")[idxSelect].value);
					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio = document.getElementsByTagName("select")[idxSelect].value;					
					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].detalleServicio = myNodelist[idxTable].rows[k].cells[1].innerHTML;
					  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].monto = myNodelist[idxTable].rows[k].cells[2].innerHTML;
					  total = total + Number(myNodelist[idxTable].rows[k].cells[2].innerHTML);
					  idxSelect = idxSelect + 1;
				}
				 vm.cliente.ordenes_de_trabajo[idxOT].total = total;
				 idxOT = idxOT + 1;
			}
			
		}
	}
	
}