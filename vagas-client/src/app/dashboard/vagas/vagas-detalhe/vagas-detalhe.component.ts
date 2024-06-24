import { Component } from '@angular/core';
import { UUID } from 'angular2-uuid';
import { Vaga, Status, Prioridade } from '../vaga.model';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';

@Component({
  selector: 'app-vagas-detalhe',
  standalone: true,
  imports: [MaterialModule, FormsModule],
  templateUrl: './vagas-detalhe.component.html',
  styleUrl: './vagas-detalhe.component.css',
})
export class VagasDetalheComponent {
  onSubmit() {
    throw new Error('Method not implemented.');
  }
  vaga: Vaga;

  constructor() {
    this.vaga = new Vaga(
      UUID.UUID(),
      'COD123',
      'Desenvolvedor Front-end',
      'Desenvolver interfaces de usuário',
      new Date(),
      'São Paulo',
      5000.0,
      Status.ABERTA,
      Prioridade.ALTA,
      new Date('2024-12-31')
    );
  }
}
