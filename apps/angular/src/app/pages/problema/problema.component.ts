import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog, MatSnackBar } from '@angular/material';
import { ProblemaService } from '../../_services/problema.service';
import { FeedBack } from '../../_model/Feedback';
import { NuevoComponent } from './nuevo/nuevo.component';

@Component({
  selector: 'app-problema',
  templateUrl: './problema.component.html',
  styleUrls: ['./problema.component.css']
})
export class ProblemaComponent implements OnInit {

  dataSource:MatTableDataSource<FeedBack>;
  totalElementos: number = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['id', 'tema', 'mensaje', 'fecha', 'acciones'];

  constructor(
    private dialog: MatDialog, 
    private serviceProblema: ProblemaService,
    private snackBar: MatSnackBar) {
    this.dataSource = new MatTableDataSource<FeedBack>();
  }

  ngOnInit() {
    this.cargarTabla(0, 100, false);

    this.serviceProblema.mensajeRegistro.subscribe((dato) => {
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
    this.serviceProblema.obtenerFeedBacksPropios(pageIndex, pageSize).subscribe((datos) => {
      let feedbacks = JSON.parse(JSON.stringify(datos)).content;
      this.dataSource = new MatTableDataSource<FeedBack>(feedbacks);
      this.totalElementos = JSON.parse(JSON.stringify(datos)).totalElements;
      if(!desdePaginador){
        this.dataSource.paginator = this.paginator;
      }
    });
  }

  eliminarFeedBack(id: number) {
    this.serviceProblema.eliminarFeedBack(id).subscribe((data) => {
      this.serviceProblema.mensajeRegistro.next('Dato eliminado correctamente...');
    });
  }

  openDialog() {
    this.dialog.open(NuevoComponent, {
      width: '80%',
      height: '80%'
    });
  }

}
