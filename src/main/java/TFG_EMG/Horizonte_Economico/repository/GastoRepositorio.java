package TFG_EMG.Horizonte_Economico.repository;

import TFG_EMG.Horizonte_Economico.model.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GastoRepositorio extends JpaRepository<Gasto, Long> {
    List<Gasto> findAllByUsuarioIdOrderByFechaDesc(Long usuarioId);
    Optional<Gasto> findByIdAndUsuarioId(Long id, Long usuarioId);
}