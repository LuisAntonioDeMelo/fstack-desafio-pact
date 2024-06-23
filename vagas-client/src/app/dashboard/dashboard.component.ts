import { Component, OnInit, computed, inject, signal } from '@angular/core';
import { AdminComponent } from './admin/admin.component';
import { CandidatosComponent } from './candidatos/candidatos.component';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { MatToolbar } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {
  MatSidenav,
  MatSidenavContainer,
  MatSidenavContent,
} from '@angular/material/sidenav';
import { CustomSidenavComponent } from './custom-sidenav/custom-sidenav.component';
import { LoginService } from '../auth/login/login.service';
import { UsuarioService } from '../auth/usuario.service';

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
export class DashboardComponent implements OnInit {
  loginService = inject(LoginService);
  router = inject(Router);

  constructor(private usuarioService: UsuarioService) {}

  collapsed = signal(false);

  sidenaWidth = computed(() => (this.collapsed() ? '65px' : '250px'));

  ngOnInit(): void {
    this.obterUsuario();
  }

  logout() {
    this.loginService.removeToken();
    this.router.navigate(['']);
  }

  obterUsuario() {
    const { id } = this.loginService.obterUsuario();
    this.usuarioService.obterUsuario({ id: id }).subscribe({
      next: (res) => {
        console.log(res);
      },
      error: (error) => {},
    });
  }
}
