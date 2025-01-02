package com.example.springBootFirst.Repository;

import com.example.springBootFirst.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntryRepo extends MongoRepository<User, ObjectId> {


    User findByUserName(String username);
}
