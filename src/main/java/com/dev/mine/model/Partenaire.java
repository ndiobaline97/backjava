package com.dev.mine.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Partenaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    private String entreprise;
    @Column(length = 10)
    private String ninea;
    @Column(length = 20)
    private String adresse;
    @Column (length = 20)
    private String email;
    @Column(length = 15)
    private String telephone;
    @Column(length = 20)
    private String statut;
    @OneToMany(mappedBy = "user")

    private List <User> users;
    private List <User> getUsers() { return users;}
    public void setUsers(List<User> employes) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
