package dev.loopstudio.countryvoteapi.repository.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

/**
 * The type Country vote model.
 * @author Victor Morales
 */
@Entity
@Table(name = "country_vote")
public class CountryVoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotEmpty
    private String name;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @Column
    @NotEmpty
    private String country;

    public CountryVoteModel() {}

    public CountryVoteModel(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
