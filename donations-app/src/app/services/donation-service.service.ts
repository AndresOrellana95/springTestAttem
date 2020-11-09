import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { environment } from './../../environments/environment';
import { DonationI} from '../interfaces/DonationI';

@Injectable({
  providedIn: 'root'
})
export class DonationServiceService {
  
  URL: string = environment.apiURL;

  constructor(private httpClient: HttpClient, private router: Router) { }

  getCountries(): any {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'token': localStorage.getItem("ACCESS_TOKEN") || ""
      })
    };
    return this.httpClient.get<any>(`${this.URL}/donations/getCountries`, httpOptions)
    .pipe(tap(
      (res: any) => {
      },
      (err) => {
        console.log(err);
      }
    ));
  }

  getCompaniesByCountry(code: string): any {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'token': localStorage.getItem("ACCESS_TOKEN") || ""
      })
    };
    return this.httpClient.get<any>(`${this.URL}/donations/getCompanylist/` + code, httpOptions)
    .pipe(tap(
      (res: any) => {
      },
      (err) => {
        console.log(err);
      }
    ));
  }

  donate(donation: DonationI): Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'token': localStorage.getItem("ACCESS_TOKEN") || ""
      })
    };
    return this.httpClient.post<any>(`${this.URL}/donations/donate`, donation, httpOptions)
    .pipe(tap(
      (res: any) => {
      },
      (err) => {
        console.log("Error al registrar donación");
      }
    ));
  }

  getMyDonations(donations: any): any {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'token': localStorage.getItem("ACCESS_TOKEN") || ""
      })
    };
    return this.httpClient.post<any>(`${this.URL}/donations/getDonations`, donations, httpOptions)
    .pipe(tap(
      (res: any) => {
      },
      (err) => {
        console.log(err);
      }
    ));
  }
}
