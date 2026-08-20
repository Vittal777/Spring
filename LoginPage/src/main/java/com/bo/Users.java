package com.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints= {@UniqueConstraint(columnNames= {"email"}),@UniqueConstraint(columnNames= {"username"})})

@Data
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String email;
	String username;
	String password;
}
