import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CandidatoSerive {
  $api = 'http://localhost:8081/cadidatos';
  constructor(public http: HttpClient) {}
}
