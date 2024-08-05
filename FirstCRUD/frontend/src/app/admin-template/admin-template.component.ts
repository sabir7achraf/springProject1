import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrl: './admin-template.component.css'
})
export class AdminTemplateComponent {
 constructor(private router: Router,public auth : AuthService) {
 }
  logout() {
   this.router.navigateByUrl('login')
  }
}
