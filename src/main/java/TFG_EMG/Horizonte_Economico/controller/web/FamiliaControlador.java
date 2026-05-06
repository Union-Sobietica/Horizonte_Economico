package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * DTOs para actualizar familia y crear miembros
 */
import TFG_EMG.Horizonte_Economico.dto.FamiliaActualizarSolicitud;
import TFG_EMG.Horizonte_Economico.dto.MiembroFamiliarCrearSolicitud;

/**
 * Enums para valores de formulario (selects)
 */
import TFG_EMG.Horizonte_Economico.model.entity.EstadoCivil;
import TFG_EMG.Horizonte_Economico.model.entity.TipoMiembroFamiliar;

/**
 * Entidad familia
 */
import TFG_EMG.Horizonte_Economico.model.entity.Familia;

/**
 * Servicio con la lógica de negocio
 */
import TFG_EMG.Horizonte_Economico.service.FamiliaServicio;

/**
 * Validación
 */
import jakarta.validation.Valid;

/**
 * Controlador MVC
 */
import org.springframework.stereotype.Controller;

/**
 * Modelo para pasar datos a la vista
 */
import org.springframework.ui.Model;

/**
 * Manejo de errores de validación
 */
import org.springframework.validation.BindingResult;

/**
 * Anotaciones web
 */
import org.springframework.web.bind.annotation.*;

/**
 * Controlador web para la gestión de familia
 */
@Controller
@RequestMapping("/usuario")
public class FamiliaControlador {

    /**
     * Servicio de familia
     */
    private final FamiliaServicio familiaServicio;

    /**
     * Inicializa las dependencias necesarias para FamiliaControlador.
     */
    public FamiliaControlador(FamiliaServicio familiaServicio) {
        this.familiaServicio = familiaServicio;
    }

    /**
     * Muestra la vista principal de familia
     */
    @GetMapping("/familia")
    public String familia(Model model) {
        var f = familiaServicio.obtenerOCrearSiNoExiste();

        /**
         * Datos principales
         */
        model.addAttribute("familia", f);
        model.addAttribute("estadosCiviles", EstadoCivil.values());
        model.addAttribute("tiposMiembro", TipoMiembroFamiliar.values());

        /**
         * Formularios
         */
        model.addAttribute("familiaActualizarSolicitud", crearSolicitud(f));
        model.addAttribute("miembros", familiaServicio.listarMiembros());
        model.addAttribute("miembroFamiliarCrearSolicitud", new MiembroFamiliarCrearSolicitud());

        return "familia";
    }

    /**
     * Actualiza los datos de la familia
     */
    @PostMapping("/familia")
    public String actualizarFamilia(@Valid @ModelAttribute FamiliaActualizarSolicitud familiaActualizarSolicitud,
                                    BindingResult bindingResult,
                                    Model model) {

        /**
         * Si hay errores, recarga la vista con datos
         */
        if (bindingResult.hasErrors()) return cargarVistaFamilia(model);

        familiaServicio.actualizarFamilia(familiaActualizarSolicitud);
        return "redirect:/usuario/familia";
    }

    /**
     * Añade un nuevo miembro familiar
     */
    @PostMapping("/familia/miembros")
    public String crearMiembro(@Valid @ModelAttribute MiembroFamiliarCrearSolicitud miembroFamiliarCrearSolicitud,
                               BindingResult bindingResult,
                               Model model) {

        /**
         * Si hay errores, recarga la vista
         */
        if (bindingResult.hasErrors()) return cargarVistaFamilia(model);

        familiaServicio.crearMiembro(miembroFamiliarCrearSolicitud);
        return "redirect:/usuario/familia";
    }

    /**
     * Elimina un miembro familiar
     */
    @PostMapping("/familia/miembros/{id}/borrar")
    public String borrarMiembro(@PathVariable Long id) {
        familiaServicio.borrarMiembro(id);
        return "redirect:/usuario/familia";
    }

    /**
     * Crea un DTO a partir de la entidad para el formulario
     */
    private FamiliaActualizarSolicitud crearSolicitud(Familia f) {
        FamiliaActualizarSolicitud s = new FamiliaActualizarSolicitud();
        s.setEstadoCivil(f.getEstadoCivil());
        return s;
    }

    /**
     * Método reutilizable para recargar la vista con todos los datos
     */
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
