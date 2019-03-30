import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatBottomSheetModule,MatButtonModule, MatToolbarModule, MatIconModule, MatSidenavModule, MatMenuModule, 
  MatDividerModule, MatProgressSpinnerModule, MatCardModule, MatListModule, MatFormFieldModule, 
  MatTableModule, MatPaginatorModule, MatInputModule, MatDialogModule, MatPaginatorIntl, MatSelectModule, MatSnackBarModule, MatGridListModule, MatProgressBarModule, MatExpansionModule}
from '@angular/material';
import { MatPaginatorImpl } from '../_shared/mat-paginator';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatMenuModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    MatCardModule,
    MatListModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatInputModule,
    MatDialogModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    MatSnackBarModule,
    MatGridListModule,
    MatProgressBarModule,
    MatExpansionModule,
    MatBottomSheetModule
  ],
  exports: [
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatMenuModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    MatCardModule,
    MatListModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatInputModule,
    MatDialogModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    MatSnackBarModule,
    MatGridListModule,
    MatProgressBarModule,
    MatExpansionModule,
    MatBottomSheetModule
  ],
  providers : [{ provide : MatPaginatorIntl, useClass: MatPaginatorImpl }],
  declarations: []
})
export class MaterialModule { }
