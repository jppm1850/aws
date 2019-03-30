import { Component, OnInit, Inject } from '@angular/core';
import { Local } from '../../../../_model/Local';
import { Negocio } from '../../../../_model/Negocio';
import { SedeService } from '../../../../_services/sede.service';
import { NegocioService } from '../../../../_services/negocio.service';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-nuevasede',
  templateUrl: './nuevasede.component.html',
  styleUrls: ['./nuevasede.component.css']
})
export class NuevasedeComponent implements OnInit {

  sede: Local;
  negocios: Negocio[] = [];

  constructor(
    private negocioService: NegocioService,
    private sedeService: SedeService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.sede = new Local();
   }

  ngOnInit() {
    if(this.data != null && this.data.sede != null){
      this.sede = this.data.sede;
    }

    this.negocioService.obtenerTodosLosRegistros().subscribe((data) => {
      this.negocios = data;
    });
  }

  onSubmit() {
    this.sedeService.guardarLocal(this.sede).subscribe((data)=>{
        this.sedeService.mensajeRegistro.next('Registrado Correctamente...');
    }, (error) => {
      this.sedeService.mensajeRegistro.next('Error al guardar el registro...');
    });
  }

  buscarPreSeleccionado(sede1 : Local, sede2: Local){
    return sede1 && sede2 ? sede1.id === sede2.id : sede1 === sede2;
  }

}
