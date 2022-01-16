package com.ejercicio1.libreriaa.controladores;

import com.ejercicio1.libreriaa.entidades.Autor;
import com.ejercicio1.libreriaa.entidades.Editorial;
import com.ejercicio1.libreriaa.errores.ErrorServicio;
import com.ejercicio1.libreriaa.servicio.EditorialServicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/guardarEditorial")
    public String guardarAutor(){
        return "guardarEditorial.html";
    }
    
    @PostMapping("/guardarEditorial")
    public String registro(ModelMap modelo,@RequestParam(required=false) String nombre){
        try{
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito","Registro Exitoso");
            return "guardarEditorial.html";
        }catch (Exception e){
            modelo.put("error", e.getMessage());
            return "guardarEditorial.html";
        }
    }
    @GetMapping("/listarEditoriales")
    public String listarEditoriales(ModelMap modelo) throws ErrorServicio{
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "listarEditoriales.html";
    }
}