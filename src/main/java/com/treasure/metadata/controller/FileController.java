package com.treasure.metadata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.treasure.metadata.model.pg.File;
import com.treasure.metadata.repository.pg.FileRepository;

@RestController
public class FileController {

	@Autowired
	private FileRepository repository;

	@GetMapping("/file/{fileUuid}")
	public File getFileByUuid(@PathVariable String fileUuid) {
//		Optional<File> result = repository.findById(fileUuid);
//		if(result.isPresent()) {
//			return result.stream().collect(Collectors.toList()).get(0);
//		}
//		return null;
		return repository.findByUuid(fileUuid);
	}

}
