package com.groupfour.MedicalCare.Controllers;

import com.groupfour.MedicalCare.Model.DTO.MedSestraIzmenaPodatakaDTO;
import com.groupfour.MedicalCare.Model.DTO.OdsustvoDTO;
import com.groupfour.MedicalCare.Service.MedicinskaSestraService;
import com.groupfour.MedicalCare.Service.ReceptService;
import com.groupfour.MedicalCare.Utill.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/medsestra")
public class MedicinskaSestraController {

    ReceptService receptService;
    private Authorization authorization;
    private String[] role = {"med_sestra"};

    @Autowired
    public MedicinskaSestraController(Authorization authorization,ReceptService rService){this.authorization = authorization;
    this.receptService = rService;}

    @RequestMapping(value = "/recepti", method = RequestMethod.GET)
    public ResponseEntity<?> getRecepti(HttpSession session) {

        if (authorization.hasPermisson(session, role)) {

            return new ResponseEntity<>(receptService.getAllActive(session), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
    }


    @RequestMapping(value = "/overiRecept", method = RequestMethod.PUT)
    public ResponseEntity<?> overiRecept(@RequestBody Integer id, HttpSession session) {

        if(authorization.hasPermisson(session,role)) {
            receptService.overiRecept(id,session);
            return new ResponseEntity<String>("Recept je overen!", HttpStatus.OK);

        }else{
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/dodajOdsustvo",method = RequestMethod.POST)
    public ResponseEntity<?> dodajZahtevZaOdsustvo(@RequestBody OdsustvoDTO odsustvoDTO, HttpSession session){
        if(authorization.hasPermisson(session,role)){
            return MedicinskaSestraService.dodajNoviZahtevZaOdsustvoMedicinskeSestre(odsustvoDTO,session);
        }

        return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
    }



    @RequestMapping(value = "/azurirajPodatke",method = RequestMethod.PUT)
    public ResponseEntity<?> azuriranjePodatakaMedicinskeSestre(@RequestBody MedSestraIzmenaPodatakaDTO medSestraIzmenaPodatakaDTO, HttpSession session) {
        if(authorization.hasPermisson(session, role))
        {
            return MedicinskaSestraService.azurirajPodatkeMedicinskeSestre(medSestraIzmenaPodatakaDTO, session);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/detalji")
    public ResponseEntity<?> dobaviDetaljeOMedicinksojSestri(HttpSession session){
        if(authorization.hasPermisson(session, role))
        {
            return MedicinskaSestraService.detaljiOMedicinskojSestri(session);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
    @GetMapping(value = "/odsusva")
    public ResponseEntity<?> getOdsusvaZaRadniKalendar(HttpSession session){
        if(authorization.hasPermisson(session,role))
        {
            return MedicinskaSestraService.odsustvaZaRadniKalendar(session);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
