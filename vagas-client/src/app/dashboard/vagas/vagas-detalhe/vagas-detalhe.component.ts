import {
  ChangeDetectionStrategy,
  Component,
  OnInit,
  inject,
  signal,
} from '@angular/core';
import { Vaga, Status, Prioridade, Requisito, TipoVaga } from '../vaga.model';
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
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { DialogVaga } from '../dialog/vaga.dialog.component';
import { VagaService } from '../../../services/vagas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-vagas-detalhe',
  standalone: true,
  imports: [
    MaterialModule,
    FormsModule,
    MatChipsModule,
    MatIconModule,
    VButtonModule,
    CommonModule,
  ],
  templateUrl: './vagas-detalhe.component.html',
  styleUrl: './vagas-detalhe.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class VagasDetalheComponent implements OnInit {
  vaga: Vaga = new Vaga(
    '',
    '',
    '',
    '',
    '',
    new Date(),
    '',
    0,
    Status.CRIADA,
    Prioridade.MEDIA,
    new Date(),
    [],
    TipoVaga.REMOTO
  );
  readonly addOnBlur = true;
  readonly requisitos = signal<Requisito[]>([]);
  readonly separatorKeysCodes: number[] = [13, 188];
  readonly announcer = inject(LiveAnnouncer);
  readonly hasError = signal(true);
  readonly dialog = inject(MatDialog);
  router = inject(Router);

  vagaService = inject(VagaService);
  activetedRoute: ActivatedRoute = inject(ActivatedRoute);
  loginService = inject(LoginService);

  constructor() {
    this.vaga;
  }

  ngOnInit(): void {
    const vagaEditar = this.activetedRoute.snapshot.paramMap.get('vaga');
    if (vagaEditar) {
      this.popularObjeto(vagaEditar);

      this.vaga.requisitos.forEach((re) => {
        this.requisitos.update((requisitos) => [
          ...requisitos,
          { nome: re.nome },
        ]);
      });
    }

    if (this.loginService.hasPermission('candidato')) {
      const vagaDetalhe =
        this.activetedRoute.snapshot.paramMap.get('vagaDetalhe');
      this.vaga = JSON.parse(vagaDetalhe as string);
    }
  }

  private popularObjeto(vagaEditar: string) {
    const vagaObject = JSON.parse(vagaEditar as string);

    this.vaga = vagaObject as Vaga;
    this.vaga.dataCriacao = parseDate(vagaObject.dataCriacao);
    this.vaga.dataVencimento = parseDate(vagaObject.dataVencimento);
  }

  onSubmit() {
    this.vaga.idAnalistaResp = localStorage.getItem('id_user_role') as string;
    this.vaga.requisitos = [...this.requisitos.call(this)];
    this.vagaService.salvarVaga(this.vaga).subscribe({
      next: (response) => {
        this.router.navigate(['/dashboard/vagas/']);
      },
      error: (error) => {
        console.log(error.error);
      },
    });
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value) {
      this.requisitos.update((requisitos) => [...requisitos, { nome: value }]);
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
      this.announcer.announce(`Removed ${requisito.nome}`);
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
        requisitos[index].nome = value;
        return [...requisitos];
      }
      return requisitos;
    });
  }
}

function parseDate(dateArray: number[]): Date {
  if (!dateArray) return new Date();

  if (dateArray.length !== 3) {
    throw new Error('data invalida');
  }
  const date = new Date(Date.UTC(dateArray[0], dateArray[1] - 1, dateArray[2]));
  if (isNaN(date.getTime())) {
    throw new Error('Data inválida.');
  }
  return date;
}
