package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.CategoriaDistribucionDTO;
import TFG_EMG.Horizonte_Economico.dto.ResumenMensualDTO;
import TFG_EMG.Horizonte_Economico.model.entity.Gasto;
import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.GastoRepositorio;
import TFG_EMG.Horizonte_Economico.repository.IngresoRepositorio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class MetricaServicio {

    private final UsuarioActualServicio usuarioActualServicio;
    private final IngresoRepositorio ingresoRepositorio;
    private final GastoRepositorio gastoRepositorio;

    public MetricaServicio(UsuarioActualServicio usuarioActualServicio,
                           IngresoRepositorio ingresoRepositorio,
                           GastoRepositorio gastoRepositorio) {
        this.usuarioActualServicio = usuarioActualServicio;
        this.ingresoRepositorio = ingresoRepositorio;
        this.gastoRepositorio = gastoRepositorio;
    }

    public ResumenMensualDTO resumenMensual(String mesYYYYMM) {
        YearMonth ym = parseMes(mesYYYYMM);
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();

        LocalDate desde = ym.atDay(1);
        LocalDate hasta = ym.atEndOfMonth();

        List<Ingreso> ingresos = ingresoRepositorio.findAllByUsuarioIdAndFechaBetweenOrderByFechaDesc(u.getId(), desde, hasta);
        List<Gasto> gastos = gastoRepositorio.findAllByUsuarioIdAndFechaBetweenOrderByFechaDesc(u.getId(), desde, hasta);

        BigDecimal totalIngresos = ingresos.stream()
                .map(Ingreso::getImporte)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGastos = gastos.stream()
                .map(Gasto::getImporte)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal sobrante = totalIngresos.subtract(totalGastos);

        List<CategoriaDistribucionDTO> distribucion = distribucionGastos(gastos, totalGastos);

        return new ResumenMensualDTO(ym.toString(), scale2(totalIngresos), scale2(totalGastos), scale2(sobrante), distribucion);
    }

    private List<CategoriaDistribucionDTO> distribucionGastos(List<Gasto> gastos, BigDecimal totalGastos) {
        Map<String, BigDecimal> porCategoria = new HashMap<>();

        for (Gasto g : gastos) {
            String nombre = (g.getCategoria() != null) ? g.getCategoria().getNombre() : "Sin categoría";
            BigDecimal imp = g.getImporte() == null ? BigDecimal.ZERO : g.getImporte();
            porCategoria.merge(nombre, imp, BigDecimal::add);
        }

        List<CategoriaDistribucionDTO> lista = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> e : porCategoria.entrySet()) {
            BigDecimal total = scale2(e.getValue());
            BigDecimal porcentaje = BigDecimal.ZERO;
            if (totalGastos.compareTo(BigDecimal.ZERO) > 0) {
                porcentaje = e.getValue()
                        .multiply(BigDecimal.valueOf(100))
                        .divide(totalGastos, 2, RoundingMode.HALF_UP);
            }
            lista.add(new CategoriaDistribucionDTO(e.getKey(), total, porcentaje));
        }

        lista.sort(Comparator.comparing(CategoriaDistribucionDTO::getTotal).reversed());
        return lista;
    }

    private YearMonth parseMes(String mesYYYYMM) {
        if (mesYYYYMM == null || mesYYYYMM.isBlank()) {
            return YearMonth.now();
        }
        return YearMonth.parse(mesYYYYMM.trim());
    }

    private BigDecimal scale2(BigDecimal v) {
        return v.setScale(2, RoundingMode.HALF_UP);
    }
}