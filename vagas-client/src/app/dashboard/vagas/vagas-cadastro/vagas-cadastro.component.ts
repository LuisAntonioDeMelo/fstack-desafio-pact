import { Component } from '@angular/core';
import { MaterialModule } from '../../material.module';
import { Prioridade, Status, Vaga } from '../vaga.model';
import { UUID } from 'angular2-uuid';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vagas-cadastro',
  standalone: true,
  imports: [MaterialModule, FormsModule],
  templateUrl: './vagas-cadastro.component.html',
  styleUrl: './vagas-cadastro.component.css',
})
export class VagasCadastroComponent {
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
