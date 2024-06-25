import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UUID } from 'angular2-uuid';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NotificacacoService {
  $api = 'http://localhost:8081/notificacoes';

  constructor(public http: HttpClient) {}

  obterNotificacoesCandidato(id: any): Observable<any> {
    return this.http.get(`${this.$api}/candidato`, {
      params: { id },
    });
  }

  obterNotificacoesAnalista(id: any): Observable<any> {
    return this.http.get(`${this.$api}/analista`, {
      params: { id },
    });
  }
}
