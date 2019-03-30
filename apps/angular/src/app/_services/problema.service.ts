import { Injectable } from '@angular/core';
import { FeedBack } from '../_model/Feedback';
import { HttpClient } from '@angular/common/http';
import { HOST_BACKEND, TOKEN_NAME } from '../_shared/constants';
import { Problema } from '../_model/Problema';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProblemaService {

  urlFeedback: string = `${HOST_BACKEND}/api/feedback`;

  mensajeRegistro = new Subject<string>();

  constructor(private httpClient: HttpClient) { }

  obtenerCatalogoProblemas() {
    return this.httpClient.get<Problema[]>(`${this.urlFeedback}/problema/listar`);
  }

  obtenerFeedBacksPropios(page: number, size: number) {
    return this.httpClient.get<FeedBack[]>(`${this.urlFeedback}/listar?page=${page}&size=${size}`);
  }

  guardarFeedBack(feedback: FeedBack) {
    return this.httpClient.post(`${this.urlFeedback}/registrar`, feedback);
  }

  eliminarFeedBack(id: number) {
    return this.httpClient.delete(`${this.urlFeedback}/eliminar/${id}`);
  }
}
