import { Component, OnInit, Inject } from '@angular/core';
import { TipoNegocio } from '../../../../_model/TipoNegocio';
import { TiponegocioService } from '../../../../_services/tiponegocio.service';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-nuevo',
  templateUrl: './nuevo.component.html',
  styleUrls: ['./nuevo.component.css']
})
export class NuevoTipoNegocioComponent implements OnInit {

  tipoNegocio: TipoNegocio;

  constructor(
    private tipoNegocioService: TiponegocioService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.tipoNegocio = new TipoNegocio();
   }

  ngOnInit() {
    if(this.data != null && this.data.tipoNegocio != null){
      this.tipoNegocio = this.data.tipoNegocio;
    }
  }

  onSubmit() {
    this.tipoNegocioService.guardarTipoNegocio(this.tipoNegocio).subscribe((data)=>{
        this.tipoNegocioService.mensajeRegistro.next('Registrado Correctamente...');
    }, (error) => {
      this.tipoNegocioService.mensajeRegistro.next('Error al guardar el registro...');
    });
  }

}
