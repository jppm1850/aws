import { Component, OnInit } from '@angular/core';
import { SedeService } from '../../_services/sede.service';
import { DetalleComponent } from './detalle/detalle.component';
import { MatDialog } from '@angular/material';
import { TipoNegocio } from '../../_model/TipoNegocio';
import { Local } from '../../_model/Local';
import { RADIO, ZOOM, TIME_UPDATE_GEOLOCALIZATION } from '../../_shared/constants';
import { MiMarker } from '../../_model/MiMarker';
import { TiponegocioService } from '../../_services/tiponegocio.service';

/**
 * @see https://angular-maps.com/guides/getting-started/
 */ 
@Component({
  selector: 'app-mapa',
  templateUrl: './mapa.component.html',
  styleUrls: ['./mapa.component.css']
})
export class MapaComponent implements OnInit {

  zoom = ZOOM;
  apiGeoLocalization = navigator.geolocation;
  mostrarLoading: boolean = false;

  locales: Local[] = [];
  tiposNegocios: TipoNegocio[] = [];

  tipoNegocio: TipoNegocio;
  miMarker: MiMarker;

  constructor(
    public dialog: MatDialog, 
    private serviceSedes: SedeService,
    private tipoNegocioService: TiponegocioService) {
      this.tipoNegocio = new TipoNegocio();
      this.miMarker = new MiMarker();
     }

  ngOnInit() {
    this.mostrarLoading = true;
    this.cargarDatos();
  }

  cargarDatos() {
    this.apiGeoLocalization.getCurrentPosition(position => {
      this.miMarker.latitud = position.coords.latitude;
      this.miMarker.longitud = position.coords.longitude;
      this.miMarker.nombre = "Estoy Aquí";

      setTimeout(() => {
        this.mostrarNegocios();
      }, 500);
    }, err => {
      console.log(err);
    }, { timeout: TIME_UPDATE_GEOLOCALIZATION });
  }

  mostrarNegocios() {
    if (this.tiposNegocios.length <= 0) {
      this.tipoNegocioService.obtenerTodosLosRegistros().subscribe((data) => {
        this.tiposNegocios = data;
      });
    }

    this.locales = [];
    if (this.tipoNegocio.id !== undefined) {
      this.serviceSedes.buscarSedesPorGeolocalizacion(this.miMarker.latitud, this.miMarker.longitud, RADIO, this.tipoNegocio)
      .subscribe((data) => {
        this.locales = data;
      });
    }
    this.mostrarLoading = false;
  }

  actualizarUbicacion() {
    this.cargarDatos();
  }

  openDialog(local: Local) {
    let dialogRef;
    this.mostrarLoading = true;

    setTimeout(() => {
      dialogRef = this.dialog.open(DetalleComponent, {
        height: '400px',
        width: '400px',
        data: { 
          local: local, 
          mostrarLoading: this.mostrarLoading }
      });

      dialogRef.afterOpen().subscribe(() => {
        this.mostrarLoading = false;
      });
    },1000);
  }
}