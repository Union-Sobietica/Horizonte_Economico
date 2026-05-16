package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.CategoriaResumenDTO;
import TFG_EMG.Horizonte_Economico.dto.EstadisticasAnualesDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Servicio de negocio encargado de coordinar las reglas de excel.
 */
@Service
public class ExcelServicio {

    /**
     * Genera la respuesta o documento solicitado a partir de los datos disponibles.
     */
    public byte[] generarExcelEstadisticas(EstadisticasAnualesDTO dto) {

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            crearHojaResumenAnual(workbook, dto);
            crearHojasMensuales(workbook, dto);

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            workbook.write(output);

            return output.toByteArray();
        }
        catch (Exception e) {
            throw new RuntimeException("Error generando Excel", e);
        }
    }

    /**
     * Crea la hoja principal con el resumen anual por mes.
     */
    private void crearHojaResumenAnual(XSSFWorkbook workbook, EstadisticasAnualesDTO dto) {
        Sheet sheet = workbook.createSheet("Resumen anual");

        Row cabecera = sheet.createRow(0);

        cabecera.createCell(0).setCellValue("Mes");
        cabecera.createCell(1).setCellValue("Ingresos");
        cabecera.createCell(2).setCellValue("Gastos");

        for (int i = 0; i < dto.getMeses().size(); i++) {

            Row fila = sheet.createRow(i + 2);

            fila.createCell(0).setCellValue(dto.getMeses().get(i));

            fila.createCell(1).setCellValue(
                    dto.getIngresos().get(i).doubleValue()
            );

            fila.createCell(2).setCellValue(
                    dto.getGastos().get(i).doubleValue()
            );
        }

        Row totales = sheet.createRow(dto.getMeses().size() + 3);

        totales.createCell(0).setCellValue("TOTAL");
        totales.createCell(1).setCellValue(dto.getTotalIngresos().doubleValue());
        totales.createCell(2).setCellValue(dto.getTotalGastos().doubleValue());

        autoSize(sheet, 3);
    }

    /**
     * Crea una hoja por mes con el detalle de ingresos y gastos por categoria.
     */
    private void crearHojasMensuales(XSSFWorkbook workbook, EstadisticasAnualesDTO dto) {
        for (int i = 0; i < dto.getMeses().size(); i++) {
            List<CategoriaResumenDTO> resumenes = obtenerResumenMensual(dto, i);
            Sheet sheet = workbook.createSheet(nombreHojaMensual(dto.getMeses().get(i), i + 1));

            Row cabecera = sheet.createRow(0);
            cabecera.createCell(0).setCellValue("Tipo");
            cabecera.createCell(1).setCellValue("Categoria");
            cabecera.createCell(2).setCellValue("Importe");

            int filaActual = 2;
            for (CategoriaResumenDTO resumen : resumenes) {
                Row fila = sheet.createRow(filaActual++);
                fila.createCell(0).setCellValue(resumen.getTipo());
                fila.createCell(1).setCellValue(resumen.getCategoria());
                fila.createCell(2).setCellValue(importe(resumen.getTotal()).doubleValue());
            }

            if (resumenes.isEmpty()) {
                Row fila = sheet.createRow(filaActual++);
                fila.createCell(0).setCellValue("Sin movimientos");
            }

            filaActual++;
            Row totalIngresos = sheet.createRow(filaActual++);
            totalIngresos.createCell(1).setCellValue("Total ingresos");
            totalIngresos.createCell(2).setCellValue(dto.getIngresos().get(i).doubleValue());

            Row totalGastos = sheet.createRow(filaActual++);
            totalGastos.createCell(1).setCellValue("Total gastos");
            totalGastos.createCell(2).setCellValue(dto.getGastos().get(i).doubleValue());

            Row balance = sheet.createRow(filaActual);
            balance.createCell(1).setCellValue("Balance");
            balance.createCell(2).setCellValue(dto.getIngresos().get(i).subtract(dto.getGastos().get(i)).doubleValue());

            autoSize(sheet, 3);
        }
    }

    /**
     * Devuelve el resumen mensual si esta disponible.
     */
    private List<CategoriaResumenDTO> obtenerResumenMensual(EstadisticasAnualesDTO dto, int indice) {
        if (dto.getResumenesMensuales() == null || dto.getResumenesMensuales().size() <= indice) {
            return Collections.emptyList();
        }
        return dto.getResumenesMensuales().get(indice);
    }

    /**
     * Normaliza el nombre de la hoja para cumplir las restricciones de Excel.
     */
    private String nombreHojaMensual(String mes, int numeroMes) {
        String nombre = String.format("%02d %s", numeroMes, mes == null ? "Mes" : mes);
        return nombre
                .replace("[", "")
                .replace("]", "")
                .replace("*", "")
                .replace("?", "")
                .replace("/", "-")
                .replace("\\", "-")
                .substring(0, Math.min(nombre.length(), 31));
    }

    /**
     * Evita nulos al escribir importes en las celdas.
     */
    private BigDecimal importe(BigDecimal importe) {
        return importe == null ? BigDecimal.ZERO : importe;
    }

    /**
     * Ajusta el ancho de las columnas usadas.
     */
    private void autoSize(Sheet sheet, int columnas) {
        for (int i = 0; i < columnas; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
