package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.GastoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Categoria;
import TFG_EMG.Horizonte_Economico.model.entity.Gasto;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.GastoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GastoServicio {

    private final GastoRepositorio gastoRepositorio;
    private final UsuarioActualServicio usuarioActualServicio;
    private final CategoriaServicio categoriaServicio;

    public GastoServicio(GastoRepositorio gastoRepositorio,
                         UsuarioActualServicio usuarioActualServicio,
                         CategoriaServicio categoriaServicio) {
        this.gastoRepositorio = gastoRepositorio;
        this.usuarioActualServicio = usuarioActualServicio;
        this.categoriaServicio = categoriaServicio;
    }

    public List<Gasto> listar() {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return gastoRepositorio.findAllByUsuarioIdOrderByFechaDesc(u.getId());
    }

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

    @Transactional
    public void borrar(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Gasto g = gastoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Gasto no encontrado"));
        gastoRepositorio.delete(g);
    }

    private String normalizar(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isBlank() ? null : t;
    }
}