package com.ito.hva_act1_t4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @GetMapping("/identificacion")
    public Map<String, String> obtenerIdentificacion() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("proyecto", "HVAact1_t4");
        respuesta.put("alumno", "Alexis Hernández Vázquez");
        respuesta.put("institucion", "Instituto Tecnológico de Oaxaca");
        respuesta.put("estado", "Servidor local corriendo correctamente");
        return respuesta;
    }

    @GetMapping("/materias")
    public Map<String, Object> listarMaterias() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("carrera", "Ingeniería en Sistemas Computacionales");
        respuesta.put("semestre", "7mo");
        respuesta.put("materias", List.of(
            "Gestión de Proyectos de Software",
            "Programación Web",
            "Taller de Investigación II"
        ));
        return respuesta;
    }
}