package com.inf1.app.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="coeff_log")
@Getter
@Setter
@ToString
public class CoeffLOG extends Coefficient {
	
	@NotNull
	@Column
	private double coeff;
	
	public CoeffLOG() {
		super();
	}

	
}
