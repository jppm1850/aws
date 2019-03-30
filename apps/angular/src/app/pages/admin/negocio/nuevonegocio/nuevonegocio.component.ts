import { Component, OnInit, Inject } from '@angular/core';
import { NegocioService } from '../../../../_services/negocio.service';
import { MAT_DIALOG_DATA } from '@angular/material';
import { Negocio } from '../../../../_model/Negocio';
import { TiponegocioService } from '../../../../_services/tiponegocio.service';
import { TipoNegocio } from '../../../../_model/TipoNegocio';

@Component({
  selector: 'app-nuevonegocio',
  templateUrl: './nuevonegocio.component.html',
  styleUrls: ['./nuevonegocio.component.css']
})
export class NuevonegocioComponent implements OnInit {

  negocio: Negocio;
  tiposNegocios: TipoNegocio[] = [];

  constructor(
    private negocioService: NegocioService,
    private tipoNegocioService: TiponegocioService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.negocio = new Negocio();
   }

  ngOnInit() {
    if(this.data != null && this.data.negocio != null){
      this.negocio = this.data.negocio;
    }

    this.tipoNegocioService.obtenerTodosLosRegistros().subscribe((data) => {
      this.tiposNegocios = data;
    });
  }

  onSubmit() {
    this.negocioService.guardarNegocio(this.negocio).subscribe((data)=>{
        this.negocioService.mensajeRegistro.next('Registrado Correctamente...');
    }, (error) => {
      this.negocioService.mensajeRegistro.next('Error al guardar el registro...');
    });
  }

  buscarPreSeleccionado(tipoNegocio1 : TipoNegocio, tipoNegocio2: TipoNegocio){
    return tipoNegocio1 && tipoNegocio2 ? tipoNegocio1.id === tipoNegocio2.id : tipoNegocio1 === tipoNegocio2;
  }

}
