import { CreditCardI } from './../../../interfaces/CreditCardI';
import { DonationI } from './../../../interfaces/DonationI';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DonationServiceService } from '../../../services/donation-service.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {
  active = 1;
  page = 1;
  pageSize = 5;
  donationList = [] as any;
  donationListT = [] as any;
  dateFilter = {} as any;
  dateFilterEnd = {} as any;
  countryList = [] as [];
  companyList = [] as [];
  selectedCountry: string ="";
  selectedCompany: string = "";
  creditCardForm = new FormGroup({
    owner: new FormControl('', Validators.required),
    expiration: new FormControl('', [Validators.required, Validators.maxLength(4)]),
    number: new FormControl('', [Validators.required, Validators.minLength(16), Validators.maxLength(16) ]),
    amount: new FormControl(0.0, Validators.required),
    secret: new FormControl('', [Validators.required, Validators.maxLength(3), Validators.minLength(3)])
  });

  refreshDonations() {
    this.donationListT = this.donationList
      .map((total, i) => ({ident: i + 1, ...total}))
      .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

  constructor(private donationService: DonationServiceService,
    private router: Router) { }

  ngOnInit(): void {
    this.donationService.getCountries().subscribe(
      (resp) => {
        if(resp.code == 200) {
          if(resp.objectResponse) {
            this.countryList = resp.objectResponse;
          }
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
  }

  filterByDates() {
    let datetime = new Date(this.dateFilter.year, this.dateFilter.month - 1, this.dateFilter.day);
    let dateTimeEnd = new Date(this.dateFilterEnd.year, this.dateFilterEnd.month - 1, this.dateFilterEnd.day);
    dateTimeEnd.setHours(23);
    dateTimeEnd.setMinutes(59);
    dateTimeEnd.setSeconds(59);
    if(dateTimeEnd.getTime() > datetime.getTime()) {
      let dates = {
        start: datetime,
        end: dateTimeEnd
      };
      this.donationService.getMyDonations(dates).subscribe(
        (resp) => {
          if(resp.code == 200) {
            if(resp.objectResponse) {
              this.donationList = resp.objectResponse;
              if(this.donationList.length > 0) {
                this.refreshDonations(); 
              } else {
                Swal.fire('Oops...', "Sin registros", 'error');
              }
            } 
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
      Swal.fire('Oops...', "Rango de fechas incorrecto", 'error');
    } 
  }

  findCompanies() {
    if(this.selectedCountry.length > 0) {
      this.donationService.getCompaniesByCountry(this.selectedCountry.toString()).subscribe(
        (resp)=> {
          if(resp.code == 200) {
            if(resp.objectResponse) {
              this.companyList = resp.objectResponse;
            }
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
    }
  }
  
  onRegister(form: any) {
    if(this.selectedCountry.length > 0 && this.selectedCompany.length > 0) {
      let donation = {} as any;
      let creditCard = {} as any;
      creditCard.expiration = form.value.expiration;
      creditCard.owner = form.value.owner;
      creditCard.number = form.value.number;
      creditCard.secret = form.value.secret;
      donation.creditCard = creditCard;
      donation.amount = form.value.amount;
      donation.company = parseInt(this.selectedCompany);
      donation.country = parseInt(this.selectedCountry);
      this.donationService.donate(donation).subscribe(
        (resp) => {
          if(resp.code == 200) {
            Swal.fire('Gracias', resp.response, 'success');
          } else {
            Swal.fire(resp.code, resp.response, 'error');
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
      Swal.fire('Error', "Parametros incompletos", 'error');
    }
  }

  signOut() {
    localStorage.removeItem("ACCESS_TOKEN");
    this.router.navigate(['/home']);
  }
}
