package com.dev.mine.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "compte")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String numCompte;


    private Integer solde;

    @JoinColumn(name = "partenaire_id",referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Partenaire partenaire;

    @OneToMany(mappedBy = "compte")
    private List<User> users;

    @OneToMany(mappedBy = "compte")
    private List <Depot> depots;

    public Compte() {
    }

    public Compte(String numCompte, Integer solde) {
        this.numCompte = numCompte;
        this.solde = solde;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
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

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }
}
