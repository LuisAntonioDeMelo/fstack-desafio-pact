import { Component, computed, signal } from '@angular/core';
import { AdminComponent } from './admin/admin.component';
import { CandidatosComponent } from './candidatos/candidatos.component';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatToolbar } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {
  MatSidenav,
  MatSidenavContainer,
  MatSidenavContent,
} from '@angular/material/sidenav';
import { CustomSidenavComponent } from './custom-sidenav/custom-sidenav.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  imports: [
    AdminComponent,
    CandidatosComponent,
    CommonModule,
    RouterOutlet,
    MatToolbar,
    MatButtonModule,
    MatIconModule,
    MatSidenavContainer,
    MatSidenav,
    MatSidenavContent,
    CustomSidenavComponent,
  ],
})
export class DashboardComponent {
  collapsed = signal(false);

  sidenaWidth = computed(() => (this.collapsed() ? '65px' : '250px'));
}
