/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infraestructuras;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author manue
 */
public class SistemaReceta {
 
    public void crearReceta(){
      
            String paciente = "ola";
            String diagnostico = "a";
            String medicamento = "x";
            String doctor = "esponja";
      
        try {

            Document doc = new Document();

            PdfWriter.getInstance(
                    doc,
                    new FileOutputStream("RecetaDental.pdf")
            );

            doc.open();

            doc.add(new Paragraph("RECETA DENTAL"));

            doc.add(new Paragraph(""));

            doc.add(new Paragraph(
                    "Paciente: " + paciente
            ));

            doc.add(new Paragraph(
                    "Doctor: " + doctor
            ));

            doc.add(new Paragraph(""));

            doc.add(new Paragraph(
                    "Diagnostico:"
            ));

            doc.add(new Paragraph(
                    diagnostico
            ));

            doc.add(new Paragraph(""));

            doc.add(new Paragraph(
                    "Medicamento:"
            ));

            doc.add(new Paragraph(
                    medicamento
            ));

            doc.close();

            System.out.println("PDF creado");

        } catch (Exception e) {

            e.printStackTrace();

        }

    } }
