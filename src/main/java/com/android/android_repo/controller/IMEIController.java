package com.android.android_repo.controller;

import com.android.android_repo.entity.AuthRequest;
import com.android.android_repo.entity.IMEI;
import com.android.android_repo.entity.ImeiRequest;
import com.android.android_repo.entity.ImeiResponse;
import com.android.android_repo.repository.IMEIRepository;
import com.android.android_repo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class IMEIController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    /*@Autowired
    private CustomUserDetailsService customUserDetailsService;*/
    @Autowired
    @Qualifier("IMEIService")
    private UserDetailsService imeiDetailsService;
    @Autowired
    IMEIRepository imeiRepository;

    public static long MaxIMEIs = 20;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to rest api !!";
    }

    @PostMapping("/authenticate_mobile")
    public ImeiResponse generateTokenForMobile(@RequestBody ImeiRequest imeiRequest) throws Exception {
        System.out.println(imeiRequest.toString());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(imeiRequest.getImei_value(), imeiRequest.getImei_pass())
            );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //return new ImeiResponse();
            throw new Exception("inavalid imei!!");
        }

        String jwt= jwtUtil.generateToken(imeiRequest.getImei_value(),imeiRequest.getDay(),imeiRequest.getHour());
        UserDetails userDetails= imeiDetailsService.loadUserByUsername(imeiRequest.getImei_value());
        Boolean isTokenExpired=jwtUtil.validateToken(jwt, userDetails);

        System.out.println("Imei response : "+jwt+" isTokenExpired : "+isTokenExpired);

       return new ImeiResponse(jwt, isTokenExpired);
       // return new ImeiResponse(jwt, false);

    }


    @PostMapping("/generateTokenManually")
    public String generateTokenManually(@RequestBody ImeiRequest imeiRequest)  {
        System.out.println("imeiRequest : "+imeiRequest);
        try {
            //String codedPwd = new String(Base64.getEncoder().encode(imeiRequest.getImei_pass().getBytes()));
            //imeiRequest.setImei_pass(codedPwd);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(imeiRequest.getImei_value(), imeiRequest.getImei_pass())
            );
        } catch (Exception ex) {
            System.out.println("inavalid imei!!\n"+ex.getMessage());
            return "Cette IMEI n'est pas dans la base!\nVeuillez l'ajouter avant de générer son token";
        }

        String jwt= jwtUtil.generateToken(imeiRequest.getImei_value(), imeiRequest.getDay(), imeiRequest.getHour());

        System.out.println("generated token : "+jwt);
        return jwt;
    }

    @GetMapping("/IMEIs")
    public List<IMEI> getImeis(){
        return imeiRepository.findAll();
    }

    @GetMapping("/getImeiById/{id}")
    public IMEI getImeiById(@PathVariable Integer id){
        try{
            //return imeiRepository.getOne(id);
            return imeiRepository.findById(id).get();
        }catch (Exception e){
            return new IMEI();
        }
    }
    @PutMapping("/updateIMEI")
    public int updateIMEI(@RequestBody IMEI imei){
        System.out.println("Imei to modify : "+imei.toString());
        try{
            imeiRepository.save(imei);
            return 0;
        }catch (Exception e){
            System.out.println("Error whilr trying to modify Imei : "+e.getMessage());
            return -1;
        }

    }

    @PostMapping("/addNewIMEI")
    public Boolean addNewIMEI(@RequestBody IMEI imei){
        Boolean res=false;
        //String codedPwd = new String(Base64.getEncoder().encode(imei.getPassword().getBytes()));
        //imei.setPassword(codedPwd);
        // Verify if the size in the db have more than the eligible number
        if(imeiRepository.findAll().size() <= MaxIMEIs){
            imeiRepository.save(imei);
            res=true;
        }else{
            res=false;
        }
        return res;

    }

    @DeleteMapping("/deleteIMEI/{imei_id}")
    public Boolean deleteIMEI(@PathVariable Integer imei_id){
        Boolean res=false;
        try {
            imeiRepository.deleteById(imei_id);
            res=true;
        }catch (Exception exp){
            res=false;
        }

        return res;
    }



}
