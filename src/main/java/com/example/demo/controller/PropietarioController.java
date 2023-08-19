package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.Propietario;
import com.example.demo.service.IPropietarioService;

@Controller
@RequestMapping("/propietarios")
public class PropietarioController {
	
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

}
