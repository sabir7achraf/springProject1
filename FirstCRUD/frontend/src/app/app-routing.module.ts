import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {UsersComponent} from "./users/users.component";
import {PaymentsComponent} from "./payments/payments.component";
import {AdminTemplateComponent} from "./admin-template/admin-template.component";
import {Authguard} from "./guard/authguard.guard";
import {AuthorizationGuardService} from "./guard/authorization-guard.service";
import {NewUserComponent} from "./new-user/new-user.component";
import {NewPaymentComponent} from "./new-payment/new-payment.component";

const routes: Routes = [
  {path: '' , component:LoginComponent},
  {path: 'login' , component:LoginComponent},
  {path: 'admin' , component:AdminTemplateComponent,
    canActivate : [Authguard],
    children:[
    {path: 'home' , component:HomeComponent},
    {path: 'users' , component:UsersComponent},
    {path: 'payments' , component:PaymentsComponent
  },
{path:'newUser',component:NewUserComponent},
  {path:'newPayment',component:NewPaymentComponent}]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
