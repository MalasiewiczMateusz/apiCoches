package com.formacionspring.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.app.entity.Modelo;
import com.formacionspring.app.service.ModeloService;

@RestController
@RequestMapping("/api")
public class ModeloController {
	
	@Autowired
	private ModeloService servicio;
	
	@GetMapping({"/modelos","/todos"})
	public List<Modelo> index(){
		return servicio.findAllModelo();
	}
	
	@GetMapping("modelos/{id}")
	public ResponseEntity<?> findMarcaById(@PathVariable Long id){
		Modelo modelo=null;
		
		Map<String,Object> response= new HashMap<>();
		
		try {
			modelo=servicio.finById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(modelo==null) {
			response.put("mensaje", "El modelo ID: "+id+" no existe en la base de datos");
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Modelo>(modelo, HttpStatus.OK);
	}
	
	@PostMapping("/modelo")
	public ResponseEntity<?>saveModelo(@RequestBody Modelo modelo){
		Modelo modeloNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			modeloNew=servicio.saveModelo(modelo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", modeloNew);
		response.put("modelo", "El modelo ha sido creado con éxito");
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/modelo/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>updateModelo(@RequestBody Modelo modelo,@PathVariable Long id){
		
		Modelo modeloUpdate=servicio.finById(id);
		
		Map<String, Object> response = new HashMap<>();
		

		if (modeloUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar la marca con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			modeloUpdate.setId(id);
			modeloUpdate.setNombre(modelo.getNombre());
			
			servicio.saveModelo(modeloUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Modelo", modeloUpdate);
		response.put("mensaje", "El modelo ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/modelo/{id}")
	public ResponseEntity<?> deleteModelo(@PathVariable Long id) {

		Modelo modeloDelete = servicio.finById(id);
		Map<String, Object> response = new HashMap<>();

		if (modeloDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar el modelo con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			servicio.deleteModelo(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("modelo", modeloDelete);
		response.put("mensaje", "El modelo ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}


}
