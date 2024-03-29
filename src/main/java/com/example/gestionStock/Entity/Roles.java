package com.example.gestionStock.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="roles")
public class Roles extends AbstractEntity {

	@Column(name ="rolename")
	private String roleName;
	
	
	@Column(name="idEntreprise")
	private Integer idEntreprise;
	
	@ManyToOne
	@JoinColumn(name="iduser")
	private Users user;
	
	
}

