package com.miapp.controllers;


import com.miapp.models.Producto;
import com.miapp.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("producto", new Producto()); // Añade un nuevo objeto Producto para el formulario
        return "productos";  // Esto hace referencia al archivo HTML de vista llamado "productos.html"
    }

    @PostMapping("/productos")
    public String agregarProducto(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }


    @GetMapping("/index")
    public String index(Model model) {
        List<Producto> productos = productoRepository.findAll();

        // Imprimir la lista para depuración
        System.out.println("Número de productos encontrados: " + productos.size());

        model.addAttribute("productos", productos);
        model.addAttribute("producto", new Producto());

        return "index";  // Asegúrate de que esto coincida con el nombre de tu archivo HTML
    }
}
