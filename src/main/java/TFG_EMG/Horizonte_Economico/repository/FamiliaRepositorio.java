package TFG_EMG.Horizonte_Economico.repository;

import TFG_EMG.Horizonte_Economico.model.entity.Familia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamiliaRepositorio extends JpaRepository<Familia, Long> {
    Optional<Familia> findByUsuarioId(Long usuarioId);
}