package br.com.sigmabin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sigmabin.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNome(String nome);
	
}
