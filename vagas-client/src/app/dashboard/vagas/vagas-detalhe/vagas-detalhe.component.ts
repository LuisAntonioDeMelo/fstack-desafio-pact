import { Component, inject, signal } from '@angular/core';
import { Vaga, Status, Prioridade, Requisito } from '../vaga.model';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import {
  MatChipEditedEvent,
  MatChipInputEvent,
  MatChipsModule,
} from '@angular/material/chips';
import { VButtonModule } from '../../../components/custom.module';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-vagas-detalhe',
  standalone: true,
  imports: [
    MaterialModule,
    FormsModule,
    MatChipsModule,
    MatIconModule,
    VButtonModule,
  ],
  templateUrl: './vagas-detalhe.component.html',
  styleUrl: './vagas-detalhe.component.css',
})
export class VagasDetalheComponent {
  vaga: Vaga = new Vaga(
    '',
    '',
    '',
    '',
    new Date(),
    '',
    0,
    Status.CRIADA,
    Prioridade.ALTA,
    new Date()
  );
  readonly addOnBlur = true;
  readonly requisitos = signal<Requisito[]>([]);

  readonly announcer = inject(LiveAnnouncer);

  constructor() {
    this.vaga;
  }

  onSubmit() {
    throw new Error('Method not implemented.');
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value) {
      this.requisitos.update((requisitos) => [...requisitos, { name: value }]);
    }

    event.chipInput!.clear();
  }

  remove(requisito: Requisito): void {
    this.requisitos.update((requisitos) => {
      const index = requisitos.indexOf(requisito);
      if (index < 0) {
        return requisitos;
      }

      requisitos.splice(index, 1);
      this.announcer.announce(`Removed ${requisito.name}`);
      return [...requisitos];
    });
  }

  edit(requisito: Requisito, event: MatChipEditedEvent) {
    const value = event.value.trim();
    if (!value) {
      this.remove(requisito);
      return;
    }

    this.requisitos.update((requisitos) => {
      const index = requisitos.indexOf(requisito);
      if (index >= 0) {
        requisitos[index].name = value;
        return [...requisitos];
      }
      return requisitos;
    });
  }
}
