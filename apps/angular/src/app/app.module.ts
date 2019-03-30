import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AgmCoreModule } from '@agm/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MaterialModule } from './material/material.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MapaComponent } from './pages/mapa/mapa.component';
import { AboutComponent } from './pages/about/about.component';
import { ProblemaComponent } from './pages/problema/problema.component';
import { DetalleComponent } from './pages/mapa/detalle/detalle.component';
import { NuevoComponent } from './pages/problema/nuevo/nuevo.component';
import { SecurityComponent } from './pages/security/security.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { TokenInterceptorService } from './_services/token-interceptor.service';
import { TiponegocioComponent } from './pages/admin/tiponegocio/tiponegocio.component';
import { NegocioComponent } from './pages/admin/negocio/negocio.component';
import { SedesComponent } from './pages/admin/sedes/sedes.component';
import { AdminComponent } from './pages/admin/admin/admin.component';
import { NuevoTipoNegocioComponent } from './pages/admin/tiponegocio/nuevo/nuevo.component';
import { NuevonegocioComponent } from './pages/admin/negocio/nuevonegocio/nuevonegocio.component';
import { NuevasedeComponent } from './pages/admin/sedes/nuevasede/nuevasede.component';
import { LocationStrategy, PathLocationStrategy } from '@angular/common';
import { BodyComponent } from './pages/body/body.component';
import { LoginComponent } from './pages/login/login.component';
import { ErrorComponent } from './pages/login/error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    MapaComponent,
    AboutComponent,
    ProblemaComponent,
    DetalleComponent,
    NuevoComponent,
    SecurityComponent,
    LogoutComponent,
    TiponegocioComponent,
    NegocioComponent,
    SedesComponent,
    AdminComponent,
    NuevoTipoNegocioComponent,
    NuevonegocioComponent,
    NuevasedeComponent,
    BodyComponent,
    LoginComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    //API KEY de google maps configurado en google cloud platform
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDRlxhDKnHX5ie8Y3gJe1YOYpC4dWpa0no'
    })
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    { provide: LocationStrategy, useClass: PathLocationStrategy }],
  bootstrap: [AppComponent],
  entryComponents: [
    DetalleComponent, 
    NuevoComponent, 
    NuevoTipoNegocioComponent, 
    NuevonegocioComponent,
    NuevasedeComponent,
    ErrorComponent]
})
export class AppModule { }
