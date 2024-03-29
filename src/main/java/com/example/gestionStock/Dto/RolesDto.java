package com.example.gestionStock.Dto;

import org.springframework.context.annotation.ComponentScan;

import com.example.gestionStock.Entity.Roles;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class RolesDto {
	
	private Integer id;

	
	private String roleName;
	

	private UsersDto user;
	
	
	private Integer idEntreprise;
	
	
	public static RolesDto fromEntity(Roles roles) {
		
		
		if(roles == null) {
			return null;
		}
		
		return RolesDto.builder()
				.id(roles.getId())
				.roleName(roles.getRoleName())
				.user(UsersDto.fromEntity(roles.getUser()))
				.idEntreprise(roles.getIdEntreprise())
				.build();
				
		
	}
	
	
	
	
	public static Roles toEntity(RolesDto roleDto) {
		
		
		if(roleDto == null) {
			
			return null;
		}
		
		Roles role = new Roles();
		
		role.setId(roleDto.getId());
		role.setRoleName(roleDto.getRoleName());
		role.setUser(UsersDto.toEntity(roleDto.getUser()));
		role.setIdEntreprise(roleDto.getIdEntreprise());
		
		return role;
	}
	
}
