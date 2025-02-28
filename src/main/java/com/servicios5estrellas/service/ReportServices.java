package com.servicios5estrellas.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.servicios5estrellas.model.Servicio;
import com.servicios5estrellas.model.ServicioOT;
import com.servicios5estrellas.reportes.model.InformeDiarioLineaDetalle;
import com.servicios5estrellas.reportes.model.InformeDiarioRpt;
import com.servicios5estrellas.reportes.model.VentaDiariaPorServicio;
import com.servicios5estrellas.reportes.model.VentaMensualPorServicio;
import com.servicios5estrellas.reportes.model.VentasDiariasPorServicio;
import com.servicios5estrellas.reportes.model.VentasMensualesPorServicio;
import com.servicios5estrellas.repository.IServicioOTRepository;
import com.servicios5estrellas.repository.ITipoServicioRepository;
//import com.servicios5estrellas.repository.ServicioDAO;
//import com.servicios5estrellas.repository.ServicioOTDAO;

@Service
public class ReportServices {
	
	@Autowired
	private ITipoServicioRepository repo;
	
	@Autowired
	private IServicioOTRepository servicioOTrepo;
	
	public InformeDiarioRpt getInformeDiario() {
		InformeDiarioRpt informeDiario = new InformeDiarioRpt();
//		ServicioDAO servicioDao = new ServicioDAO();
		InformeDiarioLineaDetalle detalle;
//		int monto;
		
//		TODO el mes y el año deben ser paramétricos
		int mes = 3;
		int agno = 2019;
		int day;
		
//		Balance Enero del  2019 Por Tipo de Servicio
		informeDiario.setTitulo("Balance "+mes+" del "+agno+" Por Tipo de Servicio");
//		for(Servicio tipoServicio : servicioDao.findAll()) {
		for(Servicio tipoServicio : repo.findAll()) {
			detalle = new InformeDiarioLineaDetalle();
			detalle.setServicio(tipoServicio);
			informeDiario.getLineas().put(tipoServicio.getIdServicio(), detalle);
		}
		
//		TODO
//		En el ciclo siguiente habría que procesar solo aquellos servicios cuya fecha corresponde al mes del reporte.
//		Lo ideal sería que los registros vinieran ya filtrados desde la base de datos
//		Objects<ServicioOT> servicios = new ServicioOTDAO().getMontoServicios();
//		TODO standarizar el nombre del método con Neodatis y mejorar la consulta para que traiga solo los serviciosOT del año ymes requeridos
		List<ServicioOT> servicios = servicioOTrepo.findAll();
		for(ServicioOT s : servicios) {
//			se procesan solo los servicios del año y mes indicados
			if(s.getOt().getFecha().getYear() == agno && s.getOt().getFecha().getMonthValue() == mes) {
//				System.out.println(s.getIdServicioOT()+" - "+s.getOt().getFecha()+" - "+s.getMonto());
				detalle = informeDiario.getLineas().get(s.getTipoServicio().getIdServicio());
//				detalle.setServicio(s.getTipoServicio());
				day = getDay(s.getOt().getFecha());
//				System.out.println("getDay: "+day);
				detalle.getTotalDiarioPorServicio()[day] = detalle.getTotalDiarioPorServicio()[day] + s.getMonto();
//				detalle.getTotalDias().put(day, monto);
				detalle.setTotalMes(detalle.getTotalMes() + s.getMonto());
//				una alternativa para guardar el total mesual por servicio
				detalle.getTotalDiarioPorServicio()[0] = detalle.getTotalDiarioPorServicio()[0] + s.getMonto();
				informeDiario.getTotalPorDia()[day] = informeDiario.getTotalPorDia()[day] + s.getMonto();
			}
		}
		return informeDiario;
	}
	
	public VentasDiariasPorServicio getVentasDiariasPorServicio(int agno, int mes) {
		VentasDiariasPorServicio informe = new VentasDiariasPorServicio();
		VentaDiariaPorServicio lineaRpt;
		int day;
		int idService;
		LocalDate fecha;
		int idxTotales;
//		ServicioDAO servicioDao = new ServicioDAO();

		fecha = LocalDate.of(agno, mes, 1);
		informe.setTitulo("Balance Diario Por Tipo de Servicio - "+fecha.getMonth()+" del "+agno);
		for(Servicio tipoServicio : repo.findAll()) {
//			System.out.println("nombre servicio: "+tipoServicio.getNombre());
//			System.out.println("informe.getHeaders()[tipoServicio.getIdServicio()]: "+informe.getHeaders()[tipoServicio.getIdServicio()]);
			informe.getHeaders()[tipoServicio.getIdServicio()+1] = tipoServicio.getNombre();
		}
		
		
//		 asignar líneas al diccionario lineas e inicializar cada línea del reporte con la fecha
		for (int i = 1; i <= fecha.lengthOfMonth(); i++) {
			lineaRpt = new VentaDiariaPorServicio();
			lineaRpt.setFecha(LocalDate.of(agno, mes, i));
			informe.getLineas().put(i, lineaRpt);
//			idxTotales = i + 1;
		}
		idxTotales = fecha.lengthOfMonth()+1;
		VentaDiariaPorServicio totalesMensuales = new VentaDiariaPorServicio();
		informe.getLineas().put(idxTotales, totalesMensuales);
		
//		Objects<ServicioOT> servicios = new ServicioOTDAO().getMontoServicios();
		List<ServicioOT> servicios = servicioOTrepo.findAll();
		for(ServicioOT s : servicios) {
//			se procesan solo los servicios del año indicado
			if(s.getOt().getFecha().getYear() == agno && s.getOt().getFecha().getMonthValue() == mes) {
//				System.out.println(s.getIdServicioOT()+" - "+s.getOt().getFecha()+" - "+s.getMonto());
				
				day = getDay(s.getOt().getFecha());
				idService = s.getTipoServicio().getIdServicio();
//				System.out.println("getDay: "+day);
				lineaRpt = informe.getLineas().get(day);
				lineaRpt.getTotalDiarioPorServicio()[idService] = lineaRpt.getTotalDiarioPorServicio()[idService] + s.getMonto();
//				total diario
				lineaRpt.getTotalDiarioPorServicio()[0] = lineaRpt.getTotalDiarioPorServicio()[0] + s.getMonto();
				totalesMensuales.getTotalDiarioPorServicio()[idService] = totalesMensuales.getTotalDiarioPorServicio()[idService] + s.getMonto();
				totalesMensuales.getTotalDiarioPorServicio()[0] = totalesMensuales.getTotalDiarioPorServicio()[0] + s.getMonto();
			}
		}
		return informe;
	}
	
	public VentasMensualesPorServicio getVentasMensualesPorServicio(int agno) {
		VentasMensualesPorServicio informe = new VentasMensualesPorServicio();
		VentaMensualPorServicio lineaRpt;
//		ServicioDAO servicioDao = new ServicioDAO();
//		TODO arreglo transitorio para determinar los años registrados en el sistema. Esta lista debe ser obtenida desde la base de datos
		String agnos[] = {"2017","2018","2019","2020"};
		String key;
		int idService;
		
		informe.setTitulo("Balance Mensual Por Tipo de Servicio "+agno);
		for(Servicio tipoServicio : repo.findAll()) {
			informe.getHeaders()[tipoServicio.getIdServicio()+1] = tipoServicio.getNombre();
		}
		
//		 asignar líneas al diccionario lineas e inicializar cada línea del reporte con la fecha
		for (String year : agnos) {
			if(Integer.valueOf(year).intValue() == agno || agno == 1) {
				for (int i = 1; i <= 12; i++) {
					lineaRpt = new VentaMensualPorServicio();
					lineaRpt.setMes(i);
					lineaRpt.setYear(Integer.valueOf(year));
					key = year+formatMes(i);
	//				System.out.println("key: "+key);
					informe.getLineas().put(key, lineaRpt);
	//				System.out.println("linearpt "+lineaRpt.toString());
				}
			}
		}
		System.out.println("ReportService ===> agno: "+agno);
//		Objects<ServicioOT> servicios = new ServicioOTDAO().getMontoServicios();
		List<ServicioOT> servicios = servicioOTrepo.findAll();
		for(ServicioOT s : servicios) {
//			se procesan solo los servicios del año y mes indicados
			if(s.getOt().getFecha().getYear() == agno || agno == 1) {
//				System.out.println(s.getIdServicioOT()+" - "+s.getOt().getFecha()+" - "+s.getMonto());
				
				key = getKey(s.getOt().getFecha());
//				System.out.println("key: "+key);
				
				idService = s.getTipoServicio().getIdServicio();
				lineaRpt = informe.getLineas().get(key);
//				System.out.println("linearpt: "+lineaRpt.toString());
				lineaRpt.getTotalMensualPorServicio()[idService] = lineaRpt.getTotalMensualPorServicio()[idService] + s.getMonto();
				
//				total mensual
				lineaRpt.getTotalMensualPorServicio()[0] = lineaRpt.getTotalMensualPorServicio()[0] + s.getMonto();
			}
		}
		
		return informe;
	}
	
	/**
	 * Retorna el día del mes(entre 1 y 31) de la fecha correspondiente al parámetro
	 * @return
	 */
	public int getDay(LocalDate date) {
		return date.getDayOfMonth();
	}
	
	/**
	 * retorna el número correspondiente al mes como un string numérico de dos dígitos.
	 * Por ejmplo: a enero le corresponde el 1. Se devuelve "01".
	 * @param mes
	 * @return
	 */
	public String formatMes(int mes) {
		DecimalFormat formatter = new DecimalFormat("##");
		String s = String.format("%02d", mes);
		return s;
//		return formatter.format(mes);
	}
	
	public String getKey(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		return date.format(formatter);
	}

}
