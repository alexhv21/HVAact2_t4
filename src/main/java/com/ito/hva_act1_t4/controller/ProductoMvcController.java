package com.ito.hva_act1_t4.controller;

import com.ito.hva_act1_t4.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoMvcController {

    @Value("${config.autor}")
    private String autor;

    @Value("${config.proyecto}")
    private String proyecto;

    // Lista simulada en memoria
    public static List<ProductoDTO> productos = new ArrayList<>(List.of(
    new ProductoDTO(1L, "Laptop Gamer", 22500.0, "Computo"),
    new ProductoDTO(2L, "Mouse Inalámbrico", 450.0, "Accesorios"),
    new ProductoDTO(3L, "Teclado Mecánico", 1200.0, "Accesorios"),
    new ProductoDTO(4L, "Monitor 144Hz", 3800.0, "Computo")
));

    // a) Listado con Model, th:each y filtro opcional con @RequestParam
    @GetMapping("/listar")
    public String listar(@RequestParam(name = "categoria", required = false) String categoria, Model model) {
        List<ProductoDTO> listaFiltrada = productos;
        if (categoria != null && !categoria.isBlank()) {
            listaFiltrada = productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
        }
        model.addAttribute("productos", listaFiltrada);
        model.addAttribute("categoriaFiltro", categoria != null ? categoria : "Todas");
        model.addAttribute("autor", autor);
        model.addAttribute("proyecto", proyecto);
        return "productos/listar";
    }

    // Detalle por ID con @PathVariable
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id, Model model) {
        ProductoDTO productoEncontrado = productos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);

        model.addAttribute("producto", productoEncontrado);
        model.addAttribute("autor", autor);
        return "productos/detalle";
    }

    // b) Formulario que usa @ModelAttribute para vincular datos
    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new ProductoDTO());
        model.addAttribute("autor", autor);
        return "productos/formulario";
    }

    // Procesar envío del formulario web
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") ProductoDTO producto) {
        producto.setId((long) (productos.size() + 1));
        productos.add(producto);
        return "redirect:/productos/listar";
    }
}