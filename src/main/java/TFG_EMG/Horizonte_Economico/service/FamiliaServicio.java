package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.FamiliaActualizarSolicitud;
import TFG_EMG.Horizonte_Economico.dto.MiembroFamiliarCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.*;
import TFG_EMG.Horizonte_Economico.repository.FamiliaRepositorio;
import TFG_EMG.Horizonte_Economico.repository.MiembroFamiliarRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Servicio de negocio encargado de coordinar las reglas de familia.
 */
@Service
public class FamiliaServicio {

    private final UsuarioActualServicio usuarioActualServicio;
    private final FamiliaRepositorio familiaRepositorio;
    private final MiembroFamiliarRepositorio miembroRepositorio;

    /**
     * Inicializa las dependencias necesarias para FamiliaServicio.
     */
    public FamiliaServicio(UsuarioActualServicio usuarioActualServicio,
                           FamiliaRepositorio familiaRepositorio,
                           MiembroFamiliarRepositorio miembroRepositorio) {
        this.usuarioActualServicio = usuarioActualServicio;
        this.familiaRepositorio = familiaRepositorio;
        this.miembroRepositorio = miembroRepositorio;
    }

    /**
     * Obtiene el recurso solicitado aplicando las validaciones necesarias.
     */
    public Familia obtenerOCrearSiNoExiste() {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return familiaRepositorio.findByUsuarioId(u.getId())
                .orElseGet(() -> familiaRepositorio.save(new Familia(u, EstadoCivil.SOLTERO)));
    }

    /**
     * Procesa la actualizacion del recurso indicado.
     */
    @Transactional
    public void actualizarFamilia(FamiliaActualizarSolicitud solicitud) {
        Familia f = obtenerOCrearSiNoExiste();
        f.setEstadoCivil(solicitud.getEstadoCivil());
        familiaRepositorio.save(f);
    }

    /**
     * Recupera la lista de datos solicitada para la vista o la API.
     */
    public List<MiembroFamiliar> listarMiembros() {
        Familia f = obtenerOCrearSiNoExiste();
        return miembroRepositorio.findAllByFamiliaIdOrderByTipoAscNombreAsc(f.getId());
    }

    /**
     * Procesa la creacion del recurso recibido.
     */
    @Transactional
    public void crearMiembro(MiembroFamiliarCrearSolicitud solicitud) {
        Familia f = obtenerOCrearSiNoExiste();
        String nombre = solicitud.getNombre().trim();
        BigDecimal ingreso = normalizarIngreso(solicitud.getIngreso());
        miembroRepositorio.save(new MiembroFamiliar(f, solicitud.getTipo(), nombre, ingreso));
    }

    /**
     * Elimina el recurso indicado cuando pertenece al usuario autorizado.
     */
    @Transactional
    public void borrarMiembro(Long id) {
        Familia f = obtenerOCrearSiNoExiste();
        MiembroFamiliar m = miembroRepositorio.findByIdAndFamiliaId(id, f.getId())
                .orElseThrow(() -> new IllegalArgumentException("Miembro no encontrado"));
        miembroRepositorio.delete(m);
    }

    /**
     * Ejecuta la operacion ingresosFamiliaresExtra dentro del flujo de FamiliaServicio.
     */
    public BigDecimal ingresosFamiliaresExtra() {
        Familia f = obtenerOCrearSiNoExiste();
        return miembroRepositorio.findAllByFamiliaIdOrderByTipoAscNombreAsc(f.getId()).stream()
                .map(MiembroFamiliar::getIngreso)
                .filter(x -> x != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Valida y normaliza el texto recibido antes de usarlo.
     */
    private BigDecimal normalizarIngreso(BigDecimal v) {
        if (v == null) return null;
        if (v.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("El ingreso no puede ser negativo");
        return v;
    }
}
