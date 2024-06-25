import { UUID } from 'angular2-uuid';

export enum Status {
  ABERTA = 'ABERTA',
  FECHADA = 'FECHADA',
  CANCELADA = 'CANCELADA',
  CRIADA = 'CRIADA',
  EM_PROCESSO = 'EM_PROCESSO',
}

export enum Prioridade {
  ALTA = 'ALTA',
  MEDIA = 'MEDIA',
  BAIXA = 'BAIXA',
}

export enum TipoVaga {
  REMOTO = 'REMOTO',
  HIBRIDO = 'HIBRIDO',
  PRESENCIAL = 'PRESENCIAL',
}

export interface Requisito {
  nome: string;
}

export class Vaga {
  id: UUID;
  idAnalistaResp: string;
  codigoVaga: string;
  titulo: string;
  descricao: string;
  dataCriacao: Date;
  localizacao: string;
  salario: number;
  status: Status;
  prioridade: Prioridade;
  dataVencimento: Date;
  requisitos: Requisito[];
  tipoVaga: TipoVaga;

  constructor(
    id: UUID,
    idAnalistaResp: string,
    codigoVaga: string,
    titulo: string,
    descricao: string,
    dataCriacao: Date,
    localizacao: string,
    salario: number,
    status: Status,
    prioridade: Prioridade,
    dataVencimento: Date,
    requisitos: Requisito[],
    tipoVaga: TipoVaga
  ) {
    this.id = id;
    this.idAnalistaResp = idAnalistaResp;
    this.codigoVaga = codigoVaga;
    this.titulo = titulo;
    this.descricao = descricao;
    this.dataCriacao = dataCriacao;
    this.localizacao = localizacao;
    this.salario = salario;
    this.status = status;
    this.prioridade = prioridade;
    this.dataVencimento = dataVencimento;
    this.requisitos = requisitos;
    this.tipoVaga = tipoVaga;
  }
}
