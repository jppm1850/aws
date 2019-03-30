import { Component, OnInit } from '@angular/core';
import { SecurityService } from '../../_services/security.service';
import { RespuestaApi } from '../../_model/RespuestaApi';
import { TOKEN_NAME, ACCESS_TOKEN_NAME } from '../../_shared/constants';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  opened: boolean = false;
  isAdmin: boolean = false;

  constructor(
    private securityService: SecurityService ){
  }

  ngOnInit(){
    setTimeout(() => {
      this.isAdmin = this.securityService.esRoleAdmin();
    },1500);

    setInterval(()=> {
      this.securityService.refreshToken().subscribe((data: RespuestaApi)=>{
        if(data.status == 'OK'){
          sessionStorage.setItem(TOKEN_NAME, data.idToken);
          sessionStorage.setItem(ACCESS_TOKEN_NAME, data.accessToken);
        }
      });
    },1000 * 60 * 30 );
  }

}
