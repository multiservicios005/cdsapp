package com.servicios5estrellas.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.servicios5estrellas.model.Cliente;
import com.servicios5estrellas.model.Orden_De_Trabajo;
import com.servicios5estrellas.model.ServicioOT;
import com.servicios5estrellas.repository.IOrden_De_TrabajoRepository;

import jakarta.servlet.http.HttpServletResponse;

public class OrdenTrabajoPDF {
	
	private Orden_De_Trabajo ot;
	
	Document documento;
	
	Font fontTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14);
	Font tituloNivel = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
	
	public OrdenTrabajoPDF() {
		super();
	}

	public OrdenTrabajoPDF(Orden_De_Trabajo ot) {
		super();
		this.ot = ot;
	}

	public void crearDocumento() {
		documento = new Document(PageSize.A4, 35, 30, 50, 50);
		
//		String ruta = System.getProperty("user.home");
		String ruta = System.getProperty("user.dir");
		try {
//			fileOutputStream = new FileOutputStream(ruta + "/Desktop/reporteov.pdf");
//			java.io.FileNotFoundException: /root/Desktop/reporteov.pdf (No such file or directory)
//			FileOutputStream fileOutputStream = new FileOutputStream(ruta + "/reporteov.pdf");
//			FileOutputStream fileOutputStream = new FileOutputStream(ruta + "/Documents/workspace-spring-tool-suite-4-4.14.0.RELEASE/cdsapp/src/main/resources/static/reporteov.pdf");
			FileOutputStream fileOutputStream = new FileOutputStream(ruta + "/src/main/resources/static/ordendetrabajo.pdf");
			PdfWriter.getInstance(documento, fileOutputStream);
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void abrirDocumento() {
		documento.open();
	}
	
	public void agregarTitulo(String texto) {
		PdfPTable tabla = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase(texto, fontTitulo));
		celda.setColspan(5);
		celda.setBackgroundColor(BaseColor.WHITE);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(celda);
		try {
			documento.add(tabla);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Esta versión genera el pdf reporteov.pdf y lo descarga automáticamente
	 * en la carpeta C:\Users\julius
	 */
	public void generarpdf() {
		crearDocumento();
		abrirDocumento();
		
		try {
			Image img = Image.getInstance("src/main/resources/static/img/logo.jpg");
			img.scalePercent(40);
			img.setAlignment(Element.ALIGN_CENTER);
			documento.add(img);
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		agregarTitulo("PRESUPUESTO");
		documento.close();
	}
	
	/**
	 * Este método obtiene el archivo reporteov.pdf desde la carpeta
	 * static y hace una copia que guarda en el archivo reporteov2.pdf
	 * que guarda en la carpeta /Users/julius/Downloads del computador.
	 */
	public void descargarpdfOld() {
		
//		String urlStr = "https://www.journaldev.com/sitemap.xml";
		String urlStr = "http://localhost:8080/reporteov.pdf";
//		String urlStr = "http://cdsapp.onrender.com/reporteov.pdf";
//		String file = "/Users/pankaj/sitemap.xml";
//		String file = "/Users/julius/reporteov2.pdf";
		String file = System.getProperty("user.home")+"/Downloads/reporteov2.pdf";
		
		try {
			URL url = new URL(urlStr);
			ReadableByteChannel rbc;
			rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream(file);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void descargarpdf(HttpServletResponse resp) {
		System.out.println("OrdenTrabajoPDF.descargarpdf ");
		
		System.out.println("OrdenTrabajoPDF: "+ ot);
		Cliente cliente = ot.getCliente();
		System.out.println("Cliente: "+ cliente);
		
		documento = new Document(PageSize.A4, 28, 30, 50, 50);
		try {
			PdfWriter.getInstance(documento, resp.getOutputStream());
			documento.open();
			Image img = Image.getInstance("src/main/resources/static/img/logo.jpg");
			img.scalePercent(40);
			img.setAlignment(Element.ALIGN_CENTER);
			documento.add(img);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		agregarTitulo("PRESUPUESTO");
		agregarSaltosDeLinea();
		agregarDireccionLocal("Extremadura 55, Local 1B, Gómez Carreño, Viña del Mar");
		agregarSaltosDeLinea();
		agregarLineaConTexto("Solicitud de Trabajo / Presupuesto");
		agregarSaltosDeLinea();
//		agregarDatosDelcliente("Nombre:", "Marisol Gonzalez");
		agregarDatosDelcliente("Nombre:", cliente.getNombre());
//		agregarSaltosDeLinea();
//		agregarDatosDelcliente("Número de Celular: +569", "92504505");
		agregarDatosDelcliente("Número de Celular: +569", cliente.getTelefono());
//		agregarSaltosDeLinea();
//		agregarDatosDelcliente("Dirección del Trabajo:", "Gastón Hamel 255/B");
		agregarDatosDelcliente("Dirección del Trabajo:", cliente.getDireccion());
		agregarSaltosDeLinea();
		agregarLineaConTexto("Lavado de Alfombras / Limpieza de Cubrepisos / Lavandería / Limpieza de Tapices");
		agregarSaltosDeLinea();
		agregarLineaConTexto("Descripción del servicio:-");
		agregarSaltosDeLinea();
		
		agregarServicios();
		agregarSaltosDeLinea();
		agregarFecha();
		agregarSaltosDeLinea();
		
		agregarNota("1.   Algunas alfombras pueden sufrir decoloración durante el lavado.");
		agregarNota("2.   El tiempo de entrega es estimado. La entrega se confirmará con el cliente.");
		agregarNota("3.   Todo trabajo debe ser pagado contra entrega o transferencia previa.");
		agregarNota("4.   El cliente debe asegurarse que haya alguien adulto para entregar o recibir.");
		
		documento.close();
	}
	
	/**
	 * Agrega la dirección del Local en la OT.
	 * @param texto
	 */
	public void agregarDireccionLocal(String texto) {
		PdfPTable tabla = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase(texto, fontTitulo));
		celda.setColspan(5);
		celda.setBackgroundColor(BaseColor.WHITE);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setBorder(PdfPCell.NO_BORDER);
		tabla.addCell(celda);
		try {
			documento.add(tabla);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarLineaConTexto(String texto) {
		PdfPTable tabla = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase(texto, fontTitulo));
		celda.setColspan(5);
		celda.setBackgroundColor(BaseColor.WHITE);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setBorder(PdfPCell.NO_BORDER);
		tabla.addCell(celda);
		try {
			documento.add(tabla);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarSaltosDeLinea() {
		Paragraph saltosdelinea = new Paragraph();
		saltosdelinea.add(new Phrase(Chunk.NEWLINE));
//		saltosdelinea.add(new Phrase(Chunk.NEWLINE));
		try {
			documento.add(saltosdelinea);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarDatosDelcliente(String label, String dato) {
		PdfPTable tabla = new PdfPTable(3);
//		PdfPCell celda = new PdfPCell(new Phrase("Nombre:", tituloNivel));
		PdfPCell celda = new PdfPCell(new Phrase(label, tituloNivel));
		celda.setColspan(1);
		tabla.addCell(celda);
//		tabla.addCell("ALTOS");
		
//		celda = new PdfPCell(new Phrase("Marisol Gonzalez", tituloNivel));
		celda = new PdfPCell(new Phrase(dato, tituloNivel));
		celda.setColspan(2);
		tabla.addCell(celda);
		
		try {
			documento.add(tabla);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarServicios() {
		PdfPTable tabla = new PdfPTable(3);
		PdfPCell celda = new PdfPCell(new Phrase("Tipo De Servicio", tituloNivel));
//		celda.setColspan(3);
		tabla.addCell(celda);
		celda = new PdfPCell(new Phrase("Detalle del Servicio", tituloNivel));
//		celda.setColspan(3);
		tabla.addCell(celda);
		celda = new PdfPCell(new Phrase("Monto", tituloNivel));
//		celda.setColspan(3);
		tabla.addCell(celda);
		
		for (ServicioOT servicio : ot.getServicios()) {
//			System.out.println("Servicio: "+ servicio);
			tabla.addCell(servicio.getTipoServicio().getNombre());
			tabla.addCell(servicio.getDetalleServicio());
			tabla.addCell(servicio.getMonto().toString());
		}
		tabla.addCell("");
		tabla.addCell("Valor Total $");
		tabla.addCell(ot.getTotal().toString());
		
		try {
			documento.add(tabla);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void agregarFecha() {
		PdfPTable tabla = new PdfPTable(3);
		tabla.addCell("Fecha:");
		tabla.addCell(ot.getFecha().toString());
		tabla.addCell("");
		
		try {
			documento.add(tabla);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarNota(String texto) {
		Font fontNota = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
		fontNota.setColor(BaseColor.RED);
		PdfPTable tabla = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase(texto, fontNota));
		celda.setColspan(5);
		celda.setBackgroundColor(BaseColor.WHITE);
		celda.setHorizontalAlignment(Element.ALIGN_LEFT);
		celda.setBorder(PdfPCell.NO_BORDER);
		tabla.addCell(celda);
		try {
			documento.add(tabla);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
