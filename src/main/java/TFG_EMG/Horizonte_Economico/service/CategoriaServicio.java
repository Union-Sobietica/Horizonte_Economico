package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.CategoriaCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Categoria;
import TFG_EMG.Horizonte_Economico.model.entity.TipoCategoria;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.CategoriaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServicio {

    private final CategoriaRepositorio categoriaRepositorio;
    private final UsuarioActualServicio usuarioActualServicio;

    public CategoriaServicio(CategoriaRepositorio categoriaRepositorio, UsuarioActualServicio usuarioActualServicio) {
        this.categoriaRepositorio = categoriaRepositorio;
        this.usuarioActualServicio = usuarioActualServicio;
    }

    public List<Categoria> listar() {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return categoriaRepositorio.findAllByUsuarioIdOrderByNombreAsc(u.getId());
    }

    public List<Categoria> listarPorTipo(TipoCategoria tipo) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return categoriaRepositorio.findAllByUsuarioIdAndTipoOrderByNombreAsc(u.getId(), tipo);
    }

    @Transactional
    public void crear(CategoriaCrearSolicitud solicitud) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        String nombre = solicitud.getNombre().trim();

        if (categoriaRepositorio.existsByUsuarioIdAndNombreIgnoreCaseAndTipo(u.getId(), nombre, solicitud.getTipo())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre y tipo");
        }

        categoriaRepositorio.save(new Categoria(u, nombre, solicitud.getTipo()));
    }

    @Transactional
    public void borrar(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Categoria c = categoriaRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        categoriaRepositorio.delete(c);
    }

    public Categoria obtenerPropia(Long categoriaId, Long usuarioId) {
        return categoriaRepositorio.findByIdAndUsuarioId(categoriaId, usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
    }
}