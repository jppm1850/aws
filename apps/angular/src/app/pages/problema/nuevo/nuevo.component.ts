import { Component, OnInit, Input } from '@angular/core';
import { ProblemaService } from '../../../_services/problema.service';
import { Problema } from '../../../_model/Problema';
import { FeedBack } from '../../../_model/Feedback';

@Component({
  selector: 'app-nuevo',
  templateUrl: './nuevo.component.html',
  styleUrls: ['./nuevo.component.css']
})
export class NuevoComponent implements OnInit {

  problemas: Problema[] = [];
  texto: string = '';
  feedback: FeedBack;

  constructor(
    private serviceProblema: ProblemaService) {
    this.feedback = new FeedBack();
  }

  ngOnInit() {
    this.serviceProblema.obtenerCatalogoProblemas().subscribe((data) => {
      this.problemas = data;
    });
  }

  onSubmit() {
    this.feedback.fecha = new Date();
    this.serviceProblema.guardarFeedBack(this.feedback).subscribe((data)=>{
        this.serviceProblema.mensajeRegistro.next('Registrado Correctamente...');
    }, (error) => {
      this.serviceProblema.mensajeRegistro.next('Error al guardar el feedback...');
    });
  }

}
