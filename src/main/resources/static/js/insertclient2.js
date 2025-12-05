  const miDiv = document.getElementById('miDiv');
  const boton = document.getElementById('toggleButton');
  
  const contenedor = document.getElementById('miContenedor');
  //const addOT = document.getElementById('addOT');
  const addServiceButton = document.getElementById('addServiceButton');
  
  /**
  boton.addEventListener('click', function() {
    // Si el div está oculto, lo muestra; de lo contrario, lo oculta
    if (miDiv.style.display === 'none') {
      miDiv.style.display = 'block'; // O el tipo de display que necesites (inline, flex, etc.)
    } else {
      miDiv.style.display = 'none';
    }
  });
  **/
  
  addService();

/**  
  addOT.addEventListener('click', function() {
	const observacion = document.createElement('input');
	observacion.type = 'text';
	observacion.id = 'miTexto';
	observacion.placeholder = 'Ingrese su texto aquí';
	//const contenedor = document.getElementById('miContenedor');
	contenedor.appendChild(observacion);
	
	addService();
	
  });
**/

  addServiceButton.addEventListener('click', function() {
	
	addService();
	
  });
  
  function addService() {
	// 1. Obtener el elemento de la tabla existente
	const tabla = document.getElementById('servicios');

	// 2. Insertar una nueva fila al final de la tabla
	const nuevaFila = tabla.insertRow(-1); // El índice -1 la agrega al final

	// 3. Insertar celdas en la nueva fila
	const celda1 = nuevaFila.insertCell(-1); // Agrega la celda al final de la fila
	const tipoServicios = document.createElement('select');
	tipoServicios.name = "tipoServicios";
	llenarComboServicios(tipoServicios);
	
	celda1.appendChild(tipoServicios);

	const celda2 = nuevaFila.insertCell(-1);
	const detalle = document.createElement('input');
	detalle.type = 'text';
	detalle.name = "detalle";
	//detalle.id = 'miTexto';
	celda2.appendChild(detalle);
	
	const celda3 = nuevaFila.insertCell(-1);
	const monto = document.createElement('input');
	//monto.type = 'number';
	monto.type = 'texto';
	//monto.placeholder = 'Ingrese solo números con separador de miles.';
	//monto.pattern = "[0-9.]"
	//detalle.id = 'miTexto';
	celda3.appendChild(monto);

	// 4. Agregar contenido a las celdas
	//celda1.textContent = 'Nuevo dato A';
	//celda2.textContent = 'Nuevo dato B';
	//celda3.textContent = 'Nuevo dato C';

  }
  
  function addServiceOld() {
	const tableServicios = document.createElement('table');
	const tr = document.createElement('tr');
	const th = document.createElement('th');
	th.textContent  = 'Detalle del Servicio';
	const thVacio = document.createElement('th');
	//thVacio.textContent  = ' ' + &nbsp;&nbsp;&nbsp; + &nbsp;&nbsp;&nbsp;;
	thVacio.style.padding = '15px';
	const th2 = document.createElement('th');
	th2.textContent  = 'Monto';
	
	tr.appendChild(thVacio);
	tr.appendChild(th);
	tr.appendChild(th2);
	
	tableServicios.appendChild(tr);
	contenedor.appendChild(tableServicios);
  }
  
  function llenarComboServicios(comboServicios) {
  
	const opciones = [
	  {idServicio: 1, nombre: 'Lavado de Alfombras Muro a Muro'},
	  {idServicio: 2, nombre: 'Lavado de Alfombras Sueltas'},
	  {idServicio: 3, nombre: 'Limpieza de Piso Flotante'},
	  {idServicio: 4, nombre: 'Limpieza de Tapices de Muebles'},
	  {idServicio: 5, nombre: 'Limpieza de Tapices de Automóviles'},
	  {idServicio: 6, nombre: 'Lavandería Con Retiro a Domicilio'},
	  {idServicio: 7, nombre: 'Limpieza de Colchones'}
	];
  
	// 3. Itera sobre las opciones y crea los elementos <option>
	opciones.forEach(opcionData => {
	  var optionElement = document.createElement('option');
	  optionElement.value = opcionData.idServicio; // Establece el valor del atributo 'value'
	  optionElement.textContent = opcionData.nombre; // Establece el texto visible
	  //console.log("optionElement.selected: "+optionElement.selected)
	  if(opcionData.idServicio == 2){
		optionElement.selected = true
	  }
	  
	  comboServicios.appendChild(optionElement); // Agrega la <option> al <select>
	});
	console.log("size combo: "+comboServicios.length)

	// 4. Añade el elemento <select> al DOM (por ejemplo, al body)
	//document.body.appendChild(comboServicios);

	// Opcional: Asignarle un ID para poder acceder a él más tarde
	//comboServicios.id = 'miSelectDinamico';
  }