package com.vagas.app.application.services;

import com.vagas.app.domain.model.Candidato;
import com.vagas.app.domain.model.Notificacao;
import com.vagas.app.domain.model.Vaga;
import com.vagas.app.infra.repository.NotificacaoRepository;
import com.vagas.app.infra.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    private final static String MSG_NOTIFICACAO_CANDIDATO = "Você candidatou a vaga {0} fique no aguardo que iremos te contactar";
    private final static String MSG_NOTIFICACAO_ANALISTA = "Candidato {0} aplicou para vaga {1}: {2}";

    public void gerarNotificacaoCandidato(Candidato candidato, String codigoVaga) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(
                MessageFormat.format(MSG_NOTIFICACAO_CANDIDATO, codigoVaga));
        notificacao.setCandidato(candidato);
        notificacaoRepository.save(notificacao);
    }

    public void gerarNotificacaoAnalista(Candidato candidato, Vaga vaga) {
        Notificacao notificacao = new Notificacao();

        String messageFormat =
                MessageFormat.format(MSG_NOTIFICACAO_ANALISTA, candidato.getPessoa().getNome(),
                        vaga.getTitulo(),
                        vaga.getCodigoVaga());

        notificacao.setMensagem(messageFormat);

        notificacao.setCandidato(candidato);
        vaga.getAnalistas().forEach(analistaRH -> {
            notificacao.setAnalista(analistaRH);
            notificacaoRepository.save(notificacao);
        });
    }

    public List<NotificacaoResponse> obterNotificacaoPorCandidato(UUID id) {
        var list = notificacaoRepository.findByCandidatoId(id)
                .stream()
                .filter(this::isAnalistaNull)
                .toList();

        return list.stream()
                .map(notificacao -> new NotificacaoResponse(notificacao.getMensagem())).toList();
    }


    public List<NotificacaoResponse> obterNotificacaoPorAnalista(UUID id) {
        var list = notificacaoRepository.findByAnalistaId(id)
                .stream()
                .filter(this::isNotAnalistaNull)
                .toList();

        return list.stream()
                .map(notificacao -> new NotificacaoResponse(notificacao.getMensagem())).toList();
    }

    private boolean isAnalistaNull(Notificacao notificacao) {
        return ObjectUtils.isEmpty(notificacao.getAnalista());
    }

    private boolean isNotAnalistaNull(Notificacao notificacao) {
        return !ObjectUtils.isEmpty(notificacao.getAnalista());
    }
}
