package br.com.gui.api.dominio.dto;

public class TesteDTO {
	private Integer id;
	private String nome;
	private Integer ano1;
	private Integer ano2;
	private Integer interval;
	
	public TesteDTO(Integer id, String nome, Integer ano1, Integer ano2) {
		this.id = id;
		this.nome = nome;
		this.ano1 = ano1;
		this.ano2 = ano2;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getAno1() {
		return ano1;
	}
	public void setAno1(Integer ano1) {
		this.ano1 = ano1;
		this.interval = getAno2() - getAno1();
	}
	public Integer getAno2() {
		return ano2;
	}
	public void setAno2(Integer ano2) {
		this.ano2 = ano2;
		this.interval = getAno2() - getAno1();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TesteDTO other = (TesteDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TesteDTO [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", ano1=");
		builder.append(ano1);
		builder.append(", ano2=");
		builder.append(ano2);
		builder.append(", interval=");
		builder.append(interval);
		builder.append("]");
		return builder.toString();
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	
}