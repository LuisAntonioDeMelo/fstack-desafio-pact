export class Login {
  username!: string;
  password!: string;
}

export interface Usuario {
  username: string | null | undefined;
  password: string | null | undefined;
  role: string | null | undefined;
}

export class Pessoa {
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
