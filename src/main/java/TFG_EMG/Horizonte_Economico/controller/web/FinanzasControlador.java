package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * DTOs para formularios de categorías, ingresos y gastos
 */
import TFG_EMG.Horizonte_Economico.dto.CategoriaCrearSolicitud;
import TFG_EMG.Horizonte_Economico.dto.GastoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.dto.IngresoCrearSolicitud;

/**
 * Enum para diferenciar categorías de ingreso y gasto
 */
import TFG_EMG.Horizonte_Economico.model.entity.TipoCategoria;

/**
 * Servicios de la lógica de negocio
 */
import TFG_EMG.Horizonte_Economico.service.CategoriaServicio;
import TFG_EMG.Horizonte_Economico.service.GastoServicio;
import TFG_EMG.Horizonte_Economico.service.IngresoServicio;

/**
 * Validación de formularios
 */
import jakarta.validation.Valid;

/**
 * Controlador MVC
 */
import org.springframework.stereotype.Controller;

/**
 * Modelo para enviar datos a las vistas
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
 * Controlador web para gestionar categorías, ingresos y gastos
 */
@Controller
@RequestMapping("/usuario")
public class FinanzasControlador {

    /**
     * Servicios principales del módulo financiero
     */
    private final CategoriaServicio categoriaServicio;
    private final IngresoServicio ingresoServicio;
    private final GastoServicio gastoServicio;

    /**
     * Inicializa las dependencias necesarias para FinanzasControlador.
     */
    public FinanzasControlador(CategoriaServicio categoriaServicio,
                               IngresoServicio ingresoServicio,
                               GastoServicio gastoServicio) {
        this.categoriaServicio = categoriaServicio;
        this.ingresoServicio = ingresoServicio;
        this.gastoServicio = gastoServicio;
    }

    /**
     * Muestra la vista de categorías del usuario
     */
    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categoriaServicio.listar());
        model.addAttribute("tiposCategoria", TipoCategoria.values());
        model.addAttribute("categoriaCrearSolicitud", new CategoriaCrearSolicitud());
        return "categorias";
    }

    /**
     * Procesa la creación de una nueva categoría
     */
    @PostMapping("/categorias")
    public String crearCategoria(@Valid @ModelAttribute CategoriaCrearSolicitud categoriaCrearSolicitud,
                                 BindingResult bindingResult,
                                 Model model) {

        /**
         * Si hay errores de validación, recarga la vista
         */
        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaServicio.listar());
            model.addAttribute("tiposCategoria", TipoCategoria.values());
            return "categorias";
        }

        try {
            categoriaServicio.crear(categoriaCrearSolicitud);

            /**
             * Captura errores de negocio, por ejemplo categoría duplicada
             */
        } catch (RuntimeException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            model.addAttribute("categorias", categoriaServicio.listar());
            model.addAttribute("tiposCategoria", TipoCategoria.values());
            return "categorias";
        }

        return "redirect:/usuario/categorias";
    }

    /**
     * Elimina una categoría
     */
    @PostMapping("/categorias/{id}/borrar")
    public String borrarCategoria(@PathVariable Long id) {
        categoriaServicio.borrar(id);
        return "redirect:/usuario/categorias";
    }

    /**
     * Muestra la vista de ingresos
     */
    @GetMapping("/ingresos")
    public String ingresos(Model model) {
        model.addAttribute("ingresos", ingresoServicio.listar());
        model.addAttribute("categoriasIngreso", categoriaServicio.listarPorTipo(TipoCategoria.INGRESO));
        model.addAttribute("ingresoCrearSolicitud", new IngresoCrearSolicitud());
        return "ingresos";
    }

    /**
     * Procesa la creación de un ingreso
     */
    @PostMapping("/ingresos")
    public String crearIngreso(@Valid @ModelAttribute IngresoCrearSolicitud ingresoCrearSolicitud,
                               BindingResult bindingResult,
                               Model model) {

        /**
         * Si falla validación, vuelve a la vista
         */
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingresos", ingresoServicio.listar());
            model.addAttribute("categoriasIngreso", categoriaServicio.listarPorTipo(TipoCategoria.INGRESO));
            return "ingresos";
        }

        try {
            ingresoServicio.crear(ingresoCrearSolicitud);

            /**
             * Captura errores de negocio
             */
        } catch (RuntimeException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            model.addAttribute("ingresos", ingresoServicio.listar());
            model.addAttribute("categoriasIngreso", categoriaServicio.listarPorTipo(TipoCategoria.INGRESO));
            return "ingresos";
        }

        return "redirect:/usuario/ingresos";
    }

    /**
     * Elimina un ingreso
     */
    @PostMapping("/ingresos/{id}/borrar")
    public String borrarIngreso(@PathVariable Long id) {
        ingresoServicio.borrar(id);
        return "redirect:/usuario/ingresos";
    }

    /**
     * Muestra el formulario de edición de un ingreso
     */
    @GetMapping("/ingresos/{id}/editar")
    public String editarIngresoForm(@PathVariable Long id, Model model) {
        var ingreso = ingresoServicio.obtenerPropio(id);

        /**
         * Se rellena el DTO con los datos actuales para editar
         */
        IngresoCrearSolicitud solicitud = new IngresoCrearSolicitud();
        solicitud.setCategoriaId(ingreso.getCategoria().getId());
        solicitud.setImporte(ingreso.getImporte());
        solicitud.setFecha(ingreso.getFecha());
        solicitud.setDescripcion(ingreso.getDescripcion());

        model.addAttribute("ingresoId", id);
        model.addAttribute("categoriasIngreso", categoriaServicio.listarPorTipo(TipoCategoria.INGRESO));
        model.addAttribute("ingresoCrearSolicitud", solicitud);
        return "editar-ingreso";
    }

    /**
     * Guarda los cambios de un ingreso editado
     */
    @PostMapping("/ingresos/{id}/editar")
    public String editarIngreso(@PathVariable Long id,
                                @Valid @ModelAttribute IngresoCrearSolicitud ingresoCrearSolicitud,
                                BindingResult bindingResult,
                                Model model) {

        /**
         * Si hay errores, mantiene la vista de edición
         */
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingresoId", id);
            model.addAttribute("categoriasIngreso", categoriaServicio.listarPorTipo(TipoCategoria.INGRESO));
            return "editar-ingreso";
        }

        ingresoServicio.actualizar(id, ingresoCrearSolicitud);
        return "redirect:/usuario/ingresos";
    }

    /**
     * Muestra la vista de gastos
     */
    @GetMapping("/gastos")
    public String gastos(Model model) {
        model.addAttribute("gastos", gastoServicio.listar());
        model.addAttribute("categoriasGasto", categoriaServicio.listarPorTipo(TipoCategoria.GASTO));
        model.addAttribute("gastoCrearSolicitud", new GastoCrearSolicitud());
        return "gastos";
    }

    /**
     * Procesa la creación de un gasto
     */
    @PostMapping("/gastos")
    public String crearGasto(@Valid @ModelAttribute GastoCrearSolicitud gastoCrearSolicitud,
                             BindingResult bindingResult,
                             Model model) {

        /**
         * Si falla la validación, recarga la vista
         */
        if (bindingResult.hasErrors()) {
            model.addAttribute("gastos", gastoServicio.listar());
            model.addAttribute("categoriasGasto", categoriaServicio.listarPorTipo(TipoCategoria.GASTO));
            return "gastos";
        }

        try {
            gastoServicio.crear(gastoCrearSolicitud);

            /**
             * Captura errores de negocio
             */
        } catch (RuntimeException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            model.addAttribute("gastos", gastoServicio.listar());
            model.addAttribute("categoriasGasto", categoriaServicio.listarPorTipo(TipoCategoria.GASTO));
            return "gastos";
        }

        return "redirect:/usuario/gastos";
    }

    /**
     * Elimina un gasto
     */
    @PostMapping("/gastos/{id}/borrar")
    public String borrarGasto(@PathVariable Long id) {
        gastoServicio.borrar(id);
        return "redirect:/usuario/gastos";
    }

    /**
     * Muestra el formulario de edición de un gasto
     */
    @GetMapping("/gastos/{id}/editar")
    public String editarGastoForm(@PathVariable Long id, Model model) {
        var gasto = gastoServicio.obtenerPropio(id);

        /**
         * Se rellena el DTO con los datos actuales para editar
         */
        GastoCrearSolicitud solicitud = new GastoCrearSolicitud();
        solicitud.setCategoriaId(gasto.getCategoria().getId());
        solicitud.setImporte(gasto.getImporte());
        solicitud.setFecha(gasto.getFecha());
        solicitud.setDescripcion(gasto.getDescripcion());

        model.addAttribute("gastoId", id);
        model.addAttribute("categoriasGasto", categoriaServicio.listarPorTipo(TipoCategoria.GASTO));
        model.addAttribute("gastoCrearSolicitud", solicitud);
        return "editar-gasto";
    }

    /**
     * Guarda los cambios de un gasto editado
     */
    @PostMapping("/gastos/{id}/editar")
    public String editarGasto(@PathVariable Long id,
                              @Valid @ModelAttribute GastoCrearSolicitud gastoCrearSolicitud,
                              BindingResult bindingResult,
                              Model model) {

        /**
         * Si hay errores, mantiene la vista de edición
         */
        if (bindingResult.hasErrors()) {
            model.addAttribute("gastoId", id);
            model.addAttribute("categoriasGasto", categoriaServicio.listarPorTipo(TipoCategoria.GASTO));
            return "editar-gasto";
        }

        gastoServicio.actualizar(id, gastoCrearSolicitud);
        return "redirect:/usuario/gastos";
    }
}
