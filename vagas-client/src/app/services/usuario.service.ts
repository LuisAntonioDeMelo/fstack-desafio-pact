import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  $api = 'http://localhost:8081/usuarios';
  constructor(public http: HttpClient) {}

  obterUsuario(data: any): Observable<any> {
    return this.http.get<any>(this.$api, { params: data });
  }
}
