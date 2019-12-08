package com.groupfour.MedicalCare.Model.Pregled;

import com.groupfour.MedicalCare.Common.db.DbColumnConstants;
import com.groupfour.MedicalCare.Common.db.DbTableConstants;
import com.groupfour.MedicalCare.Model.Dokumenti.IzvestajOPregledu;
import com.groupfour.MedicalCare.Model.Klinika.Sala;
import com.groupfour.MedicalCare.Model.Osoblje.Lekar;
import com.groupfour.MedicalCare.Model.Pacijent.Pacijent;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = DbTableConstants.PREGLED)
public class Pregled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbColumnConstants.PREGLED_ID)
    private int id;
    @Column(name = DbColumnConstants.PREGLED_TERMIN)
    private LocalDateTime terminPregleda;
    @Column(name = DbColumnConstants.PREGLED_TRAJANJE)
    private String trajanjePregleda;
    @Column(name = DbColumnConstants.PREGLED_CENA)
    private int cena;
    @Column(name = DbColumnConstants.PREGLED_POPUST)
    private int popust;
    @Column(name = DbColumnConstants.PREGLED_AKTIVAN)
    private boolean aktivan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DbColumnConstants.PREGLED_SALA)
    private Sala sala;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DbColumnConstants.PREGLED_TIP_PREGLEDA)
    private TipPregleda tipPregleda;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = DbTableConstants.LEKAR_PREGLED,
            joinColumns = @JoinColumn(name = DbColumnConstants.PREGLED_ID),
            inverseJoinColumns = @JoinColumn(name = DbColumnConstants.LEKAR_ID)
    )
    private Set<Lekar> lekari = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = DbColumnConstants.PREGLED_IZVESTAJ)
    private IzvestajOPregledu izvestajOPregledu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = DbColumnConstants.PREGLED_PACIJENT)
    private Pacijent pacijent;

}