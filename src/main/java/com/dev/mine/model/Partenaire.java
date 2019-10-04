package com.dev.mine.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.metadata.CascadableDescriptor;
import java.util.List;

@Entity
@Table(name = "partenaire")
public class Partenaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String entreprise;
    private String ninea;
    private String email;
    private String adresse;
    private String telephone;
    private String statut;

    @OneToMany(mappedBy = "partenaire")
    private List<User> users;

    @OneToMany(mappedBy = "partenaire")
    private List<Compte> comptes;

    public Partenaire() {
    }

    public Partenaire(String entreprise, String ninea, String email, String adresse, String telephone) {
        this.entreprise = entreprise;
        this.ninea = ninea;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
