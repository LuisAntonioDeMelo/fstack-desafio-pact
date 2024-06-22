import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../model';

@Injectable({
  providedIn: 'root',
})
export class registrarService {
  $api = 'http://localhost:8081/auth/register';

  constructor(public http: HttpClient) {}

  registrar(usuario: Usuario): Observable<void> {
    return this.http.post<void>(this.$api, usuario, {
      responseType: 'json',
    });
  }
}
