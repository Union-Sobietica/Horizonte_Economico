package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.dto.CategoriaCrearSolicitud;
import TFG_EMG.Horizonte_Economico.dto.GastoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.dto.IngresoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.TipoCategoria;
import TFG_EMG.Horizonte_Economico.service.CategoriaServicio;
import TFG_EMG.Horizonte_Economico.service.GastoServicio;
import TFG_EMG.Horizonte_Economico.service.IngresoServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class FinanzasControlador {

    private final CategoriaServicio categoriaServicio;
    private final IngresoServicio ingresoServicio;
    private final GastoServicio gastoServicio;

    public FinanzasControlador(CategoriaServicio categoriaServicio,
                               IngresoServicio ingresoServicio,
                               GastoServicio gastoServicio) {
        this.categoriaServicio = categoriaServicio;
        this.ingresoServicio = ingresoServicio;
        this.gastoServicio = gastoServicio;
    }

    @GetMapping("/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", categoriaServicio.listar());
        model.addAttribute("tiposCategoria", TipoCategoria.values());
        model.addAttribute("categoriaCrearSolicitud", new CategoriaCrearSolicitud());
        return "categorias";
    }

    @PostMapping("/categorias")
    public String crearCategoria(@Valid @ModelAttribute CategoriaCrearSolicitud categoriaCrearSolicitud,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaServicio.listar());
            model.addAttribute("tiposCategoria", TipoCategoria.values());
            return "categorias";
        }
        try {
            categoriaServicio.crear(categoriaCrearSolicitud);
        } catch (RuntimeException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            model.addAttribute("categorias", categoriaServicio.listar());
            model.addAttribute("tiposCategoria", TipoCategoria.values());
            return "categorias";
        }
        return "redirect:/usuario/categorias";
    }

    @PostMapping("/categorias/{id}/borrar")
    public String borrarCategoria(@PathVariable Long id) {
        categoriaServicio.borrar(id);
        return "redirect:/usuario/categorias";
    }

    @GetMapping("/ingresos")
    public String ingresos(Model model) {
        model.addAttribute("ingresos", ingresoServicio.listar());
        model.addAttribute("ingresoCrearSolicitud", new IngresoCrearSolicitud());
        return "ingresos";
    }

    @PostMapping("/ingresos")
    public String crearIngreso(@Valid @ModelAttribute IngresoCrearSolicitud ingresoCrearSolicitud,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingresos", ingresoServicio.listar());
            return "ingresos";
        }
        ingresoServicio.crear(ingresoCrearSolicitud);
        return "redirect:/usuario/ingresos";
    }

    @PostMapping("/ingresos/{id}/borrar")
    public String borrarIngreso(@PathVariable Long id) {
        ingresoServicio.borrar(id);
        return "redirect:/usuario/ingresos";
    }

    @GetMapping("/gastos")
    public String gastos(Model model) {
        model.addAttribute("gastos", gastoServicio.listar());
        model.addAttribute("categoriasGasto", categoriaServicio.listarPorTipo(TipoCategoria.GASTO));
        model.addAttribute("gastoCrearSolicitud", new GastoCrearSolicitud());
        return "gastos";
    }

    @PostMapping("/gastos")
    public String crearGasto(@Valid @ModelAttribute GastoCrearSolicitud gastoCrearSolicitud,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("gastos", gastoServicio.listar());
            model.addAttribute("categoriasGasto", categoriaServicio.listarPorTipo(TipoCategoria.GASTO));
            return "gastos";
        }
        try {
            gastoServicio.crear(gastoCrearSolicitud);
        } catch (RuntimeException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            model.addAttribute("gastos", gastoServicio.listar());
            model.addAttribute("categoriasGasto", categoriaServicio.listarPorTipo(TipoCategoria.GASTO));
            return "gastos";
        }
        return "redirect:/usuario/gastos";
    }

    @PostMapping("/gastos/{id}/borrar")
    public String borrarGasto(@PathVariable Long id) {
        gastoServicio.borrar(id);
        return "redirect:/usuario/gastos";
    }
}