import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DadosUsuario, Usuario } from '../auth/model';

@Injectable({
  providedIn: 'root',
})
export class RegistrarService {
  private $register = 'http://localhost:8081/auth/register';
  private $api = 'http://localhost:8081/usuarios';

  constructor(public http: HttpClient) {}

  registrarAuth(data: any): Observable<string> {
    return this.http.post<string>(this.$register, data, {
      responseType: 'text' as 'json',
    });
  }

  registrarDadosUsuario(dadosUsuario: DadosUsuario) {
    return this.http.post<DadosUsuario>(`${this.$api}/criar`, dadosUsuario, {
      responseType: 'json',
    });
  }
}
