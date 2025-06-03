package com.trbinc.billboard.repository;

import com.trbinc.billboard.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends MongoRepository<Note, String> {

}
