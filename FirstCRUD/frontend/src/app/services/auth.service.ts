import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public users :any={
    admin :{ password : "1234", roles :['STUDENTS','ADMIN']},
    user1 :{ password : "1234", roles :['STUDENTS']}
  }

  constructor() { }

  public login(username : string,password : string):boolean{
  return !!(this.users[username] && this.users[username]['password'] == password);
  }
}
