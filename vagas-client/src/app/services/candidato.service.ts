import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
interface Response {
  response: string;
}

@Injectable({
  providedIn: 'root',
})
export class CandidatoService {
  $api = 'http://localhost:8081/candidatos';
  constructor(public http: HttpClient) {}

  cadidatarParaVaga(data: any) {
    return this.http.post<Response>(
      `${this.$api}/cadastrar-candidatura`,
      data,
      {
        responseType: 'json',
      }
    );
  }

  obterVagasIngressadas(id: any): Observable<any> {
    return this.http.get(`${this.$api}/exibir-candidatos`, {
      params: { id },
    });
  }
}
