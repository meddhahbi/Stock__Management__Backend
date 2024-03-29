package com.example.gestionStock.Dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.ComponentScan;

import com.example.gestionStock.Entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class UsersDto {

	private Integer id;

	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	
	private Instant dateDeNaissance;
	
	
	private String motDePasse;
	
	
	private AdresseDto adresse;
	
	
	private String photo;
	
	

	private EntrepriseDto entreprise;
	
	
	
	//private Integer idEntreprise;


	@JsonIgnore
	private List<RolesDto>roles;

	
	public static UsersDto fromEntity(Users user) {
		
		if(user == null) {
			return null;
		}
		
		return UsersDto.builder()
				.id(user.getId())
				.nom(user.getNom())
				.prenom(user.getPrenom())
				.email(user.getEmail())
				.motDePasse(user.getMotDePasse())
				.dateDeNaissance(user.getDateDeNaissance())
				.adresse(AdresseDto.fromEntity(user.getAdresse()))
				.photo(user.getPhoto())
				.entreprise(EntrepriseDto.fromEntity(user.getEntreprise()))
				.roles(
						user.getRoles() != null ?
						user.getRoles().stream()
						.map(RolesDto::fromEntity)
						.collect(Collectors.toList()) : null
						).build();
				
		
		
	}
	
	
	
	public static Users toEntity(UsersDto userDto) {
		
		if(userDto == null) {
			
			return null;
		}
		
		Users user = new Users();
		
		user.setId(userDto.getId());
		user.setNom(userDto.getNom());
		user.setPrenom(userDto.getPrenom());
		user.setEmail(userDto.getEmail());
		user.setDateDeNaissance(userDto.getDateDeNaissance());
		user.setMotDePasse(userDto.getMotDePasse());
		user.setAdresse(AdresseDto.toEntity(userDto.getAdresse()));
		user.setPhoto(userDto.getPhoto());
		user.setEntreprise(EntrepriseDto.toEntity(userDto.getEntreprise()));
		
		return user;
		
	}
	
	
}
