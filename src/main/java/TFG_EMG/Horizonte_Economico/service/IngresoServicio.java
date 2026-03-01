package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.IngresoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.IngresoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngresoServicio {

    private final IngresoRepositorio ingresoRepositorio;
    private final UsuarioActualServicio usuarioActualServicio;

    public IngresoServicio(IngresoRepositorio ingresoRepositorio, UsuarioActualServicio usuarioActualServicio) {
        this.ingresoRepositorio = ingresoRepositorio;
        this.usuarioActualServicio = usuarioActualServicio;
    }

    public List<Ingreso> listar() {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        return ingresoRepositorio.findAllByUsuarioIdOrderByFechaDesc(u.getId());
    }

    @Transactional
    public void crear(IngresoCrearSolicitud solicitud) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Ingreso ingreso = new Ingreso(u, solicitud.getImporte(), solicitud.getFecha(), normalizar(solicitud.getFuente()));
        ingresoRepositorio.save(ingreso);
    }

    @Transactional
    public void borrar(Long id) {
        Usuario u = usuarioActualServicio.obtenerUsuarioActual();
        Ingreso i = ingresoRepositorio.findByIdAndUsuarioId(id, u.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ingreso no encontrado"));
        ingresoRepositorio.delete(i);
    }

    private String normalizar(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isBlank() ? null : t;
    }
}