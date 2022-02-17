package br.com.gui.api.dominio.dto;

import java.io.Serializable;

public class VitoriaProdutorDTO implements Serializable, Comparable<VitoriaProdutorDTO> {

	private static final long serialVersionUID = 1L;

	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;

	public VitoriaProdutorDTO() {
	}

	public VitoriaProdutorDTO(String producer, Integer interval, Integer previousWin, Integer followingWin) {
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Integer getPreviousWin() {
		return previousWin != null ? previousWin : 0;
	}

	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
		this.interval = this.getFollowingWin() - this.getPreviousWin();
	}

	public Integer getFollowingWin() {
		return followingWin != null ? followingWin : 0;
	}

	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
		this.interval = this.getFollowingWin() - this.getPreviousWin();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VitoriaProdutorDTO [producer=");
		builder.append(producer);
		builder.append(", interval=");
		builder.append(interval);
		builder.append(", previousWin=");
		builder.append(previousWin);
		builder.append(", followingWin=");
		builder.append(followingWin);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(VitoriaProdutorDTO o) {
		if (this.getInterval() > o.getInterval()) {
			return 1;
		}
		if (this.getInterval() < o.getInterval()) {
			return -1;
		}
		return 0;
	}

}
