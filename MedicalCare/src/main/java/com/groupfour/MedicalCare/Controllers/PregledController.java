package com.groupfour.MedicalCare.Controllers;

import com.groupfour.MedicalCare.Model.DTO.PregledDTO;
import com.groupfour.MedicalCare.Service.PregledService;
import com.groupfour.MedicalCare.Utill.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/pregledi")
public class PregledController {
    private Authorization authorization;
    String[] roles = {"pacijent", "adminklinike", "med_sestra", "lekar"};

    @Autowired
    public PregledController(Authorization authorization) {
        this.authorization = authorization;
    }

    @GetMapping
    public ResponseEntity<?> dobaviSvePregledeZaKliniku(HttpSession session) {
        String[] adminKlinike = {"adminklinike"};
        if(authorization.hasPermisson(session, adminKlinike))
        {
            return PregledService.dobaviSvePregledeZaKliniku(session);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/pacijent/{pacijentId}")
    public ResponseEntity<?> dobavliPregledeZaPacijenta(@PathVariable(value = "pacijentId") Integer pacijentId,
                                                        HttpSession session) {
        if(authorization.hasPermisson(session, roles))
        {
            return PregledService.dobaviPregledeZaPacijenta(pacijentId);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<String> kreirajNoviPregled(@RequestBody PregledDTO pregledDTO) {
        PregledService.kreirajNoviPregled(pregledDTO);
        return new ResponseEntity<>(pregledDTO.toString(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/zapocni")
    public ResponseEntity<?> zapocniNoviPregled(@RequestBody PregledDTO pregledDTO, HttpSession session) {
        if(authorization.hasPermisson(session, new String[] {"lekar"}))
        {
            return PregledService.zapocniNoviPregled(pregledDTO, session);
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
