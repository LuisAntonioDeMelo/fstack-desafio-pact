import { Injectable } from '@angular/core';
import { Vaga, Status, Prioridade } from './vaga.model';
import { UUID } from 'angular2-uuid';

@Injectable({
  providedIn: 'root',
})
export class VagaService {
  private vagas: Vaga[] = [
    new Vaga(
      UUID.UUID(),
      'COD001',
      'Desenvolvedor Front-end',
      'Desenvolver interfaces de usuário',
      new Date(),
      'São Paulo',
      5000,
      Status.ABERTA,
      Prioridade.ALTA,
      new Date('2024-12-31')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
    new Vaga(
      UUID.UUID(),
      'COD002',
      'Desenvolvedor Back-end',
      'Desenvolver APIs e lógica de negócios',
      new Date(),
      'Rio de Janeiro',
      6000,
      Status.FECHADA,
      Prioridade.MEDIA,
      new Date('2024-11-30')
    ),
  ];

  getVagas(): Vaga[] {
    return this.vagas;
  }
}
