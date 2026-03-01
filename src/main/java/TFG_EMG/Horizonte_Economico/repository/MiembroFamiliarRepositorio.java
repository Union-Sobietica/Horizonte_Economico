package TFG_EMG.Horizonte_Economico.repository;

import TFG_EMG.Horizonte_Economico.model.entity.MiembroFamiliar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MiembroFamiliarRepositorio extends JpaRepository<MiembroFamiliar, Long> {
    List<MiembroFamiliar> findAllByFamiliaIdOrderByTipoAscNombreAsc(Long familiaId);
    Optional<MiembroFamiliar> findByIdAndFamiliaId(Long id, Long familiaId);
}