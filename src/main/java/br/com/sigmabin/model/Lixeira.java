package br.com.sigmabin.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_lixeira")
public class Lixeira {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_lixeira;
	
	@NotBlank(message = "O endereco é obrigatório")
	@OneToOne
	private Endereco endereco;
	
	@NotBlank(message = "O status da lixeira é obrigatorio")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@NotBlank(message = "O complexo é obrigatório")
	@ManyToOne
	private Complexo complexo;
	
	@NotBlank(message = "A capacidade é obrigatório")
	private String capacidade;
	
	@NotBlank(message = "A capacidade é obrigatório")
	@Min(value = 0)
	@Max(value = 100)
	private double nivelResiduo;
	
	@NotBlank(message = "O tipo de residuo é obrigatorio")
	@Enumerated(EnumType.STRING)
	private TipoResiduo tipoResiduo;
}
