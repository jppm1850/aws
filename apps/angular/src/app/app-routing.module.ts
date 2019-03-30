import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MapaComponent } from './pages/mapa/mapa.component';
import { AboutComponent } from './pages/about/about.component';
import { ProblemaComponent } from './pages/problema/problema.component';
import { SecurityComponent } from './pages/security/security.component';
import { GuardService } from './_services/guard.service';
import { LogoutComponent } from './pages/logout/logout.component';
import { TiponegocioComponent } from './pages/admin/tiponegocio/tiponegocio.component';
import { NegocioComponent } from './pages/admin/negocio/negocio.component';
import { SedesComponent } from './pages/admin/sedes/sedes.component';
import { AdminComponent } from './pages/admin/admin/admin.component';
import { BodyComponent } from './pages/body/body.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent, canActivate: [GuardService]},
  {path: 'logout', component: LogoutComponent},
  {path: 'security', component: SecurityComponent},
  {path: 'app', component: BodyComponent, children: [
    {path: 'mapa', component: MapaComponent},
    {path: 'about', component: AboutComponent},
    {path: 'problema', component: ProblemaComponent},
    {path: 'admin', component: AdminComponent, children: [
      {path: 'tiponegocio', component: TiponegocioComponent},
      {path: 'negocio', component: NegocioComponent},
      {path: 'sedes', component: SedesComponent}
   ]},
  ], canActivate: [GuardService]},
  {path: '**', redirectTo: 'login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
