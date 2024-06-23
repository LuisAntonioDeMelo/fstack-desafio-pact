import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DadosUsuario, Usuario } from '../model';

@Injectable({
  providedIn: 'root',
})
export class registrarService {
  private $registra = 'http://localhost:8081/auth/register';
  private $registraDados = 'http://localhost:8081/auth/register/usuarios/criar';

  constructor(public http: HttpClient) {}

  registrar(data: any): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.$registra}/register`, data, {
      observe: 'response',
    });
  }

  registrarDadosUsuario(dadosUsuario: DadosUsuario) {
    return this.http.post<DadosUsuario>(this.$registraDados, dadosUsuario, {
      responseType: 'json',
    });
  }
}
