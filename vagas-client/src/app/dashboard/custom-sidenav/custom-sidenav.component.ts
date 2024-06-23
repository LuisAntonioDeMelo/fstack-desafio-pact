import { CommonModule } from '@angular/common';
import { Component, Input, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { RouterLink, RouterModule } from '@angular/router';

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

  menuItems = signal<MenuItem[]>([
    {
      icon: 'dashboard',
      label: 'Dashboard',
      route: 'dashboard-content',
    },
    {
      icon: 'analytics',
      label: 'Admin',
      route: 'admin',
    },
    {
      icon: 'analytics',
      label: 'Vagas',
      route: 'vagas',
    },
    {
      icon: 'dashboard',
      label: 'Candidatos',
      route: 'candidatos',
    },
  ]);
}
