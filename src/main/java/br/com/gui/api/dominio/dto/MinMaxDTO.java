package br.com.gui.api.dominio.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MinMaxDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<VitoriaProdutorDTO> min = new ArrayList<>();
	private List<VitoriaProdutorDTO> max = new ArrayList<>();

	public MinMaxDTO() {

	}

	public List<VitoriaProdutorDTO> getMin() {
		return min;
	}

	public void setMin(List<VitoriaProdutorDTO> min) {
		this.min = min;
	}

	public List<VitoriaProdutorDTO> getMax() {
		return max;
	}

	public void setMax(List<VitoriaProdutorDTO> max) {
		this.max = max;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MinMaxDTO [min=");
		builder.append(min);
		builder.append(", max=");
		builder.append(max);
		builder.append("]");
		return builder.toString();
	}
	
	

}
