package com.example.springBootFirst.controller;

import com.example.springBootFirst.Entity.Journal;
import com.example.springBootFirst.Entity.User;
import com.example.springBootFirst.service.JournalEntryService;
import com.example.springBootFirst.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalController {

    private final JournalEntryService journalEntryService;


    private final UserService userService; 

    public JournalController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }

    @GetMapping    //("/{userName}")  @PathVariable String userName
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        List<Journal> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>("No journal entries found for the user", HttpStatus.NOT_FOUND);
    }


    @PostMapping    //("/{userName}")
    public ResponseEntity<Journal> createEntry(@RequestBody Journal journal){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(journal, userName);
            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<Journal> getJournalById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<Journal> collect =  user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)). collect(Collectors.toList());
        if (!collect.isEmpty()){
            Optional<Journal> journalEntry = journalEntryService.findById(myId);
              if (journalEntry.isPresent()){
                  return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
              }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<Journal> existingJournal = journalEntryService.findById(myId);
        if (existingJournal.isPresent()) {
            journalEntryService.deleteById(myId, userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Journal entry not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalById(
            @PathVariable ObjectId id,
            @RequestBody Journal newEntry
            ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<Journal> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()){
        Optional<Journal> journalOptional = journalEntryService.findById(id);
            if (journalOptional.isPresent()) {
                    Journal oldEntry = journalOptional.get();
                    oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
                    oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
                    journalEntryService.saveEntry(oldEntry);  // Save the updated journal entry
                    return new ResponseEntity<>(oldEntry, HttpStatus.OK);
                }

            }

        return new ResponseEntity<>("Journal entry not found", HttpStatus.NOT_FOUND);
    }
}
