package TFG_EMG.Horizonte_Economico.repository;

import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data para consultar y persistir ingreso.
 */
public interface IngresoRepositorio extends JpaRepository<Ingreso, Long> {

    @EntityGraph(attributePaths = {"categoria"})
    List<Ingreso> findAllByUsuarioIdOrderByFechaDesc(Long usuarioId);

    Optional<Ingreso> findByIdAndUsuarioId(Long id, Long usuarioId);

    @EntityGraph(attributePaths = {"categoria"})
    List<Ingreso> findAllByUsuarioIdAndFechaBetweenOrderByFechaDesc(Long usuarioId, LocalDate desde, LocalDate hasta);
}
