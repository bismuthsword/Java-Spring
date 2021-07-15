package com.treasure.metadata.model.pg;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.cassandra.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class T1 {

	@Id
	@Column(value="c1")
	private int c1;

	@Column(value="c2")
	private String c2;

}
