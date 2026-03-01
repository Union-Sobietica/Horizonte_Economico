package TFG_EMG.Horizonte_Economico.repository;

import TFG_EMG.Horizonte_Economico.model.entity.Categoria;
import TFG_EMG.Horizonte_Economico.model.entity.TipoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByUsuarioIdOrderByNombreAsc(Long usuarioId);
    List<Categoria> findAllByUsuarioIdAndTipoOrderByNombreAsc(Long usuarioId, TipoCategoria tipo);
    Optional<Categoria> findByIdAndUsuarioId(Long id, Long usuarioId);
    boolean existsByUsuarioIdAndNombreIgnoreCaseAndTipo(Long usuarioId, String nombre, TipoCategoria tipo);
}