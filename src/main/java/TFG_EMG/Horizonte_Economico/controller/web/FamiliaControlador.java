package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.dto.FamiliaActualizarSolicitud;
import TFG_EMG.Horizonte_Economico.dto.MiembroFamiliarCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.EstadoCivil;
import TFG_EMG.Horizonte_Economico.model.entity.Familia;
import TFG_EMG.Horizonte_Economico.model.entity.TipoMiembroFamiliar;
import TFG_EMG.Horizonte_Economico.service.FamiliaServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class FamiliaControlador {

    private final FamiliaServicio familiaServicio;

    public FamiliaControlador(FamiliaServicio familiaServicio) {
        this.familiaServicio = familiaServicio;
    }

    @GetMapping("/familia")
    public String familia(Model model) {
        var f = familiaServicio.obtenerOCrearSiNoExiste();
        model.addAttribute("familia", f);
        model.addAttribute("estadosCiviles", EstadoCivil.values());
        model.addAttribute("tiposMiembro", TipoMiembroFamiliar.values());
        model.addAttribute("familiaActualizarSolicitud", crearSolicitud(f));
        model.addAttribute("miembros", familiaServicio.listarMiembros());
        model.addAttribute("miembroFamiliarCrearSolicitud", new MiembroFamiliarCrearSolicitud());
        return "familia";
    }

    @PostMapping("/familia")
    public String actualizarFamilia(@Valid @ModelAttribute FamiliaActualizarSolicitud familiaActualizarSolicitud,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) return cargarVistaFamilia(model);
        familiaServicio.actualizarFamilia(familiaActualizarSolicitud);
        return "redirect:/usuario/familia";
    }

    @PostMapping("/familia/miembros")
    public String crearMiembro(@Valid @ModelAttribute MiembroFamiliarCrearSolicitud miembroFamiliarCrearSolicitud,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) return cargarVistaFamilia(model);
        familiaServicio.crearMiembro(miembroFamiliarCrearSolicitud);
        return "redirect:/usuario/familia";
    }

    @PostMapping("/familia/miembros/{id}/borrar")
    public String borrarMiembro(@PathVariable Long id) {
        familiaServicio.borrarMiembro(id);
        return "redirect:/usuario/familia";
    }

    private FamiliaActualizarSolicitud crearSolicitud(Familia f) {
        FamiliaActualizarSolicitud s = new FamiliaActualizarSolicitud();
        s.setEstadoCivil(f.getEstadoCivil());
        return s;
    }

    private String cargarVistaFamilia(Model model) {
        Familia f = familiaServicio.obtenerOCrearSiNoExiste();
        model.addAttribute("familia", f);
        model.addAttribute("estadosCiviles", EstadoCivil.values());
        model.addAttribute("tiposMiembro", TipoMiembroFamiliar.values());
        model.addAttribute("familiaActualizarSolicitud", crearSolicitud(f));
        model.addAttribute("miembros", familiaServicio.listarMiembros());
        model.addAttribute("miembroFamiliarCrearSolicitud", new MiembroFamiliarCrearSolicitud());
        return "familia";
    }
}