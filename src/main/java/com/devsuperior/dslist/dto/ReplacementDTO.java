package com.devsuperior.dslist.dto;

public class ReplacementDTO {

	// classe para guardar e efetuar a movimentação de jogos

	private Integer sourceIndex;
	private Integer destinationPosition;
	
	public Integer getSourceIndex() {
		return sourceIndex;
	}
	
	public void setSourceIndex(Integer sourceIndex) {
		this.sourceIndex = sourceIndex;
	}
	
	public Integer getDestinationPosition() {
		return destinationPosition;
	}
	
	public void setDestinationPosition(Integer destinationPosition) {
		this.destinationPosition = destinationPosition;
	}
	
	
}
