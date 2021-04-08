package com.android.android_repo;

import com.android.android_repo.entity.Email;
import com.android.android_repo.entity.IMEI;
import com.android.android_repo.repository.EmailRepository;
import com.android.android_repo.repository.IMEIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EntityScan(basePackages = {"com.android.android_repo.entity"})
@EnableJpaRepositories(basePackages = "com.android.android_repo.repository")
public class ScanAndroidRepoApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    IMEIRepository imeiRepo;

    @Autowired
    EmailRepository emailRepository;

   /* @PostConstruct
    public void initUsers() {
        List<IMEI> imeis = Stream.of(
                new IMEI(101, "353948119627860")
        ).collect(Collectors.toList());
        imeiRepo.saveAll(imeis);
    }*/

    public static void main(String[] args) {

        SpringApplication.run(ScanAndroidRepoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Try to get IMEIs... ");
        //IMEI im=new IMEI("353948119627866","353948119627866", "Safa", "Mnassri",new Date());
        //imeiRepo.save(im);
        Email email=new Email(25, "smtp.gmail.com", "salimamnassri@gmail.com", "123654789");
        emailRepository.save(email);

        List<IMEI> list=imeiRepo.findAll();

        if(list.size() > 0){
            for(IMEI imei:list){
                System.out.println("IMEI : "+imei.getValue());
            }
        }

    }
}
