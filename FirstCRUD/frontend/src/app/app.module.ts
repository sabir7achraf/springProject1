import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AdminTemplateComponent } from './admin-template/admin-template.component';
import { HomeComponent } from './home/home.component';
import { UsersComponent } from './users/users.component';
import { LoginComponent } from './login/login.component';
import { PaymentsComponent } from './payments/payments.component';
import {MatToolbar, MatToolbarModule} from "@angular/material/toolbar";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatMenu, MatMenuModule, MatMenuTrigger} from "@angular/material/menu";
import {MatDrawer, MatDrawerContainer, MatDrawerContent, MatSidenavModule} from "@angular/material/sidenav";
import {MatList, MatListItem, MatListModule} from "@angular/material/list";
import {MatButton, MatButtonModule, MatIconButton} from "@angular/material/button";
import {MatCard, MatCardActions, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatCalendarHeader, MatDatepickerInput, MatDatepickerModule} from "@angular/material/datepicker";
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {MatInput, MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Authguard} from "./guard/authguard.guard";
import {AuthorizationGuardService} from "./guard/authorization-guard.service";
import {MatTableModule} from "@angular/material/table";
import {HttpClientModule} from "@angular/common/http";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {MatSort, MatSortModule} from "@angular/material/sort";
import { NewUserComponent } from './new-user/new-user.component';
import { NewPaymentComponent } from './new-payment/new-payment.component';
import {MatSelect, MatSelectModule} from "@angular/material/select";
import {MatStepper, MatStepperModule} from "@angular/material/stepper";
import {MatNativeDateModule, provideNativeDateAdapter} from "@angular/material/core";



@NgModule({
  declarations: [
    AppComponent,
    AdminTemplateComponent,
    HomeComponent,
    UsersComponent,
    LoginComponent,
    PaymentsComponent,
    NewUserComponent,
    NewPaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatListModule,
    MatButtonModule,
    MatSidenavModule,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatCardHeader,
    MatFormFieldModule,
    MatInputModule,
    MatCardActions,
    ReactiveFormsModule,
    MatTableModule,
    HttpClientModule,
    MatPaginatorModule,
    MatSortModule,
    MatSelectModule,
    FormsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [
    provideAnimationsAsync(),Authguard,AuthorizationGuardService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
