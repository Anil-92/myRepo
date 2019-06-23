package com.socgen.employee.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
public class Employee {
	@ApiModelProperty("Id of Employee")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty("First Name of Employee")
	@NotBlank
	@Setter
	@Getter
	String firstName;

	@ApiModelProperty("Last Name of Employee")
	@NotBlank
	@Setter
	@Getter
	String lastName;

	@ApiModelProperty("Gender of Employee")
	@NotBlank
	@Setter
	@Getter
	String gender;

	
	@NotBlank
	@ApiModelProperty("DOB of Employee")
	@Setter
	@Getter
	String dateOfBirth;

	@ApiModelProperty("Department of Employee")
	@NotBlank
	@Setter
	@Getter
	String department;

}
