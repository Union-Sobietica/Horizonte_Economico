package TFG_EMG.Horizonte_Economico.repository;

import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngresoRepositorio extends JpaRepository<Ingreso, Long> {
    List<Ingreso> findAllByUsuarioIdOrderByFechaDesc(Long usuarioId);
    Optional<Ingreso> findByIdAndUsuarioId(Long id, Long usuarioId);
}