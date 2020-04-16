package com.test.proj.rickandmorty.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "characters")
public class Characters {

    @Id
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location origin;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Episode> episode = new HashSet<>();

    private String url;
    private String created;

}
