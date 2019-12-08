package com.groupfour.MedicalCare.Model.Klinika;

import com.groupfour.MedicalCare.Common.db.DbColumnConstants;
import com.groupfour.MedicalCare.Common.db.DbTableConstants;
import com.groupfour.MedicalCare.Model.Pregled.Pregled;
import com.groupfour.MedicalCare.Model.Zahtevi.Operacija;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = DbTableConstants.SALA)
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbColumnConstants.SALA_ID)
    private int id;
    @Column(name = DbColumnConstants.SALA_ZAUZECE)
    private boolean zauzeta = false;
    @Column(name = DbColumnConstants.SALA_POCETAK_TERMINA)
    private LocalDateTime pocetakTermina;
    @Column(name = DbColumnConstants.SALA_ZAVRSETAK_TERMINA)
    private LocalDateTime krajTermina;
    @Column(name = DbColumnConstants.SALA_BROJ_SALE)
    private int brojSale;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private Set<Operacija> operacije = new HashSet<>();

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private Set<Pregled> pregledi = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = DbTableConstants.KLINIKA_SALA,
            joinColumns = @JoinColumn(name = DbColumnConstants.SALA_ID),
            inverseJoinColumns = @JoinColumn(name = DbColumnConstants.KLINIKA_ID)
    )
    private Klinika klinika;

}
