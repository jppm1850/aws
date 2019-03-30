import { Injectable } from '@angular/core';
import { HOST_BACKEND } from '../_shared/constants';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Negocio } from '../_model/Negocio';

@Injectable({
  providedIn: 'root'
})
export class NegocioService {

  urlNegocio: string = `${HOST_BACKEND}/api/negocio`;
  //urlNegocio: string = `https://zj1s896pui.execute-api.us-east-1.amazonaws.com/prod/api/negocio`;

  mensajeRegistro = new Subject<string>();

  constructor(private httpClient: HttpClient) { }

  obtenerTodosLosRegistros() {
    return this.httpClient.get<Negocio[]>(`${this.urlNegocio}/listar`);
  }

  obtenerRegistros(page: number, size: number) {
    return this.httpClient.get<Negocio[]>(`${this.urlNegocio}/listarpaginado?page=${page}&size=${size}`);
  }

  guardarNegocio(negocio: Negocio) {
    return this.httpClient.post(`${this.urlNegocio}/registrar`, negocio);
  }

  eliminarNegocio(id: number) {
    return this.httpClient.delete(`${this.urlNegocio}/eliminar/${id}`);
  }
}
