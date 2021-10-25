package br.com.sigmabin.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sigmabin.model.Lixeira;
import br.com.sigmabin.repository.LixeiraRepository;

@RestController
@RequestMapping("/api/lixeira")
public class ApiLixeiraController {

	@Autowired
	private LixeiraRepository lixeiras;;
	
	@GetMapping
	public List<Lixeira> listar(){
		return this.lixeiras.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Lixeira>  get(@PathVariable Long id) {
		Optional<Lixeira> lixeira = lixeiras.findById(id);
		
		return ResponseEntity.of(lixeira);
	}
	
	@PostMapping
	@CacheEvict(value = "lixeiras", allEntries = true)
	public ResponseEntity<Lixeira> create(
			@RequestBody @Valid Lixeira lixeira,
			UriComponentsBuilder uriBuilder
			) {
		lixeiras.save(lixeira);
		
		URI uri = uriBuilder
				.path("api/lixeira/{id}")
				.buildAndExpand(lixeira.getId_lixeira())
				.toUri();
		
		return ResponseEntity.created(uri).body(lixeira);
	}
	
	@DeleteMapping("{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	@CacheEvict(value = "lixeiras", allEntries = true)
	public ResponseEntity<Void>  destroy(@PathVariable Long id) {
		Optional<Lixeira> lixeira = lixeiras.findById(id);
		
		if (lixeira.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		lixeiras.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
