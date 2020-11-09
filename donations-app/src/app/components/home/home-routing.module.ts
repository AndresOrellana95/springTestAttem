import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponentComponent } from './home-component/home-component.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { RegisterComponentComponent } from './register-component/register-component.component';
import { AuthGuardService as AuthGuard } from '../../services/auth-guard.service';

const routes: Routes = [
    { path: '', component: LoginComponentComponent },
    { path: 'register', component: RegisterComponentComponent },
    { path: 'donations', canActivate: [AuthGuard],data:{role:[2]}, component: HomeComponentComponent },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule {}
