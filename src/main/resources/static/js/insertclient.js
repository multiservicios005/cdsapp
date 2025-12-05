//angularinsert.js
//alert("controller angular para el insert");
angular
	.module('app', [])
	.controller('appCtrl', ['$scope', '$http', controladorPrincipal]);

function controladorPrincipal($scope, $http){
	var vm=this;
	//inicializo un objeto en los datos de formulario
//	vm.fdatos = {};
	vm.cliente = {};
//	objeto cli usado en la versión vm.enviarOld
//	var cli = new Object();
//	objeto cli usado en la versión vm.enviarOld
//	vm.cliente = cli;
	vm.deshabilitar = false;
	vm.cliente.ordenes_de_trabajo = [];
	vm.cliente.comuna = "Viña del Mar";
	vm.cliente.fecha = new Date(); 
	
	   $scope.data = {
			    availableOptions: [
			      {idServicio: 1, nombre: 'Lavado de Alfombras Muro a Muro'},
			      {idServicio: 2, nombre: 'Lavado de Alfombras Sueltas'},
			      {idServicio: 3, nombre: 'Limpieza de Piso Flotante'},
			      {idServicio: 4, nombre: 'Limpieza de Tapices de Muebles'},
			      {idServicio: 5, nombre: 'Limpieza de Tapices de Automóviles'},
			      {idServicio: 6, nombre: 'Lavandería Con Retiro a Domicilio'},
			      {idServicio: 7, nombre: 'Limpieza de Colchones'}
			    ]
//			    ],
//			    selectedOption: {idServicio: '2', nombre: 'Lavado de Alfombras Sueltas'} //This sets the default value of the select in the ui
			    };
	   
	   $scope.comunas = {
			    availableOptions: [
			      {idComuna: '1', nombre: 'Con Con'},
			      {idComuna: '2', nombre: 'Maitencillo'},
			      {idComuna: '3', nombre: 'Puente Pelunquén'},
			      {idComuna: '4', nombre: 'Quilpué'},
			      {idComuna: '5', nombre: 'Valparaíso'},
			      {idComuna: '6', nombre: 'Villa Alemana'},
			      {idComuna: '7', nombre: 'Viña del Mar'}
			    ],
			    selectedOption: {idComuna: '7', nombre: 'Viña del Mar'} //This sets the default value of the select in the ui
			    };
	
	vm.test = function(){
//		alert("vm.fdatos: "+vm.fdatos.nombre);
//		alert("vm.cli: "+cli.nombre);
//		alert("ordenes_de_trabajo.length: "+vm.cliente.ordenes_de_trabajo.length);
//		alert("ordenes_de_trabajo[0].observacion: "+vm.cliente.ordenes_de_trabajo[0].observacion);
//		vm.cliente.ordenes_de_trabajo[0].servicios.push({"tipoServicio":{"idServicio": 2, "nombre": "Lavado de Alfombras Sueltas"}, "detalleServicio":"Living de 5x5", "monto":46000});
		var ots = document.getElementById("myTable").rows;
//		for(o in ots) {
//			alert(o.cells[0]);
//		}
		for (fila = 1; fila < ots.length; fila++) {
			  // alert("celda "+i+" . "+document.getElementById("myTable").rows[0].cells.item(i).innerHTML);
			  // alert("var cols = "+ots[fila].cells.length);
			  var cols = ots[fila].cells.length;
			  for (col = 0; col < cols; col++) {
				  if(col == 3) {
//					  alert("col ="+col+":   "+document.getElementById("myTable").rows[fila].cells[col].innerHTML);
//					  getServices();
				  } else {
//					  alert("col ="+col+":   "+document.getElementById("myTable").rows[fila].cells[col].innerHTML);
				  }
			  }
		}
//		verNodos();
//		llenarDatosCliente();
//		displayOT();
//		alert("servicio: "+$scope.data.selectedOption.nombre);
	}
	
//	muestra los datos de la OT en vm.cliente.ordenes_de_trabajo[0]
	displayOT = function(){
		alert("Muestra los datos de la nueva OT");
		alert("OT Cliente total: "+vm.cliente.ordenes_de_trabajo[0].total + "  fecha: " + vm.cliente.ordenes_de_trabajo[0].fecha + "  observación: " + vm.cliente.ordenes_de_trabajo[0].observacion);
		var i;
		for (i = 0; i < vm.cliente.ordenes_de_trabajo[0].servicios.length; i++) {
			alert("servicio: "+i+" tipoServicio.nombre:  "+vm.cliente.ordenes_de_trabajo[0].servicios[i].tipoServicio.nombre);
			alert("servicio: "+i+" detalleServicio:  "+vm.cliente.ordenes_de_trabajo[0].servicios[i].detalleServicio);
			alert("servicio: "+i+" monto:  "+vm.cliente.ordenes_de_trabajo[0].servicios[i].monto);
		}
	}
	
	verNodos = function(){
//		alert("Leyendo Servicios de una OT");
//		alert(document.getElementById("tableSer").rows.length);
		var myNodelist = document.querySelectorAll("table");
//		alert("myNodelist.length = "+myNodelist.length);
		var i;
//		recorrer cada una de las tablas de la página
		for (i = 0; i < myNodelist.length; i++) {
//		  myNodelist[i].style.backgroundColor = "red";
//		  alert("node i = "+i+"  "+myNodelist[i].innerHTML);
		  alert("TABLA  "+i+"  length = "+myNodelist[i].rows.length);
//		  alert("col ="+col+":   "+document.getElementById("myTable").rows[fila].cells[col].innerHTML);		  
		  for(k = 1; k < myNodelist[i].rows.length; k++) {
			  alert("tabla "+i+"  fila "+k+" "+myNodelist[i].rows[k].cells[2].innerHTML);
		  }
		}
	}
	
	vm.addOT = function(){
//		alert("Agregando una OT");
//		vm.cliente.ordenes_de_trabajo.push({"total":125000, "fecha":"2019-11-10", "observacion":"algo interesante", "servicios":[{"tipoServicio":{"idServicio": 2, "nombre": "Lavado de Alfombras Sueltas"}, "detalleServicio":"Living de 2x4", "monto":55000}]});
//		vm.cliente.ordenes_de_trabajo.push({total:25000, fecha:"2019-11-10", observacion:"cualquier cosa", servicios:[{tipoServicio:{idServicio: 2, nombre: "Lavado de Alfombras Sueltas"}, detalleServicio:"Living de 2x4", monto:25000}]});
		vm.cliente.ordenes_de_trabajo.push({total:0, fecha:new Date(), observacion:"cualquier cosa", servicios:[{tipoServicio:{idServicio: 2, nombre: "Lavado de Alfombras Sueltas"}, detalleServicio:"Living de 2x4", monto:0}]});
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo[0].servicios);
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo.length);
//		deshabilitar el botón AddOT
		vm.deshabilitar = true;			
	}
	
	vm.updteTotalOT = function(){
		var totalOT = 0;
//		alert("Actualizando el total de la OT"+monto);
//		alert("total:  "+vm.cliente.ordenes_de_trabajo[0].total);
		vm.cliente.ordenes_de_trabajo[0].servicios.forEach(s => totalOT = totalOT + Number(s.monto));
		vm.cliente.ordenes_de_trabajo[0].total = Number(totalOT);
	}
	
	getServices = function(){
		alert("Leyendo Servicios de una OT");
		alert(document.getElementById("tableSer").rows.length);
	}
	
	vm.test2 = function(){
//		alert("Pérdida del focus de una celda");
//		llenarDatosCliente();
//		var t1 = document.querySelectorAll("table");
//		var t2 = document.querySelectorAll('#tableSer');
		alert("tableSer  "+document.getElementById("tableSer").rows.length);
		alert("tableSer childs  "+document.getElementById("tableSer").childNodes[5]);
		console.log(document.getElementById("tableSer").childNodes[5]);
	}
	
	vm.test4 = function(){
		alert("cliente: "+vm.cliente.nombre);
		if(vm.cliente.nombre == null && vm.cliente.telefono == null) {
			alert("debe ingresar al menos el nombre del cliente o el número de teléfono !!!");
			return;
		} else if((vm.cliente.nombre == null && vm.cliente.telefono.length == 0) || 
				(vm.cliente.telefono == null && vm.cliente.nombre.length == 0) ||
				(vm.cliente.nombre.length == 0 && vm.cliente.telefono.length == 0)) {
					alert("debe ingresar al menos el nombre del cliente o el número de teléfono !!!");
					return;
		} else {
			alert("grabando !!!");
		}
		alert("pasé al final de la función");
	}
	
	vm.addService = function(){
//		alert("Agregando una Servicio a la OT");
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo.servicios);
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo[0].servicios);
//		vm.cliente.ordenes_de_trabajo[0].servicios.push({"tipoServicio":{"idServicio": 2, "nombre": "Lavado de Alfombras Sueltas"}, "detalleServicio":"Living de 5x3", "monto":78000});
		vm.cliente.ordenes_de_trabajo[0].servicios.push({tipoServicio:{idServicio: 2, nombre: "Lavado de Alfombras Sueltas"}, detalleServicio:"Living de 5x3", monto:0});
	}
	
//	método cuasi repetido en getclient.js
	llenarDatosClienteOld = function(){
//		alert("Llenar los datos del cliente");
//		var ot = new Object();
		var myNodelist = document.querySelectorAll("table");
//		llenar la OT
		vm.cliente.ordenes_de_trabajo[0].total = myNodelist[0].rows[1].cells[0].innerHTML;
		vm.cliente.ordenes_de_trabajo[0].fecha = myNodelist[0].rows[1].cells[1].innerHTML;
		vm.cliente.ordenes_de_trabajo[0].observacion = myNodelist[0].rows[1].cells[2].innerHTML;
		
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
//			  vm.cliente.ordenes_de_trabajo[0].servicios[k-1].tipoServicio.nombre = myNodelist[1].rows[k].cells[0].innerHTML;
//			  vm.cliente.ordenes_de_trabajo[0].servicios[k-1].tipoServicio = $scope.data.selectedOption;
			  vm.cliente.ordenes_de_trabajo[0].servicios[k-1].detalleServicio = myNodelist[1].rows[k].cells[1].innerHTML;
			  vm.cliente.ordenes_de_trabajo[0].servicios[k-1].monto = myNodelist[1].rows[k].cells[2].innerHTML;
			  total = total + Number(myNodelist[1].rows[k].cells[2].innerHTML);
//			  alert("tipo servicio id: "+vm.cliente.ordenes_de_trabajo[0].servicios[k-1].tipoServicio.idServicio+" - "+vm.cliente.ordenes_de_trabajo[0].servicios[k-1].tipoServicio.nombre)
		  }
		  vm.cliente.ordenes_de_trabajo[0].total = total;
//		}  fin for i
	}
	
	esImpar = function( x ) {
		  return ( x & 1 );
	}
	
	llenarDatosCliente = function() {
		var idxTable;
		var idxOT = 0;
		var total = 0;
		var idxSelect = 0;
		var myNodelist = document.querySelectorAll("table");
//		alert(" nro de filas de la grilla principal. Cada grilla de servicios corresponde a una de estas filas. = "+myNodelist[0].rows.length);
//		alert("header Total: "+myNodelist[0].rows[0].cells[0].innerHTML);
//		ciclo para recorrer la grilla con las OT´s.
		for (var i = 1; i < myNodelist[0].rows.length; i++) {
//			alert("fila: "+ i);
			
//			llenar los datos de la OT
//			despliega la obsevación de cada OT del cliente
//			alert("OT: "+i+"  observación: "+myNodelist[0].rows[i].cells[2].innerHTML);
			vm.cliente.ordenes_de_trabajo[idxOT].total = myNodelist[0].rows[i].cells[0].innerHTML;
//			vm.cliente.ordenes_de_trabajo[idxOT].fecha = myNodelist[0].rows[i].cells[1].innerHTML;
//	*		vm.cliente.ordenes_de_trabajo[idxOT].observacion = myNodelist[0].rows[i].cells[2].innerHTML;
//			alert("vm.cliente.ordenes_de_trabajo[idxOT].observacion: "+vm.cliente.ordenes_de_trabajo[idxOT].observacion);
			
			var cellServicios = myNodelist[0].rows[i].cells[3];
//			alert("celda 3 grilla OT´s: "+cellServicios);
			var servicios = cellServicios.querySelectorAll("table");
//			alert("servicios: "+servicios[0].rows[1].cells[1].innerHTML);
//			alert("servicios 2: "+servicios[0].rows[2].cells[1].innerHTML);
			
//			recorrer la grilla de servicios de la OT
//			idxTable corresponde al índice de la tabla de servicio de la OT correspondiente
			idxTable = 0;
			total = 0;
			//recorrer cada uno de los servicios de la nueva OT
//			for(var k = 1; k < myNodelist[idxTable].rows.length; k++) {
			for(var k = 1; k < servicios[idxTable].rows.length; k++) {
//				  alert(document.getElementsByTagName("select")[idxSelect].value);
//				  alert("tipo servicio: "+document.getElementById("mySelect2")[idxSelect].value);
//				  alert("tipo servicio: "+$scope.data.selectedOption.idServicio);
//				  alert("tipo servicio: "+vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio+" - "+vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.nombre);
//				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio = document.getElementsByTagName("select")[idxSelect].value;					
//				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio = document.getElementById("mySelect2")[idxSelect].value;
//				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio = $scope.data.selectedOption.idServicio;
				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].detalleServicio = servicios[idxTable].rows[k].cells[1].innerHTML;
				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].monto = servicios[idxTable].rows[k].cells[2].innerHTML;
				  total = total + Number(servicios[idxTable].rows[k].cells[2].innerHTML);
				  idxSelect = idxSelect + 1;
			}
			 vm.cliente.ordenes_de_trabajo[idxOT].total = total;
			 idxOT = idxOT + 1;
		}
	}
	
	llenarDatosClienteOld2 = function() {
		var idxTable;
		var idxOT = 0;
		var total = 0;
		var idxSelect = 0;
		var myNodelist = document.querySelectorAll("table");
		alert(" nro de filas de la grilla principal. Cada grilla de servicios corresponde a una de estas filas. = "+myNodelist[0].rows.length);
		alert("header Total: "+myNodelist[0].rows[0].cells[0].innerHTML);
//		ciclo para recorrer la grilla con las OT´s.
//		la grilla de servicios de la OT es considerada, por el DOM,  como una fila de la grilla de las OT´s.
//		Por eso si el cliente tiene 2 OT´s el DOM considera que esa grilla de las OT´s tiene 5 filas: la de los header, la fila de cada OT y la fila con la grilla correspondiente a los servicios de esa OT.
		for (var i = 1; i <= myNodelist[0].rows.length; i++) {
//			if (i == 1 || i == 3) {
			alert("fila: "+ i);
			alert("observación: "+myNodelist[0].rows[i].cells[2].innerHTML);
			alert("select: "+document.getElementsByTagName("select")[idxSelect].value);
			idxSelect = idxSelect + 1;
			alert("celda 2 grilla OT´s: "+myNodelist[0].rows[i].cells[2]);
			var ser = myNodelist[0].rows[i].cells[3];
			alert("celda 3 grilla OT´s: "+ser);
			var tb = ser.querySelectorAll("table");
			alert("servicios: "+tb[0].rows[1].cells[1].innerHTML);
			alert("servicios 2: "+tb[0].rows[2].cells[1].innerHTML);
//			myNodelist[1].rows[i].cells[3].innerHTML
			if (esImpar(i)) {
//				llenar los datos de la OT
//				despliega la obsevación de cada OT del cliente
				alert("OT: "+i+"  observación: "+myNodelist[0].rows[i].cells[2].innerHTML);
				vm.cliente.ordenes_de_trabajo[idxOT].total = myNodelist[0].rows[i].cells[0].innerHTML;
				vm.cliente.ordenes_de_trabajo[idxOT].fecha = myNodelist[0].rows[i].cells[1].innerHTML;
				vm.cliente.ordenes_de_trabajo[idxOT].observacion = myNodelist[0].rows[i].cells[2].innerHTML;
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
//					  idxSelect = idxSelect + 1;
				}
				 vm.cliente.ordenes_de_trabajo[idxOT].total = total;
				 idxOT = idxOT + 1;
			}
			
		}
	}
	
	// graba en la base de datos los datos de un nuevo cliente
	vm.insertclient = function(){

//		validar que al menos el nombre o el teléfono del cliente sea ingresado.
		if(vm.cliente.nombre == null && vm.cliente.telefono == null) {
			alert("debe ingresar al menos el nombre del cliente o el número de teléfono !!!");
			return;
		} else if((vm.cliente.nombre == null && vm.cliente.telefono.length == 0) || 
				(vm.cliente.telefono == null && vm.cliente.nombre.length == 0) ||
				(vm.cliente.nombre.length == 0 && vm.cliente.telefono.length == 0)) {
					alert("debe ingresar al menos el nombre del cliente o el número de teléfono !!!");
					return;
		}
		
//		alert("servicio: "+$scope.comunas.selectedOption.nombre);
		vm.cliente.comuna = $scope.comunas.selectedOption.nombre;
		
		if(vm.cliente.ordenes_de_trabajo.length > 0) {
//			vm.cliente.ordenes_de_trabajo.forEach(ot => ot.fecha = getDateAsString());
//			llenarDatosCliente();
		}
		
//		var obj = { name: "John", age: 30, city: "New York" };
//		var myJSON = JSON.stringify(cli);
//		document.getElementById("demo").innerHTML = myJSON;
//		console.log(myJSON);
//		alert("cliente json: "+myJSON);
//		$http.post("/clientes", myJSON)		
//		$http.post("/clientes", vm.fdatos)
		console.log(vm.cliente);
		$http.post("/clientes", vm.cliente)
			.then(function(res){
				console.log("idCliente"+res.data.idCliente);
//				vm.cliente=JSON.stringify(res.data);
				vm.cliente.idCliente=res.data.idCliente;
				//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
				if(res.data.ordenes_de_trabajo.length > 0){
					console.log("idOT = "+res.data.ordenes_de_trabajo[0].idOT);
					document.getElementById("idOT").value = res.data.ordenes_de_trabajo[0].idOT;
					console.log("idOT hidden = "+document.getElementById("idOT").value);
					document.getElementById("genOT").submit();					
				}
				});
		alert("El cliente ha sido registrado exitosamente.");
		}
	
//	Esta implementación creaba el cliente a partir de un objeto javascript y funciona correctamente.
//	Se reemplazó por una versión que manipula los objetos a partir de los model de AngularJS definidos en la página. 
	vm.enviarOld = function(){
		var ot = new Object();
		ot.total = 5600;
		ot.fecha = "2014-05-19";
		ot.observacion = "cuaquier cosa";
		ot.servicios = [];
		
		var s1 = new Object();
		s1.tipoServicio = {idServicio: 1, nombre: "Lavado de Alfombras Muro a Muro"};
		s1.detalleServicio = "Living de 3x3";
		s1.monto = 25000;
		
		ot.servicios.push(s1);
		
		var s2 = new Object();
		s2.tipoServicio = {idServicio: 2, nombre: "Lavado de Alfombras Sueltas"};
		s2.detalleServicio = "alfombra 5x4";
		s2.monto = 70000;
		
		ot.servicios.push(s2);
		
//		var cli = new Object();
		cli.nombre = vm.fdatos.nombre;
		cli.telefono = vm.fdatos.telefono;
		cli.fecha = vm.fdatos.fecha;
		cli.direccion = vm.fdatos.direccion;
		cli.observacion = vm.fdatos.observacion;
		cli.ordenes_de_trabajo = [ot];
		
//		var obj = { name: "John", age: 30, city: "New York" };
		var myJSON = JSON.stringify(cli);
//		document.getElementById("demo").innerHTML = myJSON;
		console.log(myJSON);
		alert("cliente json: "+myJSON);
//		$http.post("/clientes", vm.fdatos)
		$http.post("/clientes", myJSON)
			.then(function(res){
				console.log(res);
				//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
				});
		}
	
	vm.generarOT = function(){
		alert("generarOT  ");
		$http.post("/generarPdfOT")
			.then(function(res){
				console.log("idCliente"+res.data.idCliente);
//				vm.cliente=JSON.stringify(res.data);
				vm.cliente.idCliente=res.data.idCliente;
				//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
				document.getElementById("idOT").value = res.data.ordenes_de_trabajo[0].idOT;
				console.log("idOT = "+res.data.ordenes_de_trabajo[0].idOT);
				console.log("idOT = "+document.getElementById("idOT").value);
				console.log("idOT hidden = "+document.getElementById("idOT").value);
				});
			alert("La OT se ha generado exitosamente.");
		}
		
}