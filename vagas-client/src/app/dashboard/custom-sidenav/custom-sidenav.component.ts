import { CommonModule } from '@angular/common';
import { Component, Input, inject, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { RouterLink, RouterModule } from '@angular/router';
import { LoginService } from '../../auth/login/login.service';

export type MenuItem = {
  icon: string;
  label: string;
  route?: string;
};

@Component({
  selector: 'app-custom-sidenav',
  standalone: true,
  imports: [CommonModule, MatListModule, MatIconModule, RouterModule],
  templateUrl: './custom-sidenav.component.html',
  styleUrl: './custom-sidenav.component.css',
})
export class CustomSidenavComponent {
  sidenavCollapsed = signal(false);
  @Input() set collapsed(val: boolean) {
    this.sidenavCollapsed.set(val);
  }
  loginService = inject(LoginService);

  @Input() nomeUsuario: string = '';
  @Input() role: string = '';

  menuItems = this.loginService.hasPermission('analista')
    ? signal<MenuItem[]>([
        {
          icon: 'dashboard',
          label: 'Dashboard',
          route: 'dashboard-content',
        },
        {
          icon: 'analytics',
          label: 'Vagas',
          route: 'vagas',
        },
        {
          icon: 'account_box',
          label: 'Candidatos',
          route: 'candidatos',
        },
        {
          icon: 'admin_panel_settings',
          label: 'Admin',
          route: 'admin',
        },
      ])
    : signal<MenuItem[]>([
        {
          icon: 'dashboard',
          label: 'Dashboard',
          route: 'dashboard-content',
        },
        {
          icon: 'analytics',
          label: 'Vagas Disponiveis',
          route: 'vagas',
        },
        {
          icon: 'account_box',
          label: 'Area Candidato',
          route: 'candidatos',
        },
      ]);
}
