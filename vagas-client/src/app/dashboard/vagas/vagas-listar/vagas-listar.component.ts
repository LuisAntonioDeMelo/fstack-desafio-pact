import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';
import { Vaga } from '../vaga.model';
import { VagaService } from '../vagas.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-vagas-listar',
  standalone: true,
  imports: [FormsModule, MaterialModule, MatTableModule],
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
  ];
  dataSource!: MatTableDataSource<Vaga>;
  vagas: Vaga[] = [];
  constructor(private vagaService: VagaService) {}

  ngOnInit(): void {
    this.loadVagas();
  }

  loadVagas(): void {
    const vagas = this.vagaService.getVagas();
    this.dataSource = new MatTableDataSource(vagas);
  }
}
