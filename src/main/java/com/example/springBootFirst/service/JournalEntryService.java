package com.example.springBootFirst.service;

import com.example.springBootFirst.Entity.Journal;
import com.example.springBootFirst.Entity.User;
import com.example.springBootFirst.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    private final UserService userService;

    private final JournalEntryRepo journalEntryRepository;

    public JournalEntryService(UserService userService, JournalEntryRepo journalEntryRepository) {
        this.userService = userService;
        this.journalEntryRepository = journalEntryRepository;
    }

    @Transactional
    public void saveEntry(Journal journal, String userName ){
        User user = userService.findByUserName(userName);
          journal.setDate(LocalDateTime.now());
        Journal saved =  journalEntryRepository.save(journal);
        user.getJournalEntries().add(saved);
        userService.saveNewUser(user);
    }
    public void saveEntry(Journal journal ){
        journalEntryRepository.save(journal);
    }

    public List<Journal> getAll(){
        return journalEntryRepository.findAll();

    }

    public Optional<Journal> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String userName) {
        try {
            User user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
    }
//
//    public List<Journal> findByUserName(){
//
//    }
}
