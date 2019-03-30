import { Component, OnInit, ViewChild } from '@angular/core';
import { Negocio } from '../../../_model/Negocio';
import { MatTableDataSource, MatDialog, MatSnackBar, MatPaginator } from '@angular/material';
import { NuevonegocioComponent } from './nuevonegocio/nuevonegocio.component';
import { NegocioService } from '../../../_services/negocio.service';

@Component({
  selector: 'app-negocio',
  templateUrl: './negocio.component.html',
  styleUrls: ['./negocio.component.css']
})
export class NegocioComponent implements OnInit {

  dataSource:MatTableDataSource<Negocio>;
  totalElementos: number = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['id', 'descripcion', 'tipoNegocio', 'acciones'];

  constructor(
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private negocioService: NegocioService
  ) {
    this.dataSource = new MatTableDataSource<Negocio>();
   }

  ngOnInit() {
    this.cargarTabla(0, 100, false);

    this.negocioService.mensajeRegistro.subscribe((dato) => {
      this.dialog.closeAll();
      this.snackBar.open(dato, null, {
        duration: 1500,
      });
      this.cargarTabla(0, 100, false);
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  mostrarMas(event){
    this.cargarTabla(event.pageIndex, event.pageSize, true);
  }

  cargarTabla(pageIndex: number, pageSize: number, desdePaginador: boolean){
    this.negocioService.obtenerRegistros(pageIndex, pageSize).subscribe((datos) => {
      let registros = JSON.parse(JSON.stringify(datos)).content;
      this.dataSource = new MatTableDataSource<Negocio>(registros);
      this.totalElementos = JSON.parse(JSON.stringify(datos)).totalElements;
      if(!desdePaginador){
        this.dataSource.paginator = this.paginator;
      }
    });
  }

  eliminarNegocio(id: number) {
    this.negocioService.eliminarNegocio(id).subscribe((data) => {
      this.negocioService.mensajeRegistro.next('Dato eliminado correctamente...');
    });
  }

  actualizarNegocio(negocio: Negocio) {
    this.dialog.open(NuevonegocioComponent, {
      width: '80%',
      height: '80%',
      data: { negocio: negocio }
    });
  }

  openDialog() {
    this.dialog.open(NuevonegocioComponent, {
      width: '80%',
      height: '80%'
    });
  }

}
