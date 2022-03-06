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

import com.formacionspring.app.entity.Marca;
import com.formacionspring.app.service.MarcaServicio;

@RestController
@RequestMapping("/api")
public class MarcaController {
	
	@Autowired
	 MarcaServicio servicio;
	
	
	@GetMapping({"/marcas","/todos"})
	public List<Marca> index(){
		return servicio.findAllMarca();
	}
	
	@GetMapping("marcas/{id}")
	public ResponseEntity<?> findMarcaById(@PathVariable Long id){
		Marca marca=null;
		
		Map<String,Object> response= new HashMap<>();
		
		try {
			marca=servicio.finById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(marca==null) {
			response.put("mensaje", "El marca ID: "+id+" no existe en la base de datos");
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Marca>(marca, HttpStatus.OK);
	}
	
	@PostMapping("/coche")
	public ResponseEntity<?>saveMarca(@RequestBody Marca marca){
		Marca marcaNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			marcaNew=servicio.saveCoche(marca);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", marcaNew);
		response.put("marca", "La marca ha sido creado con éxito");
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/marca/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>updateMarca(@RequestBody Marca marca,@PathVariable Long id){
		
		Marca marcaUpdate=servicio.finById(id);
		
		Map<String, Object> response = new HashMap<>();
		

		if (marcaUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar el coche con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			marcaUpdate.setId(id);
			marcaUpdate.setNombre(marca.getNombre());
			
			servicio.saveCoche(marcaUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Marca", marcaUpdate);
		response.put("mensaje", "La marca ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/marca/{id}")
	public ResponseEntity<?> deleteMarca(@PathVariable Long id) {

		Marca marcaDelete = servicio.finById(id);
		Map<String, Object> response = new HashMap<>();

		if (marcaDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar la marca con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			servicio.deleteMarca(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("marca", marcaDelete);
		response.put("mensaje", "La marca ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}


}
