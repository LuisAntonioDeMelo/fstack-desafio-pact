import {
  AfterViewInit,
  Component,
  Input,
  OnInit,
  ViewChild,
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';
import { Vaga } from '../vaga.model';
import { VagaService } from '../../../services/vagas.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIcon } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { VButtonModule } from '../../../components/custom.module';
import { query, state } from '@angular/animations';
import { BehaviorSubject, Observable } from 'rxjs';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-vagas-listar',
  standalone: true,
  imports: [
    FormsModule,
    MaterialModule,
    MatTableModule,
    MatPaginatorModule,
    MatIcon,
    VButtonModule,
  ],
  templateUrl: './vagas-listar.component.html',
  styleUrl: './vagas-listar.component.css',
})
export class VagasListarComponent implements OnInit {
  displayedColumns: string[] = [
    'codigoVaga',
    'titulo',
    'descricao',
    'dataCriacao',
    'localizacao',
    'salario',
    'status',
    'prioridade',
    'dataVencimento',
    'acoes',
  ];

  dataSource!: MatTableDataSource<Vaga>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  @Input() vagas: Vaga[] = [];

  constructor(private vagaService: VagaService, private router: Router) {}

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource<Vaga>(this.vagas);
    this.dataSource.data = this.vagas;
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  search: string = '';

  applyFilter(event: Event) {
    const valorSearch = (event.target as HTMLInputElement).value;
    if (valorSearch) {
      let vagasFiltradas = this.vagas.filter(
        (vaga) =>
          vaga.titulo.toLowerCase().includes(valorSearch) ||
          vaga.descricao.toLowerCase().includes(valorSearch)
      );
      this.vagas = vagasFiltradas;
      this.dataSource.data = vagasFiltradas;
    } else {
      this.buscarVagas();
    }
  }

  clear() {
    this.search = '';
    this.buscarVagas();
  }

  cadastrar() {
    this.router.navigate(['/dashboard/vagas/cadastrar']);
  }

  editar(row: Vaga) {
    const queryParams = JSON.stringify(row);
    this.router.navigate(['/dashboard/vagas/editar', { vaga: queryParams }]);
  }
  excluir(row: Vaga) {
    this.vagaService.deletarVaga(row.id).subscribe({
      next: () => {
        const idUser = localStorage.getItem('id_user_role') as string;
        this.vagaService.obterVagasPorAnalista(idUser).subscribe({
          next: (vagas) => {
            this.vagas = vagas;
          },
        });
      },
      error: (error) => {
        alert('Erro ao Deletar vaga :' + error.erro);
      },
    });
  }

  private buscarVagas() {
    const id = localStorage.getItem('id_user_role');
    this.vagaService.obterVagasPorAnalista(id).subscribe({
      next: (res) => {
        this.vagas = res;
        this.dataSource.data = res;
      },
    });
  }
}
