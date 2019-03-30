import { Injectable } from '@angular/core';
import { TipoNegocio } from '../_model/TipoNegocio';
import { Local } from '../_model/Local';
import { HttpClient } from '@angular/common/http';
import { HOST_BACKEND, TOKEN_NAME } from '../_shared/constants';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SedeService {

  urlSede: string = `${HOST_BACKEND}/api/sede`;
  //urlSede: string = `https://prxgqffzo0.execute-api.us-east-1.amazonaws.com/prod/api/sede`;

  mensajeRegistro = new Subject<string>();

  constructor(private httpClient: HttpClient) { }

  buscarSedesPorGeolocalizacion(latitud: number, longitud: number, radio: number, tipoNegocio: TipoNegocio) {
    let token = sessionStorage.getItem(TOKEN_NAME);
    return this.httpClient.get<Local[]>(`${this.urlSede}/listar?tipoNegocio=${tipoNegocio.id}&latitud=${latitud}&longitud=${longitud}&radio=${radio}`);
  }

  guardarLocal(local: Local) {
    return this.httpClient.post(`${this.urlSede}/registrar`, local);
  }

  eliminarLocal(id: number) {
    return this.httpClient.delete(`${this.urlSede}/eliminar/${id}`);
  }

  obtenerRegistros(page: number, size: number) {
    return this.httpClient.get<Local[]>(`${this.urlSede}/listarpaginado?page=${page}&size=${size}`);
  }
}
