export class Login {
  username!: string;
  password!: string;
}

export class Usuario {
  id!: any;
  username!: any;
  password!: any;
  role!: any;

  constructor(id: any, username: any, password: any, role: any) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }
}

export class DadosUsuario {
  id!: string;
  nome!: string;
  cpf!: string;
  email!: string;
  telefone!: string;
  endereco!: string;
}

export interface Token {
  token: string;
  expiration: number;
  hash: string;
}

export class ObjectAssigner {
  static create<T>(init: Partial<T>): T {
    const value = {};
    Object.assign(value, init);
    return value as T;
  }
}
