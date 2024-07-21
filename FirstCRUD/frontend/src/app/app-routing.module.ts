import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {UsersComponent} from "./users/users.component";
import {PaymentsComponent} from "./payments/payments.component";
import {AdminTemplateComponent} from "./admin-template/admin-template.component";

const routes: Routes = [
  {path: '' , component:LoginComponent},
  {path: 'login' , component:LoginComponent},

  { path: 'admin' , component:AdminTemplateComponent,  children:[
    { path: 'home' , component:HomeComponent},
  {path: 'users' , component:UsersComponent},
  {path: 'payments' , component:PaymentsComponent}]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
