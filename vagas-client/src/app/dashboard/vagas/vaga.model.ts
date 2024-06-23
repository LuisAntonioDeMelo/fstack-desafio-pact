import { UUID } from 'angular2-uuid';

export enum Status {
  ABERTA = 'ABERTA',
  FECHADA = 'FECHADA',
  CANCELADA = 'CANCELADA',
}

export enum Prioridade {
  ALTA = 'ALTA',
  MEDIA = 'MEDIA',
  BAIXA = 'BAIXA',
}

export class Vaga {
  id: UUID;
  codigoVaga: string;
  titulo: string;
  descricao: string;
  dataCriacao: Date;
  localizacao: string;
  salario: number;
  status: Status;
  prioridade: Prioridade;
  dataVencimento: Date;
  dataInicio: any;

  constructor(
    id: UUID,
    codigoVaga: string,
    titulo: string,
    descricao: string,
    dataCriacao: Date,
    localizacao: string,
    salario: number,
    status: Status,
    prioridade: Prioridade,
    dataVencimento: Date
  ) {
    this.id = id;
    this.codigoVaga = codigoVaga;
    this.titulo = titulo;
    this.descricao = descricao;
    this.dataCriacao = dataCriacao;
    this.localizacao = localizacao;
    this.salario = salario;
    this.status = status;
    this.prioridade = prioridade;
    this.dataVencimento = dataVencimento;
  }
}
