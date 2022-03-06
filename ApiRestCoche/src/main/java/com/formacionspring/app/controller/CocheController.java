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

import com.formacionspring.app.entity.Coche;
import com.formacionspring.app.service.CocheService;

@RestController
@RequestMapping("/api")
public class CocheController {
	
	@Autowired
	private CocheService servicio;
	
	@GetMapping({"/coches","/todos"})
	public List<Coche> index(){
		return servicio.findAllCoche();
	}
	
	@GetMapping("coches/{id}")
	public ResponseEntity<?> findCocheById(@PathVariable Long id){
		Coche coche=null;
		
		Map<String,Object> response= new HashMap<>();
		
		try {
			coche=servicio.finById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(coche==null) {
			response.put("mensaje", "El coche ID: "+id+" no existe en la base de datos");
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Coche>(coche, HttpStatus.OK);
	}
	
	@PostMapping("/coche")
	public ResponseEntity<?>saveCoche(@RequestBody Coche coche){
		Coche cocheNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			cocheNew=servicio.saveCoche(coche);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", cocheNew);
		response.put("coche", "El coche ha sido creado con éxito");
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/coche/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>updateCoche(@RequestBody Coche coche,@PathVariable Long id){
		
		Coche cocheUpdate=servicio.finById(id);
		
		Map<String, Object> response = new HashMap<>();
		

		if (cocheUpdate == null) {
			response.put("mensaje", "Error: no se puedo editar el coche con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			cocheUpdate.setId(id);
			cocheUpdate.setMarca(coche.getMarca());
			cocheUpdate.setModelo(coche.getModelo());
			cocheUpdate.setMatricula(coche.getMatricula());
			cocheUpdate.setVelocidad(coche.getVelocidad());
			cocheUpdate.setCilindrada(coche.getCilindrada());
			cocheUpdate.setColor(coche.getColor());
		
			

			servicio.saveCoche(cocheUpdate);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar un update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Coche", cocheUpdate);
		response.put("mensaje", "El departamento ha sido actualizado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/coche/{id}")
	public ResponseEntity<?> deleteCoche(@PathVariable Long id) {

		Coche cocheDelete = servicio.finById(id);
		Map<String, Object> response = new HashMap<>();

		if (cocheDelete == null) {
			response.put("mensaje", "Error: no se pudo eliminar el coche con ID: " + id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			servicio.deleteCoche(id);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar la información en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("coche", cocheDelete);
		response.put("mensaje", "El coche ha sido eliminado con éxito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
