package com.dev.mine.controller;

import com.dev.mine.model.*;
import com.dev.mine.repository.*;
import com.dev.mine.repository.CompteRepository;
import com.dev.mine.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartenaireRepository partenaireRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/liste")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> liste(){
        return userRepository.findAll();
    }

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    PasswordEncoder encoder;
    @PostMapping(value = "/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User addUser(@RequestBody(required = false) User u)
    {
        User user = new User(u.getName(),u.getUsername(),encoder.encode(u.getPassword()));
        user.setStatut("actif");

        //user.setRoles();
        user.setPartenaire(null);
        user.setCompte(null);

        return userRepository.save(user);
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User add(@RequestBody(required = false) PartenaireUser pu ){
        Partenaire partenaire = new Partenaire(pu.getEntreprise(),pu.getNinea(),pu.getAdresse(),pu.getEmail(),pu.getTelephone());
        partenaire.setStatut("actif");
          User user = new User(pu.getName(),pu.getUsername(),encoder.encode(pu.getPassword()));
          user.setStatut("actif");
          user.setPartenaire(partenaire);
//          Role role = new Role();
//          user.setId((long)2);
          Compte compte = new Compte();
        //randum du numéro de compte
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("ssyyyyMMddHHmm");
        Date now=new Date();
        String numcompte = formater.format(now);

          compte.setNumCompte(numcompte);
          compte.setSolde(0);
          compte.setPartenaire(partenaire);
          user.setCompte(compte);
          partenaireRepository.save(partenaire);
          compteRepository.save(compte);
          userRepository.save(user);
          return userRepository.save(user);
    }

    @PostMapping(value = "/addDepot",consumes =(MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<String> depot (@RequestBody(required = false) PartenaireUser  partenaireUser){
        Depot d =new Depot();
        d.setDateDepot(new Date());
        d.setMontant(partenaireUser.getMontant());
        d.setCompte(partenaireUser.getCompte());

        User user=userDetailsService.getUserConnect();
        d.setUser(user);

        //ajout du montant du depot sur le solde du compte
        Compte cpt= compteRepository.findByNumCompte(partenaireUser.getNumCompte()).orElseThrow();
        cpt.setSolde(cpt.getSolde()+d.getMontant());
        compteRepository.save(cpt);
        depotRepository.save(d);

        return new ResponseEntity<>("Le depot a été effectué avec succès", HttpStatus.OK);
    }

    @GetMapping("/blockuser/{id}")
    public String blocked(@PathVariable Integer id){

        User userBlock = userRepository.findOneById(id)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not find."));

        if(userBlock.getStatut().equals("ACTIF")){

            userBlock.setStatut("BLOQUE");
        }
        else {
            userBlock.setStatut("ACTIF");
        }

        userRepository.save(userBlock);

        return "User update successfully!";

    }
}