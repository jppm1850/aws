package aws.mitocode.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "negocios")
public class Negocio implements Serializable {
	
	private static final long serialVersionUID = 2445247993956960711L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_negocio", nullable = false)
	private TipoNegocio tipoNegocio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoNegocio getTipoNegocio() {
		return tipoNegocio;
	}

	public void setTipoNegocio(TipoNegocio tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}

}
