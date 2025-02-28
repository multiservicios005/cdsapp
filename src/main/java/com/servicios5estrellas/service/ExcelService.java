package com.servicios5estrellas.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicios5estrellas.model.Cliente;
import com.servicios5estrellas.model.Orden_De_Trabajo;
import com.servicios5estrellas.model.Servicio;
import com.servicios5estrellas.model.ServicioOT;
import com.servicios5estrellas.repository.IClienteRepository;
import com.servicios5estrellas.repository.IOrden_De_TrabajoRepository;
import com.servicios5estrellas.repository.IServicioOTRepository;
import com.servicios5estrellas.util.ServiceLocator;

@Service
public class ExcelService {
	
	 private Map<Integer, Cliente> clientsMap = new HashMap<>();
	 private XSSFWorkbook workbook;
	 
	 @Autowired
	 private IClienteRepository repocli;
	 
	 @Autowired
	 private IOrden_De_TrabajoRepository otRepo;
	 
	 @Autowired
	 private IServicioOTRepository servicioRepo;
	 
	 int nextIdOT;
	 
//	 public ExcelService(IClienteRepository repocli) {
//		super();
//		this.repocli = repocli;
//	}

	public Map<Integer, Cliente> getClientsMap() {
		return clientsMap;
	}

	public void setClientsMap(Map<Integer, Cliente> clientsMap) {
		this.clientsMap = clientsMap;
	}

	public void importarExcel() {
		 try {
//			 workbook = new XSSFWorkbook("D:\\jvr\\Luis\\Control Servicios\\version tere\\ControlServicios.xlsx");
			 workbook = new XSSFWorkbook("ControlServicios.xlsx");
			 importarClientes();
			 importarOTs();
//			 mostrarclientes();
//			 mostrarOTs();
			 grabarClientes();
			 workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	public void importarClientes() {
		System.out.println("ExcelService.importarClientes ");
		int idCliente = 0;
		String nombre = "";
		String telefono;
		String direccion;
		LocalDate fecha;
		String observacion;
		String[] fechaxls;
		DataFormatter formatter = new DataFormatter();
		Cliente cliente;
//			for (Sheet sheet : workbook) {
//				System.out.println("sheet name: "+sheet.getSheetName());
//			}
			Sheet sheet = workbook.getSheet("Clientes");
			System.out.println("sheet clientes: "+sheet.getLastRowNum());
		    for (Row row : sheet) {
//		    	saltarse la primera fila de la planilla
		    	if(row.getRowNum() > 0) {
			    	cliente = new Cliente();
			        for (Cell cell : row) {
		        		if(cell.getColumnIndex() == 0) {
		        			idCliente = Integer.valueOf(formatter.formatCellValue(cell)).intValue();
//			        			System.out.println(idCliente);
		        			cliente.setIdCliente(idCliente);
		        		}
		        		if(cell.getColumnIndex() == 1) {
//		        			System.out.println("Rut: "+cell.getColumnIndex()+": "+formatter.formatCellValue(cell));
		        		}
		        		if(cell.getColumnIndex() == 2) {
		        			nombre = formatter.formatCellValue(cell);
		        			cliente.setNombre(nombre);
		        		}
		        		if(cell.getColumnIndex() == 3) {
		        			nombre = nombre + " " + formatter.formatCellValue(cell);
		        			cliente.setNombre(nombre);
		        		}
		        		if(cell.getColumnIndex() == 4) {
		        			direccion = formatter.formatCellValue(cell);
		        			cliente.setDireccion(direccion);
		        		}
		        		if(cell.getColumnIndex() == 5) {
//		        			System.out.println("Comuna: "+cell.getColumnIndex()+": "+formatter.formatCellValue(cell));
		        			cliente.setComuna(formatter.formatCellValue(cell));
		        		}
		        		if(cell.getColumnIndex() == 6) {
//		        			System.out.println("Correo: "+cell.getColumnIndex()+": "+formatter.formatCellValue(cell));
		        		}
		        		if(cell.getColumnIndex() == 7) {
		        			telefono = formatter.formatCellValue(cell);
		        			cliente.setTelefono(telefono);
		        		}
		        		if(cell.getColumnIndex() == 8) {
		        			if(!formatter.formatCellValue(cell).equals("")) {
//			        			fechaxls = formatter.formatCellValue(cell).split("/");
//			        			fecha = LocalDate.of(Integer.valueOf(fechaxls[2]), Integer.valueOf(fechaxls[1]), Integer.valueOf(fechaxls[0]));
//			        			cliente.setFecha(fecha);
			        			cliente.setFecha(getLocalDate(formatter.formatCellValue(cell)));	
		        			}
		        		}
		        		if(cell.getColumnIndex() == 9) {
		        			observacion = formatter.formatCellValue(cell);
		        			cliente.setObservacion(observacion);
		        		}
			        }
			        clientsMap.put(cliente.getIdCliente(), cliente);
		    	}
		    }
	}
	
	public void importarOTs() {
		System.out.println("ExcelService.importarOTs ");
		int idCliente = 0;
		String[] fechaxls;
		LocalDate fecha;
		DataFormatter formatter = new DataFormatter();
		Sheet sheet = workbook.getSheet("Servicios Realizados");
		System.out.println("sheet: "+sheet.getLastRowNum());
		Orden_De_Trabajo ot = null;
		ServicioOT servicio = null;
		nextIdOT = otRepo.getNextId();
		int nextServicioId = servicioRepo.getNextId();
		for (Row row : sheet) {
//			System.out.println("row: "+row.getRowNum()+" - ");
//			saltarse la primera fila de la planilla
			if(row.getRowNum() > 0) {
				ot = new Orden_De_Trabajo();
				servicio = new ServicioOT();
				for (Cell cell : row) {
	        		if(cell.getColumnIndex() == 0) {
	        			idCliente = Integer.valueOf(formatter.formatCellValue(cell)).intValue();
//		        			System.out.println(idCliente);
	        			ot.setCliente(clientsMap.get(idCliente));
	        		}
	        		if(cell.getColumnIndex() == 1) {
//	        			System.out.println("Código Servicio: "+cell.getColumnIndex()+": "+formatter.formatCellValue(cell));
	        			servicio.setTipoServicio(getTipoServicio(Integer.valueOf(formatter.formatCellValue(cell)).intValue()));
	        		}
	        		if(cell.getColumnIndex() == 5) {
//	        			System.out.println("Detalle Servicio: "+cell.getColumnIndex()+": "+formatter.formatCellValue(cell));
	        			servicio.setDetalleServicio(formatter.formatCellValue(cell));
//	        			System.out.println("importarOTs.detalleServicio: "+servicio.getDetalleServicio());
	        		}
	        		if(cell.getColumnIndex() == 6) {
	        			if(!formatter.formatCellValue(cell).equals("")) {
//		        			fechaxls = formatter.formatCellValue(cell).split("/");
//		        			fecha = LocalDate.of(Integer.valueOf(fechaxls[2]), Integer.valueOf(fechaxls[1]), Integer.valueOf(fechaxls[0]));
//		        			ot.setFecha(fecha);
		        			ot.setFecha(getLocalDate(formatter.formatCellValue(cell)));
	        			}
	        		}
	        		if(cell.getColumnIndex() == 7) {
//	        			System.out.println("Monto: "+cell.getColumnIndex()+": "+formatter.formatCellValue(cell));
//	        			servicio.setMonto(Integer.getInteger(formatter.formatCellValue(cell)));
//	        			System.out.println("importarOTs.monto parseado: "+new Integer(Integer.parseInt(formatter.formatCellValue(cell))));
//	        			System.out.println("importarOTs.monto replaceAll: "+formatter.formatCellValue(cell).replaceFirst("\\.", ""));
	        			servicio.setMonto(Integer.valueOf(formatter.formatCellValue(cell).replaceFirst("\\.", "")));
//	        			System.out.println("importarOTs.detalleServicio 2: "+servicio.getDetalleServicio());
//	        			System.out.println("importarOTs.monto: "+servicio.getMonto());
	        		}
//	        		System.out.println("OT1: "+ot.toString());
	        	}
				servicio.setIdServicioOT(nextServicioId);
				nextServicioId = nextServicioId + 1;
				ot.addServicioOT(servicio);
//				System.out.println("importarOTs.monto: "+servicio.toString());
//				System.out.println("OT: "+ot.toString());
				ot.setIdOT(nextIdOT);
				updateOTlist(ot);
			}
//			updateOTlist(ot);
		}
	}
	
	/**
	 * Actualiza las Ordenes de Trabajo y Servicios del Cliente
	 * Agrega al cliente una nueva OT o agrega un servicio a una OT ya existente
	 */
	public void updateOTlist(Orden_De_Trabajo ot) {
//		System.out.println("OT: "+ot);
//		System.out.println("cliente: "+ot.getCliente());
		Orden_De_Trabajo otExistente = ot.getCliente().getOTforDate(ot.getFecha());
		ServicioOT servicio = ot.getServicios().get(0);
//		System.out.println("updateOTlist.monto: "+servicio.getMonto());
		
//		es una OT nueva
//		if(ot.getCliente().getOrdenes_de_trabajo().isEmpty() || otExistente == null) {
		if(otExistente == null) {
			nextIdOT = nextIdOT + 1;
			ot.getCliente().addOT(ot);
//			System.out.println("monto: "+servicio.getMonto());
			ot.setTotal(servicio.getMonto());
		} else {
//			es un nuevo servicio de una OT ya existente
			otExistente.addServicioOT(servicio);
//			System.out.println("total: "+otExistente.getTotal());
//			System.out.println("total 2do servicio: "+servicio.getMonto());
			otExistente.setTotal(otExistente.getTotal() + servicio.getMonto());
		}
	}
	
	public void mostrarclientes() {
        for (Map.Entry<Integer, Cliente> entry : clientsMap.entrySet()) {
        	System.out.println();
        	System.out.println("==============================================================");
            System.out.println("cliente: "+entry.getValue().toString());
        }
	}
	
	public void mostrarOTs() {
		for (Map.Entry<Integer, Cliente> entry : clientsMap.entrySet()) {
        	System.out.println();
//        	System.out.println("==============================================================");
//            System.out.println("cliente: "+entry.getValue().toString());
            for (Orden_De_Trabajo ot : entry.getValue().getOrdenes_de_trabajo()) {
            	System.out.println("OT: "+ot.toString());
            }
        }
	}
	
	/**
	 * Graba los clientes importados desde la planilla excel.
	 */
	public void grabarClientes() {
        for (Map.Entry<Integer, Cliente> entry : clientsMap.entrySet()) {
//        	if(entry.getValue().getIdCliente() < 51) {
//        		System.out.println("grabando el cliente: "+entry.getValue().getIdCliente());
//        		System.out.println("grabando el cliente: "+entry.getValue().toString());
//        		repocli.save(entry.getValue());
//        	}
            repocli.save(entry.getValue());
        }
	}
	
//	convierte y retorna una fecha de una celda en una fecha de tipo LocalDate
	public LocalDate getLocalDate(String date) {
		String[] fechaxls = date.split("/");
		return LocalDate.of(Integer.valueOf(fechaxls[2]), Integer.valueOf(fechaxls[1]), Integer.valueOf(fechaxls[0]));
	}
	
	/**
	 * Retorna el objeto Servicio (Tipo de  Servicio) cuyo id corresponde al parámetro idTipoServicio
	 * @param idTipoServicio
	 * @return
	 */
	public Servicio getTipoServicio(int idTipoServicio) {
		
		return ServiceLocator.getFactory().getTipoServicio(idTipoServicio);
		
	}

}
