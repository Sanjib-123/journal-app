package com.example.springBootFirst.Repository;

import com.example.springBootFirst.Entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepo extends MongoRepository<Journal, ObjectId> {

}
