package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.EstadisticasAnualesDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

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

            Sheet sheet = workbook.createSheet("Estadísticas");

            Row cabecera = sheet.createRow(0);

            cabecera.createCell(0).setCellValue("Mes");
            cabecera.createCell(1).setCellValue("Ingresos");
            cabecera.createCell(2).setCellValue("Gastos");

            for (int i = 0; i < dto.getMeses().size(); i++) {

                Row fila = sheet.createRow(i + 1);

                fila.createCell(0).setCellValue(dto.getMeses().get(i));

                fila.createCell(1).setCellValue(
                        dto.getIngresos().get(i).doubleValue()
                );

                fila.createCell(2).setCellValue(
                        dto.getGastos().get(i).doubleValue()
                );
            }

            Row totales = sheet.createRow(dto.getMeses().size() + 2);

            totales.createCell(0).setCellValue("TOTAL");
            totales.createCell(1).setCellValue(dto.getTotalIngresos().doubleValue());
            totales.createCell(2).setCellValue(dto.getTotalGastos().doubleValue());

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            workbook.write(output);

            return output.toByteArray();
        }
        catch (Exception e) {
            throw new RuntimeException("Error generando Excel", e);
        }
    }
}
