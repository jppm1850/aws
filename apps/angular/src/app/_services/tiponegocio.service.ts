import { Injectable } from '@angular/core';
import { HOST_BACKEND } from '../_shared/constants';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { TipoNegocio } from '../_model/TipoNegocio';

@Injectable({
  providedIn: 'root'
})
export class TiponegocioService {

  urlTipoNegocio: string = `${HOST_BACKEND}/api/tiponegocio`;
  //urlTipoNegocio: string = `https://zj1s896pui.execute-api.us-east-1.amazonaws.com/prod/api/tiponegocio`;

  mensajeRegistro = new Subject<string>();

  constructor(private httpClient: HttpClient) { }

  obtenerTodosLosRegistros() {
    return this.httpClient.get<TipoNegocio[]>(`${this.urlTipoNegocio}/listar`);
  }

  obtenerRegistros(page: number, size: number) {
    return this.httpClient.get<TipoNegocio[]>(`${this.urlTipoNegocio}/listarpaginado?page=${page}&size=${size}`);
  }

  guardarTipoNegocio(tipoNegocio: TipoNegocio) {
    return this.httpClient.post(`${this.urlTipoNegocio}/registrar`, tipoNegocio);
  }

  eliminarTipoNegocio(id: number) {
    return this.httpClient.delete(`${this.urlTipoNegocio}/eliminar/${id}`);
  }
}
