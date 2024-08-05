import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {NewUserComponent} from "../new-user/new-user.component";
import {UserServiceService} from "../services/user-service.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  public loginForm!: FormGroup
  public switch:string="login"

  constructor(private fb: FormBuilder,private route: Router ,public  authservice: UserServiceService) {
  }
  changeComponentToLogin(): void {
    this.switch = 'login';
  }
  changeComponentToRegistre(): void{
    this.switch = 'registre';
  }

  ngOnInit(): void {
    this.loginForm=this.fb.group({
      username : this.fb.control(''),
      password : this.fb.control('')
      }
    );

  }
  login(){
    this.authservice.login(this.loginForm.value.username,this.loginForm.value.password)

  }

  redirectToRegister() {
    this.route.navigateByUrl('/register');


  }
}
