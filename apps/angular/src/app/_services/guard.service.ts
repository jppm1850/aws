import { Injectable } from '@angular/core';
import { CanActivate, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { TOKEN_NAME } from '../_shared/constants';
import { SecurityService } from './security.service';

@Injectable({
  providedIn: 'root',
})
export class GuardService implements CanActivate {

  constructor(
    private router: Router,
    private securityService: SecurityService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let token = sessionStorage.getItem(TOKEN_NAME);
    let isAdministrador = this.securityService.esRoleAdmin();

    if (token != null) {
      
      if(!isAdministrador){
        switch (state.url) {
          case '/admin/tiponegocio':
          case '/admin/negocio':
          case '/admin/sedes': 
            this.router.navigate(['mapa']);  
            return false;
        }
      }
      return true;
    } else {
      if(state.url !== '/login'){
        this.router.navigate(['login']);
      }else{
        return true;
      }
    }
    return false;
  }
}
