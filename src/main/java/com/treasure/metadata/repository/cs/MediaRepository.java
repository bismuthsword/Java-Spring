package com.treasure.metadata.repository.cs;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.treasure.metadata.model.cs.Media;

@Repository
public interface MediaRepository extends CassandraRepository<Media, String> {

	@Query(value="SELECT file_uuid as fileUuid, metadata FROM media WHERE file_uuid = :fileUuid")
	Media findByFileUuid(String fileUuid);

	@Query(value="SELECT * FROM media WHERE file_uuid IN (:fileUuids)")
	List<Media> findByFileUuids(String []fileUuids);

}
