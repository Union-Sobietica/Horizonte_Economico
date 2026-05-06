package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.*;
import TFG_EMG.Horizonte_Economico.model.entity.Gasto;
import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.GastoRepositorio;
import TFG_EMG.Horizonte_Economico.repository.IngresoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

/**
 * Servicio de negocio encargado de coordinar las reglas de metrica.
 */
@Service
@Transactional(readOnly = true)
public class MetricaServicio {

    private final UsuarioActualServicio usuarioActualServicio;
    private final IngresoRepositorio ingresoRepositorio;
    private final GastoRepositorio gastoRepositorio;
    private final FamiliaServicio familiaServicio;

    /**
     * Inicializa las dependencias necesarias para MetricaServicio.
     */
    public MetricaServicio(UsuarioActualServicio usuarioActualServicio,
                           IngresoRepositorio ingresoRepositorio,
                           GastoRepositorio gastoRepositorio,
                           FamiliaServicio familiaServicio) {
        this.usuarioActualServicio = usuarioActualServicio;
        this.ingresoRepositorio = ingresoRepositorio;
        this.gastoRepositorio = gastoRepositorio;
        this.familiaServicio = familiaServicio;
    }

    /**
     * Calcula y devuelve el resumen financiero solicitado.
     */
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

        return new ResumenMensualDTO(
                ym.toString(),
                scale2(totalIngresos),
                scale2(totalGastos),
                scale2(sobrante),
                distribucion,
                resumenPorCategorias(ingresos, gastos)
        );
    }

    /**
     * Ejecuta la operacion distribucionGastos dentro del flujo de MetricaServicio.
     */
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

    /**
     * Calcula y devuelve el resumen financiero solicitado.
     */
    public ResumenFamiliarDTO resumenFamiliar(String mesYYYYMM) {
        YearMonth ym = parseMes(mesYYYYMM);
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();

        LocalDate desde = ym.atDay(1);
        LocalDate hasta = ym.atEndOfMonth();

        List<Ingreso> ingresos = ingresoRepositorio.findAllByUsuarioIdAndFechaBetweenOrderByFechaDesc(u.getId(), desde, hasta);
        List<Gasto> gastos = gastoRepositorio.findAllByUsuarioIdAndFechaBetweenOrderByFechaDesc(u.getId(), desde, hasta);

        BigDecimal totalIngresosUsuario = ingresos.stream()
                .map(Ingreso::getImporte)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGastos = gastos.stream()
                .map(Gasto::getImporte)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal extra = familiaServicio.ingresosFamiliaresExtra();
        BigDecimal totalIngresosFamilia = totalIngresosUsuario.add(extra);
        BigDecimal sobranteFamiliar = totalIngresosFamilia.subtract(totalGastos);

        return new ResumenFamiliarDTO(
                ym.toString(),
                scale2(totalIngresosUsuario),
                scale2(extra),
                scale2(totalIngresosFamilia),
                scale2(totalGastos),
                scale2(sobranteFamiliar),
                distribucionGastos(gastos, totalGastos),
                resumenPorCategoriasFamiliar(ingresos, gastos, extra)
        );
    }

    /**
     * Calcula y devuelve el resumen financiero solicitado.
     */
    private List<CategoriaResumenDTO> resumenPorCategoriasFamiliar(List<Ingreso> ingresos,
                                                                   List<Gasto> gastos,
                                                                   BigDecimal ingresosFamiliaresExtra) {
        List<CategoriaResumenDTO> resultado = resumenPorCategorias(ingresos, gastos);

        if (ingresosFamiliaresExtra != null && ingresosFamiliaresExtra.compareTo(BigDecimal.ZERO) > 0) {
            resultado.add(new CategoriaResumenDTO(
                    "Ingresos familiares extra",
                    "INGRESO_FAMILIAR",
                    scale2(ingresosFamiliaresExtra)
            ));
        }

        return resultado;
    }

    /**
     * Calcula y devuelve el resumen financiero solicitado.
     */
    private List<CategoriaResumenDTO> resumenPorCategorias(List<Ingreso> ingresos, List<Gasto> gastos) {
        Map<String, BigDecimal> ingresosPorCategoria = new LinkedHashMap<>();
        Map<String, BigDecimal> gastosPorCategoria = new LinkedHashMap<>();

        for (Ingreso i : ingresos) {
            String nombre = (i.getCategoria() != null) ? i.getCategoria().getNombre() : "Sin categoría";
            BigDecimal importe = i.getImporte() == null ? BigDecimal.ZERO : i.getImporte();
            ingresosPorCategoria.merge(nombre, importe, BigDecimal::add);
        }

        for (Gasto g : gastos) {
            String nombre = (g.getCategoria() != null) ? g.getCategoria().getNombre() : "Sin categoría";
            BigDecimal importe = g.getImporte() == null ? BigDecimal.ZERO : g.getImporte();
            gastosPorCategoria.merge(nombre, importe, BigDecimal::add);
        }

        List<CategoriaResumenDTO> resultado = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> e : ingresosPorCategoria.entrySet()) {
            resultado.add(new CategoriaResumenDTO(
                    e.getKey(),
                    "INGRESO",
                    scale2(e.getValue())
            ));
        }

        for (Map.Entry<String, BigDecimal> e : gastosPorCategoria.entrySet()) {
            resultado.add(new CategoriaResumenDTO(
                    e.getKey(),
                    "GASTO",
                    scale2(e.getValue())
            ));
        }

        return resultado;
    }

    /**
     * Convierte el valor textual recibido en el tipo de fecha esperado.
     */
    private YearMonth parseMes(String mesYYYYMM) {
        if (mesYYYYMM == null || mesYYYYMM.isBlank()) {
            return YearMonth.now();
        }
        return YearMonth.parse(mesYYYYMM.trim());
    }

    /**
     * Normaliza importes monetarios a dos decimales.
     */
    private BigDecimal scale2(BigDecimal v) {
        return v.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula las estadisticas agregadas solicitadas.
     */
    public EstadisticasAnualesDTO estadisticasAnuales(int anio) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();

        List<String> meses = new ArrayList<>();
        List<BigDecimal> ingresosMes = new ArrayList<>();
        List<BigDecimal> gastosMes = new ArrayList<>();

        BigDecimal totalIngresos = BigDecimal.ZERO;
        BigDecimal totalGastos = BigDecimal.ZERO;

        for (int i = 1; i <= 12; i++) {
            YearMonth ym = YearMonth.of(anio, i);

            LocalDate desde = ym.atDay(1);
            LocalDate hasta = ym.atEndOfMonth();

            List<Ingreso> ingresos = ingresoRepositorio
                    .findAllByUsuarioIdAndFechaBetweenOrderByFechaDesc(u.getId(), desde, hasta);

            List<Gasto> gastos = gastoRepositorio
                    .findAllByUsuarioIdAndFechaBetweenOrderByFechaDesc(u.getId(), desde, hasta);

            BigDecimal totalIngMes = ingresos.stream()
                    .map(Ingreso::getImporte)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalGasMes = gastos.stream()
                    .map(Gasto::getImporte)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            meses.add(ym.getMonth()
                    .getDisplayName(java.time.format.TextStyle.FULL, new java.util.Locale("es", "ES")));

            ingresosMes.add(scale2(totalIngMes));
            gastosMes.add(scale2(totalGasMes));

            totalIngresos = totalIngresos.add(totalIngMes);
            totalGastos = totalGastos.add(totalGasMes);
        }

        return new EstadisticasAnualesDTO(
                anio,
                meses,
                ingresosMes,
                gastosMes,
                scale2(totalIngresos),
                scale2(totalGastos)
        );
    }

}
