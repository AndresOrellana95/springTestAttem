import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { UserI } from '../interfaces/UserI';
import { environment } from './../../environments/environment';
import Swal from 'sweetalert2'

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  URL: string = environment.apiURL;
  
  constructor(private httpClient: HttpClient, private router: Router) { }

  register(user: UserI): Observable<any> {
    return this.httpClient.post<UserI>(`${this.URL}/register`, user)
    .pipe(tap(
      (res: any) => { }
    ));
  }

  login(credentials: any): Observable<any> {
    return this.httpClient.post<any>(`${this.URL}/login`, credentials)
    .pipe(tap(
      (res: any) => { 
        if(res.code == 200) {
          this.saveToken(res.objectResponse);
        }
      }
    ));
  }

  private saveToken(token: string): void {
    localStorage.setItem('ACCESS_TOKEN', token);
  }
}
