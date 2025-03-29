package tn.esprit.tpfoyer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bloc {

    public Bloc( String nomBloc, int capaciteBloc, Foyer foyer, Set<Chambre> chambres) {

        this.nomBloc = nomBloc;
        this.capaciteBloc = capaciteBloc;
        this.foyer = foyer;
        this.chambres = chambres;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idBloc;

    String nomBloc;
    long capaciteBloc;
    long rrc;


    @ManyToOne(cascade = CascadeType.ALL)
    Foyer foyer;

    @OneToMany(mappedBy = "bloc")
    @JsonIgnore
    @ToString.Exclude
    Set<Chambre> chambres = new HashSet<Chambre>();


}

