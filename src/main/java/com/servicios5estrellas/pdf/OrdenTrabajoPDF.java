package com.servicios5estrellas.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class OrdenTrabajoPDF {
	
	Document documento;
	
	Font fontTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
	
	public void crearDocumento() {
		documento = new Document(PageSize.A4, 35, 30, 50, 50);
		
//		String ruta = System.getProperty("user.home");
		String ruta = System.getProperty("user.dir");
		try {
//			fileOutputStream = new FileOutputStream(ruta + "/Desktop/reporteov.pdf");
//			java.io.FileNotFoundException: /root/Desktop/reporteov.pdf (No such file or directory)
//			FileOutputStream fileOutputStream = new FileOutputStream(ruta + "/reporteov.pdf");
//			FileOutputStream fileOutputStream = new FileOutputStream(ruta + "/Documents/workspace-spring-tool-suite-4-4.14.0.RELEASE/cdsapp/src/main/resources/static/reporteov.pdf");
			FileOutputStream fileOutputStream = new FileOutputStream(ruta + "/src/main/resources/static/reporteov.pdf");
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
	public void descargarpdf() {
		
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

}
