import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, CanActivate, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators'; 
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {
  authSubject = new BehaviorSubject(false);
  storedToken: string;
  URL_ENDPOINT = environment.apiURL + "/validateToken";

  constructor(public router: Router, private httpClient: HttpClient) {}

  async canActivate(route: ActivatedRouteSnapshot,): Promise<boolean> {
    let flag = false;
    await this.isAuthenticated().toPromise().then( 
      (rep) => {
        if(rep.code == 200) {
          let decoded = {} as any;
          decoded = jwt_decode(localStorage.getItem("ACCESS_TOKEN"));
          if(decoded.level) {
            if(route.data.role && route.data.role.indexOf(decoded.level) === -1) {
              this.router.navigate(['/home']);
              return false;
            } 
            flag = true;
          } else {
            flag = false;
          }
        } else {
          flag = false;
          console.log("Sesión expirada");
        } 
      },
      (error) => {
        this.router.navigate(['/home']);
      }
    );
    if(!flag) {
      this.router.navigate(['/home']);
    }
    return flag;
  }

  public isAuthenticated(): any {
    let token = localStorage.getItem("ACCESS_TOKEN") || "";
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'token': token
      })
    };
    return this.httpClient.get(`${this.URL_ENDPOINT}`, httpOptions)
        .pipe(tap());
  }
}
