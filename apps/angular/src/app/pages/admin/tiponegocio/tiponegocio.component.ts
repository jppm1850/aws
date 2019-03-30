import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog, MatSnackBar } from '@angular/material';
import { TipoNegocio } from '../../../_model/TipoNegocio';
import { NuevoTipoNegocioComponent } from './nuevo/nuevo.component';
import { TiponegocioService } from '../../../_services/tiponegocio.service';

@Component({
  selector: 'app-tiponegocio',
  templateUrl: './tiponegocio.component.html',
  styleUrls: ['./tiponegocio.component.css']
})
export class TiponegocioComponent implements OnInit {

  dataSource:MatTableDataSource<TipoNegocio>;
  totalElementos: number = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['id', 'descripcion', 'acciones'];

  constructor(
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private tiponegocioService: TiponegocioService
  ) {
    this.dataSource = new MatTableDataSource<TipoNegocio>();
   }

  ngOnInit() {
    this.cargarTabla(0, 100, false);

    this.tiponegocioService.mensajeRegistro.subscribe((dato) => {
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
    this.tiponegocioService.obtenerRegistros(pageIndex, pageSize).subscribe((datos) => {
      let registros = JSON.parse(JSON.stringify(datos)).content;
      this.dataSource = new MatTableDataSource<TipoNegocio>(registros);
      this.totalElementos = JSON.parse(JSON.stringify(datos)).totalElements;
      if(!desdePaginador){
        this.dataSource.paginator = this.paginator;
      }
    });
  }

  eliminarTipoNegocio(id: number) {
    this.tiponegocioService.eliminarTipoNegocio(id).subscribe((data) => {
      this.tiponegocioService.mensajeRegistro.next('Dato eliminado correctamente...');
    });
  }

  actualizarTipoNegocio(tipoNegocio: TipoNegocio) {
    this.dialog.open(NuevoTipoNegocioComponent, {
      width: '80%',
      height: '80%',
      data: { tipoNegocio: tipoNegocio }
    });
  }

  openDialog() {
    this.dialog.open(NuevoTipoNegocioComponent, {
      width: '80%',
      height: '80%'
    });
  }

}
