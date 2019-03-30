package aws.mitocode.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sedes")
public class Sede implements Serializable {

	private static final long serialVersionUID = -8686839700916248089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "latitud", nullable = true)
	private Double latitud;
	
	@Column(name = "longitud", nullable = true)
	private Double longitud;
	
	@ManyToOne
	@JoinColumn(name = "id_negocio", nullable = false)
	private Negocio negocio;
	
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
        name = "servicios", 
        joinColumns = { @JoinColumn(name = "id_sede") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_tipo_servicio") }
    )
	private List<TipoServicio> tiposServicios = new ArrayList<TipoServicio>();
	
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
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public Negocio getNegocio() {
		return negocio;
	}
	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}
	public List<TipoServicio> getTiposServicios() {
		return tiposServicios;
	}
	public void setTiposServicios(List<TipoServicio> tiposServicios) {
		this.tiposServicios = tiposServicios;
	}
}
