package com.ito.hva_act1_t4.controller;

import com.ito.hva_act1_t4.dto.ProductoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @PostMapping("/crear")
    public Map<String, Object> crearProducto(@RequestBody ProductoDTO nuevoProducto) {
        // Asignar ID si viene nulo o calcular el siguiente
        if (nuevoProducto.getId() == null) {
            nuevoProducto.setId((long) (ProductoMvcController.productos.size() + 1));
        }

        // AGREGAR EL PRODUCTO A LA LISTA QUE MUESTRA THYMELEAF
        ProductoMvcController.productos.add(nuevoProducto);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto recibido y agregado exitosamente al catálogo");
        respuesta.put("productoGuardado", nuevoProducto);
        respuesta.put("totalProductos", ProductoMvcController.productos.size());
        respuesta.put("status", "SUCCESS");
        return respuesta;
    }
}