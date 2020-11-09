import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuardService } from '../../services/auth-guard.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponentComponent } from './home-component/home-component.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { RegisterComponentComponent } from './register-component/register-component.component';
import { LoginServiceService } from './../../services/login-service.service';
import { DonationServiceService } from './../../services/donation-service.service';


@NgModule({
    declarations: [
        HomeComponentComponent,
        LoginComponentComponent,
        RegisterComponentComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        HomeRoutingModule,
        NgbModule
    ],
    providers: [
        AuthGuardService,
        LoginServiceService,
        DonationServiceService
    ]
})

export class HomeModule {}
