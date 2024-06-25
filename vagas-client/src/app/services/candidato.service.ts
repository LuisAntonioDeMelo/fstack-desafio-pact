import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CandidatoSerive {
  $api = 'http://localhost:8081/candidatos';
  constructor(public http: HttpClient) {}

  cadidatarParaVaga(data: any) {
    return this.http.post(`${this.$api}/cadastrar-candidatura`, data, {
      responseType: 'json',
    });
  }
}
