import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginServiceService } from '../../../services/login-service.service';
import { UserI } from '../../../interfaces/UserI';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-register-component',
  templateUrl: './register-component.component.html',
  styleUrls: ['./register-component.component.css']
})
export class RegisterComponentComponent implements OnInit {
  notEquals: boolean = true;
  registerForm = new FormGroup({
    name: new FormControl('', Validators.required),
    lastname: new FormControl('', Validators.required),
    doc: new FormControl('', [Validators.required, Validators.maxLength(8), Validators.minLength(8)]),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    passwordR: new FormControl('', Validators.required)
  });

  constructor(private router: Router,
     private loginService: LoginServiceService) { }

  ngOnInit(): void {
  }

  verifyPassword() {
    this.notEquals = (!(this.registerForm.controls['password'].value === this.registerForm.controls['passwordR'].value) && this.registerForm.controls['passwordR'].value.length > 0);
    console.log(this.notEquals);
  }

  onRegister(form: any) {
    if(!this.notEquals) {
      let user = {} as UserI;
      user.document = form.value.doc;
      user.email = form.value.email;
      user.names = form.value.name;
      user.lastnames = form.value.lastname;
      user.password = form.value.password;
      this.loginService.register(user).subscribe( 
        (resp) => {
          if(resp.code == 200) {
            Swal.fire('Bienvenido', resp.response, 'success');
            this.router.navigate(['/home']);
          } else {
            Swal.fire('Error', resp.response, 'error');
          }
        },
        (error) => {
          if(error.status == 400) {
            Swal.fire('Oops...', error.response, 'error');
          } else if(error.name === "HttpErrorResponse") {
            Swal.fire('Oops...', "Fallo al comunicarse con el servidor", 'error');
          }
        }
      );
    } else {
      Swal.fire('Error', "La contraseña no se introdujo correctamente", 'error');
    }
  }
}
