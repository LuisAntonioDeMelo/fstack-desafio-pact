import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AnalistaService {
  $api = 'http://localhost:8081/analistas';
  constructor(public http: HttpClient) {}

  obterCandidatosIngredsoAnalista(id: any): Observable<any> {
    return this.http.get(`${this.$api}/exibir-candidatos`, {
      params: { id },
    });
  }
}
