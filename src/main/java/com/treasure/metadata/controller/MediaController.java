package com.treasure.metadata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.treasure.metadata.model.cs.Media;
import com.treasure.metadata.repository.cs.MediaRepository;

@RestController
public class MediaController {

	@Autowired
	private MediaRepository repository;

	@GetMapping("/metadata/{fileUuid}")
	public Media getMetadataByFileUuid(@PathVariable String fileUuid) {
		return repository.findByFileUuid(fileUuid);
	}

	@PostMapping("/metadata")
	public int saveMetadata(@RequestBody Media media) {
		try {
			repository.save(media);			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
