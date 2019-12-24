import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes} from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PacijentHomepageComponent } from './pacijent-homepage/pacijent-homepage.component';
import { LoadingSpinnerComponent} from '../../shared/loading-spinner/loading-spinner.component';
import { AdminKlinikeHomepageComponent } from './admin-klinike-homepage/admin-klinike-homepage.component';
import { LekarComponent } from './lekar/lekar.component';
import { MedicinskaSestraComponent } from './medicinska-sestra/medicinska-sestra.component';
import { AdminKcComponent } from './admin-kc/admin-kc.component';
import { RegistracijaAdminaKlinikeComponent } from './registracija-admina-klinike/registracija-admina-klinike.component';
import { RegistracijaKlinikeComponent } from './registracija-klinike/registracija-klinike.component';

import { DefinisanjePregledaComponent } from './definisanje-pregleda/definisanje-pregleda.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TimepickerModule } from 'ngx-bootstrap';
import { KlinikaDetaljnijeComponent } from './klinika/klinika-detaljnije/klinika-detaljnije.component';
import { SaleComponent } from './sale/sale.component';
import { DodajSaluComponent } from './sale/dodaj-salu/dodaj-salu.component';
import { DodavanjeTipaPregledaComponent } from './definisanje-pregleda/dodavanje-tipa-pregleda/dodavanje-tipa-pregleda.component';
import { KlinikaServiceComponent } from './services/klinika-service/klinika-service.component';
import { AngularDateTimePickerModule } from 'angular2-datetimepicker';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { MaterialModule } from './material/material.module';
import { SalaDialogComponent } from './dialozi/sala-dialog/sala-dialog.component';
import { NavbarComponent } from './admin-klinike/navbar/navbar.component';



const appRouts: Routes = [
  { path: 'login', component : LoginComponent },
  { path: 'registration', component : RegistrationComponent },
  { path: 'home', component : PacijentHomepageComponent },
  { path: 'adminklinike', component: AdminKlinikeHomepageComponent},
  { path: 'adminklinike/defpregleda', component: DefinisanjePregledaComponent},
  { path: 'adminklinike/klinika', component: KlinikaDetaljnijeComponent},
  { path: 'adminklinike/sale', component: SaleComponent},
  { path: 'adminklinike/dodavanjetipa', component: DodavanjeTipaPregledaComponent},
  { path: 'adminklinike/dodajsalu', component: DodajSaluComponent},
  { path: 'lekar', component: LekarComponent },
  { path: 'medsestra', component: MedicinskaSestraComponent },
  { path: 'adminkc', component : AdminKcComponent},
  { path: 'registracija-admina', component : RegistracijaAdminaKlinikeComponent},
  { path: 'registracija-klinike', component : RegistracijaKlinikeComponent},
  { path: '', redirectTo : '/login', pathMatch : 'full' },
  { path: '**', component : PageNotFoundComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    PageNotFoundComponent,
    PacijentHomepageComponent,
    LoadingSpinnerComponent,
    AdminKlinikeHomepageComponent,
    LekarComponent,
    MedicinskaSestraComponent,
    AdminKcComponent,
    RegistracijaAdminaKlinikeComponent,
    RegistracijaKlinikeComponent,
    DefinisanjePregledaComponent,
    KlinikaDetaljnijeComponent,
    SaleComponent,
    DodajSaluComponent,
    DodavanjeTipaPregledaComponent,
    KlinikaServiceComponent,
    SalaDialogComponent,
    NavbarComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    BsDatepickerModule.forRoot(),
    TimepickerModule.forRoot(),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    AngularDateTimePickerModule,
    MaterialModule,
    RouterModule.forRoot(appRouts)
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [SalaDialogComponent]
})
export class AppModule { }
