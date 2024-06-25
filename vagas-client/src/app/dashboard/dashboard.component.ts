import { Component, OnInit, computed, signal } from '@angular/core';
import { AdminComponent } from './admin/admin.component';
import { CandidatosComponent } from './candidatos/candidatos.component';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { MatToolbar } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import {
  MatSidenav,
  MatSidenavContainer,
  MatSidenavContent,
} from '@angular/material/sidenav';
import { MatBadgeModule } from '@angular/material/badge';
import { CustomSidenavComponent } from './custom-sidenav/custom-sidenav.component';
import { LoginService } from '../services/login.service';
import { NotificacacoService } from '../services/notificacao.service';
import { UsuarioService } from '../services/usuario.service';

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
    MatMenuModule,
  ],
})
export class DashboardComponent implements OnInit {
  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    public loginService: LoginService,
    private notificacaoService: NotificacacoService
  ) {}

  collapsed = signal(false);

  sidenaWidth = computed(() => (this.collapsed() ? '65px' : '250px'));

  nomeUsuario: string = '';
  usuarioRole: string = '';
  notificacacoes: Notificacao[] = [];

  ngOnInit(): void {
    this.obterUsuario();
  }

  logout() {
    this.loginService.removeToken();
    localStorage.removeItem('id_user_role');
    this.router.navigate(['']);
  }

  obterUsuario() {
    const { id } = this.loginService.obterUsuario();
    this.usuarioService.obterUsuario({ id: id }).subscribe({
      next: (res) => {
        console.log(res);
        this.nomeUsuario = res.pessoa.nome;
        this.usuarioRole = res.role;
        this.defineRole(res);
      },
      error: (error) => {},
    });
  }

  defineRole(response: any) {
    if (response.role === 'ANALISTA_RH') {
      const id = response.pessoa.analista.id;
      localStorage.setItem('id_user_role', id);
      this.obterNotificacoesAnalista(id);
    } else {
      const id = response.pessoa.candidato.id;
      localStorage.setItem('id_user_role', id);
      this.obterNotificacoesCandidato(id);
    }
  }

  obterNotificacoesAnalista(idUser: any) {
    this.notificacaoService.obterNotificacoesAnalista(idUser).subscribe({
      next: (res: Notificacao[]) => {
        console.log(res);
        this.notificacacoes = res;
      },
      error: (err) => {},
    });
  }

  obterNotificacoesCandidato(idUser: any) {
    this.notificacaoService.obterNotificacoesCandidato(idUser).subscribe({
      next: (res: Notificacao[]) => {
        console.log(res);
        this.notificacacoes = res;
      },
      error: (err) => {},
    });
  }
}

interface Notificacao {
  message: string;
}
