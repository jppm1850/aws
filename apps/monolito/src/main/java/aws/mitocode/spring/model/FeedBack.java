package aws.mitocode.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedbacks")
public class FeedBack implements Serializable {

	private static final long serialVersionUID = -1725563866279171711L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "mensaje", nullable = false)
	private String mensaje;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_problema", nullable = false)
	private Problema problema;

	@Column(name = "id_usuario", nullable = true)
	private String idUsuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}
}
