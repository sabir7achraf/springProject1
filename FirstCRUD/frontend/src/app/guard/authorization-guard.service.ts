import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import {Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";
@Injectable()
export  class AuthorizationGuardService {
  constructor(private authservice : AuthService,private router :Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.authservice.authentifie){
      let requiredRoles = route.data['roles'];
      let userRoles =this.authservice.roles;
      for(let role of userRoles){
        if(requiredRoles.includes(role))
          return true;
      }
      return false ;
    }
    else{
        this.router.navigateByUrl('login')
      return false;
    }
  }

}
