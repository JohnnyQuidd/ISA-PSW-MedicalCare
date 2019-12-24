import { Component, OnInit } from '@angular/core';
import { KlinikaServiceComponent } from 'src/app/services/klinika-service/klinika-service.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatDialog, MatSnackBar } from '@angular/material';
import { SalaDialogComponent } from 'src/app/dialozi/sala-dialog/sala-dialog.component';


@Component({
  selector: 'app-klinika-detaljnije',
  templateUrl: './klinika-detaljnije.component.html',
  styleUrls: ['./klinika-detaljnije.component.css']
})
export class KlinikaDetaljnijeComponent implements OnInit {

  private displayColumns: string[] = ['Broj Sale', 'Pocetak termina', 'Kraj termina', 'Akcije'];
  private lekariColumns: string[] = ['Ime', 'Prezime', 'Email', 'Akcije'];
  private preglediColumns: string[] = ['Broj Sale', 'Pocetak pregleda', 'Kraj pregleda', 'Lekar', 'Akcije'];
  private pregledi: Array<Pregled>;
  private klinika: KlinikaDTO = {
    id: 0,
    naziv: '',
    opis: '',
    adresa: ''
  };
  private klinikaStara: KlinikaDTO;
  private sale: Array<SalePretraga>;
  lekari: Array<Lekar>;
  // tslint:disable-next-line: max-line-length
  constructor(private dataService: KlinikaServiceComponent, private http: HttpClient, private salaDialog: MatDialog, private snackBar: MatSnackBar) {
    if (this.dataService.getData() !== undefined) {
      this.klinika = this.dataService.getData();
      this.klinikaStara = this.klinika;
    }

    // Metode
    this.getLekareInitialy();
    this.getSaleInitialy();
    this.getPregledeInitialy();
   }

  ngOnInit() {
  }

  async isInvalid() {
    // tslint:disable-next-line: max-line-length
    return (this.klinika.naziv === this.klinikaStara.naziv) && (this.klinika.opis === this.klinikaStara.opis) && (this.klinika.adresa === this.klinikaStara.adresa);
  }
  async getLekareInitialy() {
    const apiEndpoint = 'http://localhost:8080/lekari/' + this.klinika.id;

    this.http.get(apiEndpoint,
      {responseType: 'json'}).subscribe((data) => {
        this.lekari = data as Array<Lekar>;
      }, err => {
        console.log('Greska pri pribavljanju lekara: ');
        console.log(err);
      });
  }

  async getPregledeInitialy() {
    const apiEndpoint = 'http://localhost:8080/pregledi/' + this.klinika.id;

    this.http.get(apiEndpoint,
      {responseType: 'json'}).subscribe((data) => {
        this.pregledi = data as Array<Pregled>;
      }, err => {
        console.log('Greska pri pribavljanju lekara: ');
        console.log(err);
      });
  }



  async getSaleInitialy() {
    const apiEndpoint = 'http://localhost:8080/sale/' + this.klinika.id;
    console.log(apiEndpoint);

    this.http.get(apiEndpoint,
      {responseType: 'json'}).subscribe((data) => {
        this.sale = data as Array<SalePretraga>;
      }, err => {
        console.log('Greska pri pribavljanju sala: ');
        console.log(err);
      });
  }

  async onSubmit() {
    const apiEndpoint = 'http://localhost:8080/klinika';

    this.http.put(apiEndpoint, this.klinika, {responseType: 'text'}).subscribe((data) => {
    }, err => {
      console.log('Greska');
      console.log(err);
    });

    console.log(this.klinika);
  }

  async obrisiSalu(sala) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: sala,
    };
    const apiEndpoint = 'http://localhost:8080/sale';
    this.http.delete(apiEndpoint, options).subscribe((data) => {
      console.log('Uspenso brisanje klinike');
      this.getSaleInitialy();
    }, err => {
      console.log(err);
    });
  }

  async openDialog(sala) {
    const odgovor = this.salaDialog.open(SalaDialogComponent);
    odgovor.afterClosed().subscribe(result => {
      if (result === 'true') {
        this.obrisiSalu(sala);
        this.snackBar.open('Sala izbrisana', 'X', {duration: 2000});
      }
    });
  }

  async openSnackBar(message, action){
    this.snackBar.open(message, action);
  }
}

export interface KlinikaDTO {
  id: number;
  naziv: string;
  opis: string;
  adresa: string;
}

export interface Lekar {
  id: number;
  ime: string;
  prezime: string;
  email: string;
}

export interface SalePretraga {
  brojSale: number;
  pocetakTermina: string;
  krajTermina: string;
}

export interface Pregled {
  datumVreme: Date;
  trajanjePregleda: number;
  cena: number;
  popust: number;
  sala: number;
  tipPregleda: string;
  lekar: number;
  lekarImeIPrezime: string;
  pocetakTermina: string;
  krajTermina: string;
}

