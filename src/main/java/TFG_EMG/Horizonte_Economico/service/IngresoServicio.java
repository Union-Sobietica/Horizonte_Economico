package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.IngresoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Categoria;
import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.IngresoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IngresoServicio {

    private final IngresoRepositorio ingresoRepositorio;
    private final UsuarioActualServicio usuarioActualServicio;
    private final CategoriaServicio categoriaServicio;

    public IngresoServicio(IngresoRepositorio ingresoRepositorio, UsuarioActualServicio usuarioActualServicio, CategoriaServicio categoriaServicio) {
        this.ingresoRepositorio = ingresoRepositorio;
        this.usuarioActualServicio = usuarioActualServicio;
        this.categoriaServicio = categoriaServicio;
    }

    public List<Ingreso> listar() {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return ingresoRepositorio.findAllByUsuarioIdOrderByFechaDesc(u.getId());
    }

    @Transactional
    public void crear(IngresoCrearSolicitud solicitud) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Categoria categoria = categoriaServicio.obtenerPropia(solicitud.getCategoriaId(), u.getId());

        Ingreso ingreso = new Ingreso(u, categoria, solicitud.getImporte(), solicitud.getFecha(), normalizar(solicitud.getDescripcion()));
        ingresoRepositorio.save(ingreso);
    }

    @Transactional
    public void borrar(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Ingreso i = ingresoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ingreso no encontrado"));
        ingresoRepositorio.delete(i);
    }

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

    public Ingreso obtenerPropio(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return ingresoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ingreso no encontrado"));
    }

    private String normalizar(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isBlank() ? null : t;
    }
}