package br.com.sigmabin.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_complexo")
public class Complexo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_complexo;
	
	@NotBlank(message = "O nome do complexo é obrigatório")
	private String nome;
	
	@NotBlank(message = "O endereco é obrigatório")
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Endereco endereco;
	
}
