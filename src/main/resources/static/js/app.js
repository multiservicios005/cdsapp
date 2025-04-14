var app = angular.module('app', ['ngTouch', 'ui.grid', 'ui.grid.cellNav', 'ui.grid.edit', 'ui.grid.resizeColumns', 'ui.grid.pinning', 'ui.grid.selection', 'ui.grid.moveColumns', 'ui.grid.exporter', 'ui.grid.importer', 'ui.grid.grouping']);
 
app.controller('MainCtrl',
 function ($scope, $http, $timeout, $interval, uiGridConstants, uiGridGroupingConstants) {
   var vm = this,
     gridApi;
   
   vm.mostrardetalle = false;
   vm.mostrarmodify = false;
   vm.deshabilitar = false;	
   vm.idCliente;
   vm.cliente = {};
   vm.cliente.comuna = "Viña del Mar";
   
  $scope.gridOptions = {
    enableRowSelection: true
  };
	
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
//		    ],
//		    selectedOption: {idServicio: '2', nombre: 'Lavado de Alfombras Sueltas'} //This sets the default value of the select in the ui
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
   
   $scope.someProp = 'abc',
   $scope.showMe = function(){
       alert($scope.someProp);
    };
 
//  la línea enableRowSelection: true, fue agregada por mí; pero no está funcionando
  vm.gridOptions = {
    data: 'myData',
    enableRowSelection: true,
    enableCellEditOnFocus: false,
    enableColumnResizing: true,
    enableFiltering: true,
    enableGridMenu: true,
    showGridFooter: false,
    showColumnFooter: true,
    fastWatch: true,
    rowIdentity: getRowId,
    getRowIdentity: getRowId,
    importerDataAddCallback: function importerDataAddCallback( grid, newObjects ) {
      $scope.myData = $scope.data.concat( newObjects );
    },
    columnDefs: [
      { name:'idCliente', width:50 , type: 'number', enableCellEdit: false },
      { name:'nombre', width:100 },
      { name:'telefono', width:100, enableCellEdit: true },
      { name:'direccion', width:150, enableCellEdit: true },
      { name:'comuna', width:150, enableCellEdit: true },
      { name:'fecha', width:150 },
      { name:'observacion', width:300 }
    ],
    onRegisterApi: function onRegisterApi(registeredApi) {
      gridApi = registeredApi;
    }
  };
  
//  el código siguiente se eliminó del código anterior en columnDefs pq no ha sido posible detecta en que fila se está clickeando el botón Ver Detalle del Cliente
//  queda pendiente su uso hasta aclarar como detectar el cliente que se desea eliminar.
//  esta idea funciona en el botón Agregar Servicio a la OT de la modificación pero son implementaciones diferentes 
//  { name: 'Ver Detalle del Cliente',
//      cellTemplate:'<button class="btn primary" ng-click="grid.appScope.verDetalleTest($index)">Ver Detalle del Cliente</button>',
//      enableFiltering: true}
  
//  agregada para que solo se pueda seleccionar una fila
  vm.gridOptions.multiSelect = false;
  
//  $scope.gridOptions = { enableRowSelection: true, enableRowHeaderSelection: false };
  
//  columnDefs: [
//      { name:'id', width:50 },
//      { name:'name', width:100 },
//      { name:'age', width:100, enableCellEdit: true, aggregationType: uiGridConstants.aggregationTypes.avg, treeAggregationType: uiGridGroupingConstants.aggregation.AVG },
//      { name:'address.street', width:150, enableCellEdit: true },
//      { name:'address.city', width:150, enableCellEdit: true },
//      { name:'address.state', width:50, enableCellEdit: true },
//      { name:'address.zip', width:50, enableCellEdit: true },
//      { name:'company', width:100, enableCellEdit: true },
//      { name:'email', width:100, enableCellEdit: true },
//      { name:'phone', width:200, enableCellEdit: true },
//      { name:'about', width:300, enableCellEdit: true },
//      { name:'friends[0].name', displayName:'1st friend', width:150, enableCellEdit: true },
//      { name:'friends[1].name', displayName:'2nd friend', width:150, enableCellEdit: true },
//      { name:'friends[2].name', displayName:'3rd friend', width:150, enableCellEdit: true },
//      { name:'agetemplate',field:'age', width:150, cellTemplate: '<div class="ui-grid-cell-contents"><span>Age 2:{{COL_FIELD}}</span></div>' },
//      { name:'Is Active',field:'isActive', width:150, type:'boolean' },
//      { name:'Join Date',field:'registered', cellFilter:'date', width:150, type:'date', enableFiltering: false },
//      { name:'Month Joined',field:'registered', cellFilter: 'date:"MMMM"', filterCellFiltered: true, sortCellFiltered: true, width:150, type:'date' }
//    ],
 
  function getRowId(row) {
    return row.id;
  }
  
  vm.test = function() {
//	  	alert("test "  );
//	  alert("hola"+ $scope.myData[5].idCliente);
//	  alert("x "+vm.gridOptions.data[0]);
//	  alert("gridApi:  "+gridApi.cellNav);
	  var rowCol = gridApi.cellNav.getFocusedCell();
//	    alert('Row Id:' + rowCol );	  
//	    alert('Row Id:' + rowCol.row.entity.id + ' col:' + rowCol.col.colDef.name );
	    alert('Id Cliente:' + rowCol.row.entity.idCliente + ' col:' + rowCol.row.entity.nombre );
//	    alert('Id nombre:' + $scope.myData[rowCol.row.entity.id].nombre );
	    
//	    borraría fila la cero. Probar
//	    vm.gridOptions.data.splice(0,1);
  };
  
  $scope.verDetalleTest = function(fila) {
	  alert("verDetalleTest "+ fila);
//	  investigar como obtener la fila en que se clickeo verDetalle
	  var rowCol = gridApi.cellNav.getFocusedCell();
	  if(rowCol == null) {
		  alert("Debe seleccionar un cliente");
	  } else {
//		  alert("ir a verDetalle");
		  vm.verDetalle();
	  }
  }
  
  vm.verDetalle = function() {
//	  	alert("ver detalle antes "+ vm.mostrardetalle  );
//	  alert("ver detalle"+gridApi.cellNav)
	   	vm.mostrarmodify = false;
	  	var rowCol = gridApi.cellNav.getFocusedCell();
		if(rowCol == null) {
			 alert("Debe seleccionar un cliente");
		} else {
		  	vm.idCliente = rowCol.row.entity.idCliente;
		  	vm.cliente = $scope.myData[rowCol.row.entity.id];
//		  	alert(vm.cliente+" - length"+vm.cliente.ordenes_de_trabajo.length);
		  	vm.mostrardetalle = true;
//		  	alert("ver detalle después "+ vm.mostrardetalle  );		
		}
	  };
	  
	  vm.verModifyClient = function() {
//		  	alert("ver modify client antes "+ vm.mostrarmodify  );
//		  alert("ver modify client"+gridApi.cellNav)
		  	vm.mostrardetalle = false;
		  	var rowCol = gridApi.cellNav.getFocusedCell();
			if(rowCol == null) {
				 alert("Debe seleccionar un cliente");
			} else {
			  	vm.idCliente = rowCol.row.entity.idCliente;
			  	vm.cliente = $scope.myData[rowCol.row.entity.id];
//			  	alert(vm.cliente+" - length"+vm.cliente.ordenes_de_trabajo.length);
//			  	vm.cliente.ordenes_de_trabajo[0].fecha = getDate(vm.cliente.ordenes_de_trabajo[0].fecha);
//			  	alert("ot.fecha:   "+vm.cliente.ordenes_de_trabajo[0].fecha);
			  	vm.mostrarmodify = true;
//			  	alert("ver modify client después "+ vm.mostrarmodify  );		
			}
		  };
  
  vm.toggleFilterRow = function() {
    vm.gridOptions.enableFiltering = !vm.gridOptions.enableFiltering;
    gridApi.core.notifyDataChange(uiGridConstants.dataChange.COLUMN);
  };
 
  vm.callsPending = 0;
 
  var i = 0;
  vm.refreshData = function(){
//	alert("refreshData");
	i = 0;
	vm.mostrardetalle = false;
	vm.mostrarmodify = false;
    $scope.myData = [];
 
    var start = new Date();

    var sec = $interval(function () {
      vm.callsPending++;
 
//      $http.get('/data/500_complex.json')
      $http.get('/clientes')
        .then(function(response) {
          var data = response.data;
 
          vm.callsPending--;
 
          data.forEach(function(row){
//        	console.log(row);
//        	row.ordenes_de_trabajo.forEach(ot => console.log(getDate(ot.fecha)));
//        	La fecha retornada por el microservicio viene como String a pesar que en la base de datos es de tipo Date
//        	La línea siguiente la convierte a  tipo Date porque el objeto html5 de tipo date solo acepta objetos de tipo Date
        	row.ordenes_de_trabajo.forEach(ot => ot.fecha = getDate(ot.fecha));
            row.id = i;
            i++;
            row.registered = new Date(row.registered)
            $scope.myData.push(row);
          });
        })
        .catch(function() {
          vm.callsPending--;
        });
    }, 200, 10);
 
 
    var timeout = $timeout(function() {
       $interval.cancel(sec);
       vm.left = '';
    }, 200);
//  }, 2000);
    
    $scope.$on('$destroy', function(){
      $timeout.cancel(timeout);
      $interval.cancel(sec);
    });
  };
  
  vm.newClient = function() {
	  alert("Crear cliente");
  };
  
  vm.deleteClient = function() {
	  	var respuesta;
//	  	alert("Borrar cliente");
	  	var rowCol = gridApi.cellNav.getFocusedCell();
		if(rowCol == null) {
			 alert("Debe seleccionar un cliente");
		} else {
			respuesta = confirm("¿ Está seguro que quiere borrar este cliente del sistema ?");
			if(respuesta == true) {
			  	vm.idCliente = rowCol.row.entity.idCliente;
	//		  	vm.cliente = $scope.myData[rowCol.row.entity.id];
			  	url = "/clientes/";
				$http.delete(url+vm.idCliente)
				.then(function(res){
					console.log(res);
					alert("El cliente ha sido borrado exitosamente.");
					//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
					vm.refreshData();
					});	
			}				
		}

  };
  
  vm.modifyClient = function() {
//	  alert("Modificar cliente");
//	  alert("Debe seleccionar un cliente");
	if(vm.cliente.ordenes_de_trabajo.length > 0) {
//		llenarDatosCliente();
		console.log("vm.cliente:   "+vm.cliente);
	}
	
//	vm.cliente.ordenes_de_trabajo.forEach(ot => ot.fecha = getDateAsString(ot.fecha));
//	vm.cliente.ordenes_de_trabajo.forEach(ot => console.log(getDateAsString(ot.fecha)));
	//$http.put("/clientes", vm.fdatos)
	$http.put("/clientes", vm.cliente)
		.then(function(res){
			console.log(res);
			alert("El cliente ha sido modificado exitosamente.");
			vm.refreshData();
			//por supuesto podrás volcar la respuesta al modelo con algo como vm.res = res;
			});
  };
  
  llenarDatosClienteOld1 = function() {
		var idxTable;
		var idxOT = 0;
		var total = 0;
		var idxSelect = 0;
		var myNodelist = document.querySelectorAll("table");
		alert("myNodelist.length "+myNodelist.length);
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
  
  llenarDatosCliente = function() {
		var idxTable;
		var idxOT = 0;
		var total = 0;
		var idxSelect = 0;
		var tipoServicios;
//		var idxServicioSelected;
		var nameServicioSelected;
//		var myNodelist = document.querySelectorAll("table");
		var myNodelist = document.querySelectorAll("#modifyClient");
//		alert(" nro de filas de la grilla principal. Cada grilla de servicios corresponde a una de estas filas. = "+myNodelist[0].rows.length);
//		alert("header Total: "+myNodelist[0].rows[0].cells[0].innerHTML);
//		ciclo para recorrer la grilla con las OT´s.
		for (var i = 1; i < myNodelist[0].rows.length; i++) {
//			alert("fila: "+ i);
			
//			llenar los datos de la OT
//			despliega la obsevación de cada OT del cliente
//			alert("OT: "+i+"  observación: "+myNodelist[0].rows[i].cells[3].innerHTML);
			vm.cliente.ordenes_de_trabajo[idxOT].total = myNodelist[0].rows[i].cells[1].innerHTML;
//			vm.cliente.ordenes_de_trabajo[idxOT].fecha = myNodelist[0].rows[i].cells[2].innerHTML;
//			vm.cliente.ordenes_de_trabajo[idxOT].observacion = myNodelist[0].rows[i].cells[3].innerHTML;
			
			var cellServicios = myNodelist[0].rows[i].cells[4];
//			alert("celda 4 grilla OT´s: "+cellServicios);
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
				  tipoServicios = document.getElementsByTagName("select")[idxSelect];
//				  alert(tipoServicios.value);
//				  alert(document.getElementsByTagName("select")[idxSelect].value);
//				  idxServicioSelected = document.getElementsByTagName("select")[idxSelect].selectedIndex;
//				  idxServicioSelected = tipoServicios.selectedIndex;
//				  alert("x "+document.getElementsByTagName("select")[idxSelect].selectedIndex);
//				  alert(idxServicioSelected);
//				  alert("text "+document.getElementsByTagName("select")[idxSelect].options[idxServicioSelected].text);
//				  nameServicioSelected = document.getElementsByTagName("select")[idxSelect].options[idxServicioSelected].text;
//				  nameServicioSelected = tipoServicios.options[idxServicioSelected].text;
//				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio = document.getElementsByTagName("select")[idxSelect].value;
//				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.idServicio = tipoServicios.value;
//				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].tipoServicio.nombre = nameServicioSelected;
				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].detalleServicio = servicios[idxTable].rows[k].cells[1].innerHTML;
				  vm.cliente.ordenes_de_trabajo[idxOT].servicios[k-1].monto = servicios[idxTable].rows[k].cells[2].innerHTML;
				  total = total + Number(servicios[idxTable].rows[k].cells[2].innerHTML);
				  idxSelect = idxSelect + 1;
			}
			 vm.cliente.ordenes_de_trabajo[idxOT].total = total;
			 idxOT = idxOT + 1;
		}
	}
  
//	código reusado desde getcliente.js que a su vez viene desde el insert.js
	vm.addOT = function(){
//		alert("Agregando una OT");
//		vm.cliente.ordenes_de_trabajo.push({"total":125000, "fecha":"2019-11-10", "observacion":"algo interesante", "servicios":[{"tipoServicio":{"idServicio": 2, "nombre": "Lavado de Alfombras Sueltas"}, "detalleServicio":"Living de 2x4", "monto":55000}]});
		vm.cliente.ordenes_de_trabajo.push({total:0, fecha:new Date(), observacion:"algo interesante", servicios:[{tipoServicio:{idServicio: 2, nombre: "Lavado de Alfombras Sueltas"}, detalleServicio:"Living de 2x4", monto:0}]});
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo[0].servicios);
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo.length);
		vm.deshabilitar = true;	
	}
	
	vm.addService = function(ot){
//		alert("Agregando una Servicio a la OT");
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo.servicios);
//		alert("Servicios de la OT"+vm.cliente.ordenes_de_trabajo[0].servicios);
//		vm.cliente.ordenes_de_trabajo[0].servicios.push({"tipoServicio":{"idServicio": 2, "nombre": "Lavado de Alfombras Sueltas"}, "detalleServicio":"Living de 5x3", "monto":78000});
		vm.cliente.ordenes_de_trabajo[ot].servicios.push({tipoServicio:{idServicio: 2, nombre: "Lavado de Alfombras Sueltas"}, detalleServicio:"Living de 5x3", monto:0});
	}
	
	vm.updteTotalOT = function(idxOT){
		var totalOT = 0;
//		alert("idxOT:  "+idxOT);
//		alert("total:  "+vm.cliente.ordenes_de_trabajo[0].total);
		vm.cliente.ordenes_de_trabajo[idxOT].servicios.forEach(s => totalOT = totalOT + Number(s.monto));
		vm.cliente.ordenes_de_trabajo[idxOT].total = Number(totalOT);
	}
  
  esImpar = function( x ) {
	  return ( x & 1 );
  }
  
//  Convierte una String de fecha a Un objeto Date de javascript.
//  Ejemplo:
//	  fecha = "2017-10-02"  ===>  new Date(2017, 9, 2)
  getDate = function( strFecha ) {
//	  alert("fecha: "+strFecha.substr(0,4)+"-"+ strFecha.substr(5,2)-1+"-"+ strFecha.substr(8,2));
//	  console.log("fecha antes:  "+strFecha );
//	  console.log("fecha después:  "+new Date(strFecha.substr(0,4), strFecha.substr(5,2)-1, strFecha.substr(8,2)));
	  return new Date(strFecha.substr(0,4), strFecha.substr(5,2)-1, strFecha.substr(8,2));
  }
  
//  Implementado para mostrar la fecha en formato dd-MM-yyyy al mostrar detalle del cliente
  vm.getDateAsString = function( date ) {
	  var mes = date.getMonth() + 1;
//	  console.log("fecha antes:  "+date );
//	  console.log("fecha después:  "+date.getDate() + "-" + mes + "-" + date.getFullYear());
	  return date.getDate() + "-" + mes + "-" + date.getFullYear();
  }
  
});