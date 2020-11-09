import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginServiceService } from '../../../services/login-service.service';
import { Observable, BehaviorSubject } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  loginForm = new FormGroup({
    user: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(private router: Router, private loginService: LoginServiceService
    ) { }

  ngOnInit(): void {
  }

  onLogin(form: any) {
    let credentials = {
      user: form.value.user,
      password: form.value.password,
    };
    this.loginService.login(credentials).subscribe(
      (resp: any) => {
        if(resp.code == 200) {
          this.router.navigate(['/home/donations']);
        } else if(resp.code == 401) {
          Swal.fire('Oops...', resp.response, 'error');
        } else if(resp.code == 404) {
          Swal.fire('Oops...', resp.response, 'error');
        }
      },
      (error) => {
        if(error.status == 401) {
          Swal.fire('Oops...', error.response, 'error');
        } else if(error.name === "HttpErrorResponse") {
          Swal.fire('Oops...', "Fallo al comunicarse con el servidor", 'error');
        }
      }
    );
  }

  goToRegister() {
    this.router.navigate(['/home/register']);
  }
}
