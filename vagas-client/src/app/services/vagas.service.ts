import { Injectable } from '@angular/core';
import { Vaga } from '../dashboard/vagas/vaga.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root',
})
export class VagaService {
  $api = 'http://localhost:8081/vagas';
  constructor(public http: HttpClient) {}

  obterVagasHome(): Observable<Vaga[]> {
    return this.http.get<Vaga[]>(`${this.$api}/listar`, {
      responseType: 'json',
    });
  }

  obterVagasPorAnalista(id: any): Observable<any> {
    return this.http.get<any>(`${this.$api}/vagas-analista`, {
      params: { id_analista: id },
    });
  }

  obterStatusPorAnalista(id: any): Observable<any> {
    return this.http.get<any>(`${this.$api}/vagas-dash`, {
      params: { id_analista: id },
    });
  }

  salvarVaga(vaga: Vaga): Observable<Vaga> {
    return this.http.post<Vaga>(`${this.$api}/criar`, vaga, {
      responseType: 'json',
    });
  }

  deletarVaga(id: any) {
    return this.http.delete(this.$api, { params: { id } });
  }

  obterVagasAbertas() {
    throw new Error('Method not implemented.');
  }
}
