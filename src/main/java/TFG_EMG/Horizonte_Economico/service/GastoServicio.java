package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.GastoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Categoria;
import TFG_EMG.Horizonte_Economico.model.entity.Gasto;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.GastoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio de negocio encargado de coordinar las reglas de gasto.
 */
@Service
@Transactional(readOnly = true)
public class GastoServicio {

    private final GastoRepositorio gastoRepositorio;
    private final UsuarioActualServicio usuarioActualServicio;
    private final CategoriaServicio categoriaServicio;

    /**
     * Inicializa las dependencias necesarias para GastoServicio.
     */
    public GastoServicio(GastoRepositorio gastoRepositorio,
                         UsuarioActualServicio usuarioActualServicio,
                         CategoriaServicio categoriaServicio) {
        this.gastoRepositorio = gastoRepositorio;
        this.usuarioActualServicio = usuarioActualServicio;
        this.categoriaServicio = categoriaServicio;
    }

    /**
     * Recupera la lista de datos solicitada para la vista o la API.
     */
    public List<Gasto> listar() {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return gastoRepositorio.findAllByUsuarioIdOrderByFechaDesc(u.getId());
    }

    /**
     * Procesa la creacion del recurso recibido.
     */
    @Transactional
    public void crear(GastoCrearSolicitud solicitud) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Categoria categoria = categoriaServicio.obtenerPropia(solicitud.getCategoriaId(), u.getId());

        Gasto gasto = new Gasto(
                u,
                categoria,
                solicitud.getImporte(),
                solicitud.getFecha(),
                normalizar(solicitud.getDescripcion())
        );
        gastoRepositorio.save(gasto);
    }

    /**
     * Elimina el recurso indicado cuando pertenece al usuario autorizado.
     */
    @Transactional
    public void borrar(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Gasto g = gastoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Gasto no encontrado"));
        gastoRepositorio.delete(g);
    }

    /**
     * Procesa la actualizacion del recurso indicado.
     */
    @Transactional
    public void actualizar(Long id, GastoCrearSolicitud solicitud) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();

        Gasto gasto = gastoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Gasto no encontrado"));

        Categoria categoria = categoriaServicio.obtenerPropia(solicitud.getCategoriaId(), u.getId());

        gasto.setCategoria(categoria);
        gasto.setImporte(solicitud.getImporte());
        gasto.setFecha(solicitud.getFecha());
        gasto.setDescripcion(normalizar(solicitud.getDescripcion()));

        gastoRepositorio.save(gasto);
    }

    /**
     * Obtiene el recurso solicitado aplicando las validaciones necesarias.
     */
    public Gasto obtenerPropio(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return gastoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Gasto no encontrado"));
    }

    /**
     * Valida y normaliza el texto recibido antes de usarlo.
     */
    private String normalizar(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isBlank() ? null : t;
    }
}
