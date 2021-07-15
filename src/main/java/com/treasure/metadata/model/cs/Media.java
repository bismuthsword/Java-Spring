package com.treasure.metadata.model.cs;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(value = "media")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Media {

	@Id
	@Column(value = "file_uuid")
	private String fileUuid;

	@Column(value = "metadata")
	private String metadata;

}
