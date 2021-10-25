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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sigmabin.model.Complexo;
import br.com.sigmabin.model.Usuario;
import br.com.sigmabin.repository.ComplexoRepository;
import br.com.sigmabin.repository.UsuarioRepository;


@RestController
@RequestMapping("/api/usuario")
public class ApiUsuarioController {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private ComplexoRepository complexoRep;
	
	@GetMapping("/complexo/{id}")
	public ResponseEntity<Complexo> getComplexo(@PathVariable Long id) {
		Optional<Complexo> complexo = complexoRep.findById(id);
		
		return ResponseEntity.of(complexo);
	}
	
	@GetMapping
	public List<Usuario> listar(){
		return this.repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Usuario>  get(@PathVariable Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		
		return ResponseEntity.of(usuario);
	}
	
	@PostMapping
	@CacheEvict(value = "usuarios", allEntries = true)
	public ResponseEntity<Usuario> create(
			@RequestBody @Valid Usuario usuario,
			UriComponentsBuilder uriBuilder
			) {
		repository.save(usuario);
		
		URI uri = uriBuilder
				.path("api/usuario/{id}")
				.buildAndExpand(usuario.getId_usuario())
				.toUri();
		
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@DeleteMapping("{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	@CacheEvict(value = "usuarios", allEntries = true)
	public ResponseEntity<Void>  destroy(@PathVariable Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		
		if (usuario.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "usuarios", allEntries = true)
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario newUsuario) {
		Optional<Usuario> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		Usuario usuario = optional.get();
		usuario.setNome(newUsuario.getNome());
		usuario.setEmail(newUsuario.getEmail());
		usuario.setSenha(newUsuario.getSenha());
		usuario.setComplexo(newUsuario.getComplexo());
		usuario.setTelefone(newUsuario.getTelefone());
		
		repository.save(usuario);
		return ResponseEntity.ok(usuario);
	}
	
}
