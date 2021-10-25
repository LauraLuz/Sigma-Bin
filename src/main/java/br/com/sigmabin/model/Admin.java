package br.com.sigmabin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_admin")
public class Admin {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_admin;
	
	@NotBlank(message = "O complexo é obrigatório")
	@OneToOne
	private Complexo complexo;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O email é obrigatório")
	private String email;
	
	@Size(min = 10, message = "O Telefone deve conter pelo meno 10 digitos com o DDD")
	private String telefone;
	
	@NotBlank(message = "A senha é obrigatoria")
	@Size(min = 8, message = "A senha deve conter pelo menos 8 caracteres!")
	private String senha;

}
