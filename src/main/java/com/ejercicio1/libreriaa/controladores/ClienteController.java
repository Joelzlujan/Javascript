
package com.ejercicio1.libreriaa.controladores;

import com.ejercicio1.libreriaa.entidades.Cliente;
import com.ejercicio1.libreriaa.errores.ErrorServicio;
import com.ejercicio1.libreriaa.servicio.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/guardarCliente")
    public String guardarCliente(){
        return "guardarCliente.html";
    }
    @PostMapping("/guardarCliente")
    public String registroCliente (ModelMap modelo,@RequestParam Long documento,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String telefono){
        try{
            clienteServicio.crearCliente(documento, nombre, apellido, telefono);
            modelo.put("exito","Registro Exitoso");
            return "guardarCliente.html";
        } catch (Exception e){
            modelo.put("error",e.getMessage());
            return "guardarCliente";
        }
    }
    @GetMapping("/modificarCliente/{id}")
    public String modificarCliente(@PathVariable String id, ModelMap modelo){
        modelo.put("cliente", clienteServicio.buscarPorId(id));
        return "modificarCliente.html";       
    }
    @PostMapping("/modificarCliente/{id}")
    public String modificarCliente(@PathVariable String id,ModelMap modelo,@RequestParam String nombre,@RequestParam String apellido,@RequestParam Long documento,@RequestParam String telefono){
        try{
            clienteServicio.modificarCliente(id, documento, nombre, apellido, telefono);
            modelo.put("exito","Modificacion Exitosa");
            modelo.put("cliente", clienteServicio.buscarPorId(id));
            return "redirect:/cliente/listarClientes";
        } catch (Exception e){
            modelo.put("error", e.getMessage());
            modelo.put("cliente", clienteServicio.buscarPorId(id));
            return "modificarCliente.html";
        }
    }
    @GetMapping("/listarClientes")
    public String listarClientes (ModelMap modelo)throws ErrorServicio{
        List<Cliente> clientesLista = clienteServicio.listarClientes();
        modelo.addAttribute("clientes",clientesLista);
        return "listarClientes";
    }
    @GetMapping("/alta/{id}")
    public String alta(@PathVariable String id){
        try{
            clienteServicio.darAltaCliente(id);
            return "redirect:/cliente/listarClientes";
        }catch (Exception e) {
            return "redirect:/";
        }
    }
        @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id){
        try{
            clienteServicio.darBajaCliente(id);
            return "redirect:/cliente/listarClientes";
        }catch (Exception e) {
            return "redirect:/";
        }
    }

}
