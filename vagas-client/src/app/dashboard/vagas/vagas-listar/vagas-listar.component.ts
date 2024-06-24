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
import { VagaService } from '../vagas.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIcon } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { VButtonModule } from '../../../components/custom.module';

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
export class VagasListarComponent implements OnInit, AfterViewInit {
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

  @Input() vagas: Vaga[] = [];
  constructor(private vagaService: VagaService, private router: Router) {}

  ngOnInit(): void {
    this.loadVagas();
  }

  loadVagas(): void {
    const vagas = this.vagaService.getVagas();
    this.dataSource = new MatTableDataSource<Vaga>(vagas);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  searchQuery: string = '';

  onSearch() {
    console.log('Buscar:', this.searchQuery);
  }

  clearSearch() {
    this.searchQuery = '';
    console.log('Pesquisa limpa');
  }

  cadastrar() {
    console.log('cas');
    this.router.navigate(['/dashboard/vagas/cadastrar']);
  }

  editar(row: any) {
    this.router.navigate(['/dashboard/vagas/cadastrar']);
  }
  excluir(row: any) {}
}
