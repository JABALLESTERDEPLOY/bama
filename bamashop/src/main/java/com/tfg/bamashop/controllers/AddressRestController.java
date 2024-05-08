package com.tfg.bamashop.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.bamashop.dto.AddressDto;
import com.tfg.bamashop.dto.AddressRequestDto;
import com.tfg.bamashop.services.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "AddressRestController", description = "AddressRestController API")
@SecurityRequirement(name = "bearerAuth")
public class AddressRestController {

	@Autowired
	AddressService addressService;

	//Resumo un poco el método, la barra significa que la cookie va estar accesible para 
    //todo el sistema y subsistema del proyecto y la segunda será la vista donde la vamos a retornar.
    //Cookie es como se crea la propia, el primer parámetro es el nombre de la misma y la segunda es 
    // lo que contiene. Ahora añadimos al Response la Cookie y retornamos la vista
    //Con la anotación CookieValue lo que hacemos es asignar el nombre para su búsqueda y le decimos con required 
    //que si no  la encuentra no pasa nada.
    //Esto no sé para que nos puede servir pero así se creean las coockies. Podemos crear un cuadro adicional para que guarde 
    //algún dato. NO sé como lo ves
    @GetMapping(value = {"/", "/index"})
    public String getAddressCookies(HttpServletResponse response,
            @CookieValue(name = "Cookie1", required = false) String lecturaCookie,
            Model objModel){
        if(lecturaCookie.equals("")) {
            Cookie objCookie = new Cookie("Cookie1", "Bienvenido");
            //Aquí se le asigna lo que quieres que dure la cookie en segundos, en este caso dura un día
            //objCookie.setMaxAge(24 * 60 * 60);
            objCookie.setMaxAge(30);
            response.addCookie(objCookie);
            return "Acceso Es la primera vez que se crea";
        }else {
        	return "Acceso Bienvenido de nuevo: " + lecturaCookie;
        }

    }
    
	@Operation(summary = "Get Addresses", operationId = "getAddresses", description = "Get Addresses")
	@GetMapping("/addresses")
	public ResponseEntity<Object> getAddresses() {

		log.info("Init getAddresses - AddressRestController");

		return ResponseEntity.ok(addressService.getAddresses());
	}

	@Operation(summary = "Get Address", operationId = "getAddress", description = "Get an address")
	@GetMapping("/addresses/{id}")
	public ResponseEntity<Object> getAddress(@PathVariable Long id) {

		log.info("Init getAddress - AddressRestController");

		return ResponseEntity.ok(addressService.getAddress(id));
	}

	@Operation(summary = "Save Address", operationId = "saveAddress", description = "Save an address")
	@PostMapping("/addresses")
	public ResponseEntity<Object> saveAddress(@RequestBody AddressRequestDto addressRequestDto) {

		log.info("Init saveAddress - AddressRestController");

		AddressDto address = addressService.saveAddress(addressRequestDto);

		return ResponseEntity.ok(address);
	}

	@Operation(summary = "Update Address", operationId = "updateAddress", description = "Update an address")
	@PutMapping("/addresses/{id}")
	public ResponseEntity<Object> updateAddress(@PathVariable Long id,
			@RequestBody AddressRequestDto addressRequestDto) {

		log.info("Init updateUser - AddressRestController");

		return ResponseEntity.ok(addressService.updateAddress(id, addressRequestDto));
	}

	@Operation(summary = "Delete Address", operationId = "deleteAddress", description = "Delete an address")
	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<Object> deleteAddress(@PathVariable Long id) {

		log.info("Init deleteAddress - AddressRestController");

		addressService.deleteAddress(id);

		return ResponseEntity.ok("Address deleted");
	}
	
	@Operation(summary = "Get Addess By Any Field ", operationId = "getAddressByAnyField", description = "Get Address by Any Field")
	@GetMapping("/addresses/by-anyField/{anyField}")
	public ResponseEntity<Object> getAddressByAnyField(@PathVariable String anyField) {

		log.info("Init getAddressByAnyField - AddressRestController");

		return ResponseEntity.ok(addressService.searchAddresses(anyField));
	}
}
