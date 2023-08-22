package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Propietario;
import com.example.demo.service.IPropietarioService;

@Controller
@RequestMapping("/propietarios")
public class PropietarioController {
	
	//private static Logger LOG = (Logger) LogManager.getLogger();
	
	@Autowired
	private IPropietarioService iPropietarioService;
	
	//http://localhost:8080/concesionario/propietarios/buscar
	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) {
		
		List<Propietario> lista = this.iPropietarioService.buscarTodos();
		modelo.addAttribute("propietarios", lista);
		
		return "vistaListaPropietarios"; //El modelo son los propietarios
		//Modelo: Datos que gestina la vista
	}
	
	//El idPropietario es el argmento de entrada de mi metodo pedido en el navegador
	//http://localhost:8080/concesionario/propietarios/buscarPorId/1
	@GetMapping("/buscarPorId/{idPropietario}")
	public String buscarPorId(@PathVariable("idPropietario") Integer id, Model modelo) {
		
		Propietario prop = this.iPropietarioService.buscarPorId(id);
		//Con este propietario lo mando al MODELO
		//para imprimir en le navegador lo mando siempre como MODELO
		modelo.addAttribute("propietario",prop);
		
		return "vistaPropietario";
	}
	
	@PutMapping("/actualizar/{idPropietario}")
	public String actualizarPropietario(@PathVariable("idPropietario") Integer id, Propietario propietario){
		
		this.iPropietarioService.actualizar(propietario);
		return "redirect:/propietarios/buscar";
	}
	
	//Siempre String porque redirecciona a la vista
	//http://localhost:8080/consecionario/propietarios/borrar/1
	@DeleteMapping("/borrar/{idPropietario}")
	public String eliminarPorID(@PathVariable("idPropietario") Integer id) {
		this.iPropietarioService.borrarPorId(id);
		//Redirecciona a la misma lista
		return "redirect:/propietarios/buscar";
	}
	
	/*@DeleteMapping("/borrar/{idPropietario}")
    public String eliminarPorId(@PathVariable("idPropietario")Integer id ){
        this.propietarioService.remover(id);
        return "redirect:/propietarios/buscar";
    }*/
	
	@PostMapping("/guardar")
	public String insertarPropietario(Propietario propietario) {
		this.iPropietarioService.agregar(propietario);
		return "redirect:/propietarios/buscar";
	}
	
	//pagina de redireccionamiento que muestre la pagina vista propietarionuevo
	@GetMapping("/nuevo")
	public String paginaNuevoPropietario(Propietario propietario) {//siempre usar el propietario por tema de orden
		return "vistaPropietarioNuevo";
	}

}
