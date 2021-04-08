package com.android.android_repo.controller;

import com.android.android_repo.entity.Email;
import com.android.android_repo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EmailController {

    @Autowired
    EmailRepository emailRepository;


    @GetMapping("/getFirstMail")
    public Email getMailServer(){
        try{
            if(emailRepository.findAll() != null){
                return emailRepository.findAll().get(0);
            }

        }catch (Exception e){
            System.out.println("Error while getting mails : "+e.getMessage());
            return null;
        }
        return null;
    }

    @PutMapping("/updateMailServer")
    public int updateMailServer(@RequestBody Email email){
        try {
            emailRepository.save(email);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @DeleteMapping("/deleteMailServer/{id}")
    public int deleteMailServer(@PathVariable(name = "id") Integer server_id){
        try {
            emailRepository.deleteById(server_id);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
