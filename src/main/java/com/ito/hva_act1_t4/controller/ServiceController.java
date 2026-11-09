package com.ito.hva_act1_t4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/servicio")
public class ServiceController {

    @GetMapping("/status")
    public Map<String, Object> checkStatus() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("servidor", "Activo");
        respuesta.put("timestamp", LocalDateTime.now().toString());
        respuesta.put("codigo", 200);
        return respuesta;
    }
}