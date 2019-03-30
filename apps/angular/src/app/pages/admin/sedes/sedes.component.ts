import { Component, OnInit, ViewChild } from '@angular/core';
import { Local } from '../../../_model/Local';
import { MatTableDataSource, MatPaginator, MatDialog, MatSnackBar } from '@angular/material';
import { SedeService } from '../../../_services/sede.service';
import { NuevasedeComponent } from './nuevasede/nuevasede.component';

@Component({
  selector: 'app-sedes',
  templateUrl: './sedes.component.html',
  styleUrls: ['./sedes.component.css']
})
export class SedesComponent implements OnInit {

  dataSource:MatTableDataSource<Local>;
  totalElementos: number = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['id', 'negocio', 'local', 'coordenadas', 'acciones'];

  constructor(
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private sedeService: SedeService
  ) {
    this.dataSource = new MatTableDataSource<Local>();
   }

  ngOnInit() {
    this.cargarTabla(0, 100, false);

    this.sedeService.mensajeRegistro.subscribe((dato) => {
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
    this.sedeService.obtenerRegistros(pageIndex, pageSize).subscribe((datos) => {
      let registros = JSON.parse(JSON.stringify(datos)).content;
      this.dataSource = new MatTableDataSource<Local>(registros);
      this.totalElementos = JSON.parse(JSON.stringify(datos)).totalElements;
      if(!desdePaginador){
        this.dataSource.paginator = this.paginator;
      }
    });
  }

  eliminarLocal(id: number) {
    this.sedeService.eliminarLocal(id).subscribe((data) => {
      this.sedeService.mensajeRegistro.next('Dato eliminado correctamente...');
    });
  }

  actualizarLocal(sede: Local) {
    this.dialog.open(NuevasedeComponent, {
      width: '80%',
      height: '80%',
      data: { sede: sede }
    });
  }

  openDialog() {
    this.dialog.open(NuevasedeComponent, {
      width: '80%',
      height: '80%'
    });
  }

}
