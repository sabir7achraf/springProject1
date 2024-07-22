import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public users :any={
    admin :{ password : "1234", role :['STUDENTS','ADMIN']},
    user1 :{ password : "1234", role :['STUDENTS']}
  }
  public user : any
  public authentifie : boolean =false
  public roles : string[]=[]

  constructor() { }

  public login(username : string,password : string):boolean{
  if (this.users[username] && this.users[username]['password'] == password){
    this.user=username;
    this.authentifie=true;
    this.roles=this.users[username]["role"];
    return  true;
  }
  else {
    return false;
  }
  }

  logout() {
    this.authentifie=false;
    this.roles=[]
    this.user=undefined
  }
}
