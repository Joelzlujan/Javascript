package com.ejercicio1.libreriaa.controladores;

import com.ejercicio1.libreriaa.entidades.Libro;
import com.ejercicio1.libreriaa.errores.ErrorServicio;
import com.ejercicio1.libreriaa.servicio.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroServicio libroServicio;

    @GetMapping("/guardarLibro")
    public String guardarLibro() {
        return "guardarLibro.html";
    }

    @PostMapping("/guardarLibro")
    public String registro(ModelMap modelo, @RequestParam(required=false) String titulo, @RequestParam(required=false) Long ISBN, @RequestParam(required=false) Integer anio, @RequestParam(required=false) Integer ejemplares, @RequestParam(required=false) Integer ejemplaresPrestados, @RequestParam(required=false) Integer ejemplaresRestantes, @RequestParam(required=false) String autor, @RequestParam(required=false) String editorial) {

        try {
            libroServicio.crearLibro(titulo, ISBN, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
            modelo.put("exito", "Registro Exitoso");
            return "guardarLibro";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "guardarLibro";
        }

    }

    @GetMapping("/modificarLibro/{id}")
    public String modificarLibro(@PathVariable String id,ModelMap modelo){
        modelo.put("libro",libroServicio.buscarLibroPorId(id));
        return "modificarLibro";
    }
    
    @PostMapping("/modificarLibro/{id}")
    public String modificarLibro(@PathVariable String id,ModelMap modelo,@RequestParam String titulo, @RequestParam Long ISBN, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes, @RequestParam String autor, @RequestParam String editorial) {
        try{
            libroServicio.modificarLibro(id, titulo, ISBN, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
            modelo.put("exito", "Modificación Exitosa");
            modelo.put("libro",libroServicio.buscarLibroPorId(id));
            return "modificarLibro.html";
        }catch(Exception e){
            modelo.put("error", e.getMessage());
            modelo.put("libro",libroServicio.buscarLibroPorId(id));
            return "modificarLibro";
        }
       
    }

    @GetMapping("/buscarLibro")
    public String buscarLibro(ModelMap modelo) throws ErrorServicio {

        List<Libro> librosLista = libroServicio.cargarListaLibros();
        modelo.addAttribute("libros",librosLista);
        return "buscarLibro";
    }
    
    @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id){
        try{
            libroServicio.darAltaLibro(id);
            return "redirect:/libro/buscarLibro";
        }catch (Exception e){
            return "redirect:/";
        }
    }
        @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id){
        try{
            libroServicio.darBajaLibro(id);
            return "redirect:/libro/buscarLibro";
        }catch (Exception e){
            return "redirect:/";
        }
    }
}
