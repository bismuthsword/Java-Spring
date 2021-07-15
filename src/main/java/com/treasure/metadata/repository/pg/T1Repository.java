package com.treasure.metadata.repository.pg;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treasure.metadata.model.pg.T1;

@Repository
public interface T1Repository extends JpaRepository<T1, Integer> {

	List<T1> findByC1(int c1);
	List<T1> findByC2(String c2);
	List<T1> findAll();

}
