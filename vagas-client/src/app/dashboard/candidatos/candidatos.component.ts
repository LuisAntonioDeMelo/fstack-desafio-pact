import {
  AfterViewInit,
  Component,
  OnInit,
  ViewChild,
  inject,
} from '@angular/core';
import { LoginService } from '../../services/login.service';
import { AnalistaService } from '../../services/analista.service';
import { MatCardModule } from '@angular/material/card';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MaterialModule } from '../material.module';
import { FormsModule } from '@angular/forms';
import { MatIcon } from '@angular/material/icon';

export interface CandidatoInteressado {
  nome: string;
  idCandidato: string;
  idVaga: string;
  habilidades: string[];
  codigoVaga: string;
  statusVaga: string;
}

@Component({
  selector: 'app-candidatos',
  standalone: true,
  imports: [
    FormsModule,
    MaterialModule,
    MatIcon,
    MatTableModule,
    MatPaginatorModule,
  ],
  templateUrl: './candidatos.component.html',
  styleUrl: './candidatos.component.css',
})
export class CandidatosComponent implements OnInit, AfterViewInit {
  loginService: LoginService = inject(LoginService);
  analistaService: AnalistaService = inject(AnalistaService);

  dataSource!: MatTableDataSource<CandidatoInteressado>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  canditados!: CandidatoInteressado[];

  displayedColumns: string[] = ['codigoVaga', 'nome', 'statusVaga', 'acoes'];

  constructor() {}

  ngOnInit(): void {
    const idUser = localStorage.getItem('id_user_role') as string;

    this.analistaService.obterCandidatosIngredsoAnalista(idUser).subscribe({
      next: (res: CandidatoInteressado[]) => {
        this.canditados = res;
        this.dataSource.paginator = this.paginator;
      },
      error: (error) => {},
    });
  }

  ngAfterViewInit(): void {
    this.dataSource = new MatTableDataSource<CandidatoInteressado>(
      this.canditados
    );
  }

  descontinuar(row: any) {
    console.log(row);
  }

  detalhar(row: any) {}
}
