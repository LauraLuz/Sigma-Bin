package br.com.sigmabin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_endereco")
public class Endereco {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_endereco;
	
	@NotBlank(message = "A rua é obrigatório")
	private String rua;
	
	@NotBlank(message = "O numero da rua é obrigatório")
    private String numero;
	
	@NotBlank(message = "O cep é obrigatório")
    private String cep;
	
	@NotBlank(message = "A cidade é obrigatório")
    private String cidade;
	
	@NotBlank(message = "O estado é obrigatório")
    private String estado;
	
	@NotBlank(message = "O Pais é obrigatório")
    private String pais;
	
}
