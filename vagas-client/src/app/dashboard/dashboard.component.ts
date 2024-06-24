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
import { MatBadgeModule } from '@angular/material/badge';
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
    MatBadgeModule,
    CustomSidenavComponent,
  ],
})
export class DashboardComponent implements OnInit {
  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    public loginService: LoginService
  ) {}

  collapsed = signal(false);

  sidenaWidth = computed(() => (this.collapsed() ? '65px' : '250px'));

  nomeUsuario: string = '';
  usuarioRole: string = '';
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
        this.nomeUsuario = res.pessoa.nome;
        this.usuarioRole = res.role;
      },
      error: (error) => {},
    });
  }
}
