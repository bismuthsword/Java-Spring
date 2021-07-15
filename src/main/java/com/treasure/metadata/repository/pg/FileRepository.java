package com.treasure.metadata.repository.pg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.treasure.metadata.model.pg.File;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

	@Query("select f from File f where f.uuid = :uuid and f.trashed = false and f.attached = true")
	File findByUuid(@Param("uuid") String uuid);

}
