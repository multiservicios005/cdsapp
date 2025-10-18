package com.servicios5estrellas.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.servicios5estrellas.model.Cliente;
import com.servicios5estrellas.model.Orden_De_Trabajo;
import com.servicios5estrellas.model.Servicio;
import com.servicios5estrellas.model.ServicioOT;
import com.servicios5estrellas.pdf.OrdenTrabajoPDF;
import com.servicios5estrellas.reportes.model.InformeDiarioLineaDetalle;
import com.servicios5estrellas.reportes.model.InformeDiarioRpt;
import com.servicios5estrellas.reportes.model.VentasDiariasPorServicio;
import com.servicios5estrellas.reportes.model.VentasMensualesPorServicio;
import com.servicios5estrellas.repository.IClienteRepository;
import com.servicios5estrellas.repository.IOrden_De_TrabajoRepository;
import com.servicios5estrellas.repository.IServicioOTRepository;
import com.servicios5estrellas.repository.ITipoServicioRepository;
import com.servicios5estrellas.repository.SequenceDao;
import com.servicios5estrellas.service.ExcelService;
import com.servicios5estrellas.service.ReportServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class ControlServiciosController {
	
	@Autowired
	private ITipoServicioRepository repo;
	
	@Autowired
	private IClienteRepository repocli;
	
	@Autowired
	private SequenceDao seqDao;
	
	@Autowired
	private IServicioOTRepository servicioOTrepo;
	
	@Autowired
	private ExcelService excelService;
	
//	@Autowired
//	private Test test;
	
	@Autowired
	private ReportServices reportService;
	
	@Autowired
	private IOrden_De_TrabajoRepository repoOT;
	
	@RequestMapping("/")
	public String start() {
		return "index";
	}
	
	@RequestMapping("/test")
	public String test() {
		System.out.println("llamando a la página test.html ");
		return "test";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping("/help")
	public String help() {
		return "help";
	}
	
	@RequestMapping("/insertclient")
	public String insertclient() {
		return "insertclient";
	}
	
	@RequestMapping("/getallclients")
	public String getallclients() {
		return "getallclients";
	}
	
	@RequestMapping("/getcliente")
	public String getcliente() {
		return "getcliente";
	}
	
	@RequestMapping("/getclientebyid")
	public String getclientebyid(@RequestParam(name="id") Integer id, Model model) {
		model.addAttribute("idCliente", id);
		return "getclientebyid";
	}
	
	@RequestMapping("/deletecliente")
	public String angulardelete() {
		return "deletecliente";
	}
	
	@RequestMapping("/modifyclient")
	public String angularmodify() {
		return "modifyclient";
	}
	
	@RequestMapping("/generarTipoServicios")
	public String generarTipoServicios(Model model) {
		
		repo.save(new Servicio(1, "Lavado de Alfombras Muro a Muro"));
		repo.save(new Servicio(2, "Lavado de Alfombras Sueltas"));
		repo.save(new Servicio(3, "Limpieza de Piso Flotante"));
		repo.save(new Servicio(4, "Limpieza de Tapices de Muebles"));
		repo.save(new Servicio(5, "Limpieza de Tapices de Automóviles"));
		repo.save(new Servicio(6, "Lavandería Con Retiro a Domicilio"));
		repo.save(new Servicio(7, "Limpieza de Colchones"));
//		repo.save(new Servicio(10, "Otro Servicio"));
		
		model.addAttribute("tiposDeServicios", repo.findAll());
		return "allTipoServicios";
	}
	
	@RequestMapping("/genSequences")
	public String genSequences() {
		seqDao.crearSequences();
		return "index";
	}
	
	@RequestMapping("/crearcliente")
	public String crearcliente() {
		
		Cliente cliente = new Cliente("Francisco Pizarro", "956983427", "Nueva Aurora 1321", LocalDate.of(2019, 11, 7), "Cuarto cliente ingresado para probar el ID generado por una SEQ");
	    
	    Orden_De_Trabajo ot = new Orden_De_Trabajo(110000, LocalDate.of(2019, 11, 6), "Trabajo en Reñaca", cliente);
	     
	    ot.addServicioOT(new ServicioOT(repo.findById(2).get(), "Alfombra de 3x3", 30000, ot));
	    ot.addServicioOT(new ServicioOT(repo.findById(2).get(), "Alfombra Blanca de 2x2", 45000, ot));
	    ot.addServicioOT(new ServicioOT(repo.findById(1).get(), "Pieza de 3x2", 35000, ot));
	    
	    cliente.addOT(ot);
	    
//	    cliente.addOT(new Orden_De_Trabajo());
	    
	    repocli.save(cliente);

	    return "getallclients";
	}
	
	/**
	 * Importación de los datos a partir de una planilla excel.
	 * @return
	 */
	@RequestMapping("/importarclientes")
	public String importarClientes() {
		System.out.println("ControlServiciosController.importarClientes ");

//		ExcelService excelService = new ExcelService(repocli);
		excelService.importarExcel();
		
		return "help";
	}
	
	@RequestMapping("/informeDiario")
	public String informeDiario(Model model) {
//		InformeDiarioRpt informe = new ReportServices().getInformeDiario();
		int agno = LocalDate.now().getYear();
		int mes = LocalDate.now().getMonthValue();
System.out.println("nro de tipos de servicios: "+repo.findAll().size());
//		VentasDiariasPorServicio informe = new ReportServices().getVentasDiariasPorServicio(agno, mes);
		VentasDiariasPorServicio informe = reportService.getVentasDiariasPorServicio(agno, mes);
/**		
		for (Map.Entry<Integer, InformeDiarioLineaDetalle> entry : informe.getLineas().entrySet()) {
        	System.out.println("detalle: "+entry.getValue());
        	for (int i = 0; i < entry.getValue().getTotalDiarioPorServicio().length; i++) {
        		System.out.println("total día: "+i+" - "+ entry.getValue().getTotalDiarioPorServicio()[i]);
				
			}
        }

		for (int i = 1; i < informe.getTotalPorDia().length; i++) {
			System.out.println("Totales diarios: "+i+" - "+informe.getTotalPorDia()[i]);
		}
**/
		model.addAttribute("agno", agno);
		model.addAttribute("month", mes);
		model.addAttribute("informe", informe);
		return "informeDiario";
	}
	
	@PostMapping("/informeDiario2")
	public String getInformeDiario(Model model, int year, int mes) {
//		VentasDiariasPorServicio informe = new ReportServices().getVentasDiariasPorServicio(year, mes);
		VentasDiariasPorServicio informe = reportService.getVentasDiariasPorServicio(year, mes);

//		System.out.println("Post Mapping  "+year+" - "+mes);
		model.addAttribute("agno", year);
		model.addAttribute("month", mes);
		model.addAttribute("informe", informe);
		return "informeDiario";
	}
	
	@RequestMapping("/informeMensual")
	public String informeMensual(Model model, int year) {
//		System.out.println("ControlServiciosController.informeMensual() >>> year = "+year);
//		VentasMensualesPorServicio informe = new ReportServices().getVentasMensualesPorServicio(year);
		VentasMensualesPorServicio informe = reportService.getVentasMensualesPorServicio(year);
		
		model.addAttribute("agno", year);
		model.addAttribute("informe", informe);
		return "informeMensual";
	}
	
	/**
	 * opción transitoria para cerrar la aplicación, shutdown el tomcat y liberar el puerto 8080
	 * TODO la opción definitiva debería atrapar el close de la página para invocar este método
	 * que cierra la aplicación.
	 * @return
	 */
	@RequestMapping("/exitApplication")
	public String exitApplication() {
		System.exit(0);
		return "exitApplication";
	}
	
	@GetMapping("/importView")
	public String importView() {
		System.out.println("ControlServiciosController.importView.");
		
		return "import";
	}
	
	@RequestMapping("/getNroOT")
	public String getNroOT() {
		return "getNroOT";
	}
	
	@RequestMapping("/generarPdfOTold")
	public String generarPdfOTold(HttpServletRequest req) {
		System.out.println("ControlServiciosController.generarPdfOT ");
		
		System.out.println("Número OT: "+req.getParameter("idOT"));
		
//		Obtener la OT desde la base de datos
//		Generar el PDF
		OrdenTrabajoPDF otPDF = new OrdenTrabajoPDF();
		otPDF.generarpdf();
		otPDF.descargarpdfOld();
		
		return "generarPdfOT";
	}
	
	@RequestMapping("/generarPdfOT")
	public void generarPdfOT(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("ControlServiciosController.generarPdfOT ");
		
		int idOT = Integer.valueOf(req.getParameter("idOT"));
		OrdenTrabajoPDF otPDF = new OrdenTrabajoPDF(repoOT.getReferenceById(idOT));
		
		resp.setContentType("application/pdf");
//		String filename = req.getParameter("filename");
		
		resp.setHeader("content-disposition", "attachment; filename=ordendetrabajo.pdf");
		otPDF.descargarpdf(resp);
		
	}

}
