package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.IngresoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Categoria;
import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.IngresoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio de negocio encargado de coordinar las reglas de ingreso.
 */
@Service
@Transactional(readOnly = true)
public class IngresoServicio {

    private final IngresoRepositorio ingresoRepositorio;
    private final UsuarioActualServicio usuarioActualServicio;
    private final CategoriaServicio categoriaServicio;

    /**
     * Inicializa las dependencias necesarias para IngresoServicio.
     */
    public IngresoServicio(IngresoRepositorio ingresoRepositorio, UsuarioActualServicio usuarioActualServicio, CategoriaServicio categoriaServicio) {
        this.ingresoRepositorio = ingresoRepositorio;
        this.usuarioActualServicio = usuarioActualServicio;
        this.categoriaServicio = categoriaServicio;
    }

    /**
     * Recupera la lista de datos solicitada para la vista o la API.
     */
    public List<Ingreso> listar() {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return ingresoRepositorio.findAllByUsuarioIdOrderByFechaDesc(u.getId());
    }

    /**
     * Procesa la creacion del recurso recibido.
     */
    @Transactional
    public void crear(IngresoCrearSolicitud solicitud) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Categoria categoria = categoriaServicio.obtenerPropia(solicitud.getCategoriaId(), u.getId());

        Ingreso ingreso = new Ingreso(u, categoria, solicitud.getImporte(), solicitud.getFecha(), normalizar(solicitud.getDescripcion()));
        ingresoRepositorio.save(ingreso);
    }

    /**
     * Elimina el recurso indicado cuando pertenece al usuario autorizado.
     */
    @Transactional
    public void borrar(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Ingreso i = ingresoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ingreso no encontrado"));
        ingresoRepositorio.delete(i);
    }

    /**
     * Procesa la actualizacion del recurso indicado.
     */
    @Transactional
    public void actualizar(Long id, IngresoCrearSolicitud solicitud) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();

        Ingreso ingreso = ingresoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ingreso no encontrado"));

        ingreso.setCategoria(categoriaServicio.obtenerPropia(solicitud.getCategoriaId(), u.getId()));
        ingreso.setImporte(solicitud.getImporte());
        ingreso.setFecha(solicitud.getFecha());
        ingreso.setDescripcion(normalizar(solicitud.getDescripcion()));

        ingresoRepositorio.save(ingreso);
    }

    /**
     * Obtiene el recurso solicitado aplicando las validaciones necesarias.
     */
    public Ingreso obtenerPropio(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return ingresoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ingreso no encontrado"));
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
