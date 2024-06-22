import { Component } from '@angular/core';
import { AdminComponent } from '../admin/admin.component';
import { CandidatosComponent } from '../candidatos/candidatos.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  imports: [AdminComponent, CandidatosComponent],
})
export class DashboardComponent {}
