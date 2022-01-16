
package com.ejercicio1.libreriaa.controladores;

import com.ejercicio1.libreriaa.entidades.Prestamo;
import com.ejercicio1.libreriaa.errores.ErrorServicio;
import com.ejercicio1.libreriaa.servicio.PrestamoServicio;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/prestamo")
public class PrestamoController {
    
    @Autowired
    private PrestamoServicio prestamoServicio;
    
    @GetMapping("/guardarPrestamo")
    public String guardarPrestamo(){
        return "guardarPrestamo.html";
    }
    
    @PostMapping("/guardarPrestamo")
    public String registro(ModelMap modelo,@RequestParam Long documento,@RequestParam String titulo,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDevolucion){
        try {
            prestamoServicio.crearPrestamo(documento, titulo, fechaDevolucion);
            modelo.put("exito","Registro Exitoso");
            return "guardarPrestamo.html";
       
        } catch (Exception e){
            modelo.put("error", e.getMessage());
            modelo.put("documento",documento);
            modelo.put("titulo",titulo);
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            modelo.put("fechaDevolucion",sfd.format(fechaDevolucion));
            return "guardarPrestamo";
        }
    }
    
    @GetMapping("/modificarPrestamo/{id}")
    public String modificarPrestamo (@PathVariable String id, ModelMap modelo){
        modelo.put("prestamo",prestamoServicio.buscarPorId(id));
        return "modificarPrestamo";
    }
    
    @PostMapping("/modificarPrestamo/{id}")
    public String modificarPrestamo (@PathVariable String id,ModelMap modelo,@RequestParam Date fechaPrestamo,@RequestParam Date fechaDevolucion,@RequestParam String libro,@RequestParam String cliente){
        try{
            prestamoServicio.modificarPrestamo(id, fechaPrestamo, fechaDevolucion, libro, cliente);
            modelo.put("exito","Modificación exitosa");
            modelo.put("cliente",prestamoServicio.buscarPorId(id));
            return "listarPrestamos.html";
            
        } catch (Exception e){
            modelo.put("error",e.getMessage());
            modelo.put("cliente",prestamoServicio.buscarPorId(id));
            return "modificarPrestamo";
        }
    }
    
    @GetMapping("/listarPrestamos")
    public String buscarPrestamo(ModelMap modelo)throws ErrorServicio{
        List<Prestamo> prestamosLista = prestamoServicio.cargarListaPrestamo();
        modelo.addAttribute("prestamos",prestamosLista);
        return "listarPrestamos";
    }
    
    @GetMapping("/alta/{id}")
    public String alta (@PathVariable String id){
        try{
            prestamoServicio.darAlta(id);
            return "redirect:/prestamo/listarPrestamos";
        } catch (Exception e){
            return "redirect:/";
        }
    }
    
        @GetMapping("/baja/{id}")
    public String baja (@PathVariable String id){
        try{
            prestamoServicio.darBaja(id);
            return "redirect:/prestamo/listarPrestamos";
        } catch (Exception e){
            return "redirect:/";
        }
    }
}
