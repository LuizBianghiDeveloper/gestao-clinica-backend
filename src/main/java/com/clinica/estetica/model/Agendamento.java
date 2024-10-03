package com.clinica.estetica.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    @NotNull(message = "A data e hora do agendamento são obrigatórias")
    @Future(message = "A data e hora do agendamento devem ser futuras")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @NotBlank(message = "O status é obrigatório")
    @Pattern(regexp = "Confirmado|Cancelado|Pendente", message = "Status inválido")
    @Column(nullable = false)
    private String status; // Ex: "Confirmado", "Cancelado", "Pendente"

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // Relacionamentos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Construtores

    public Agendamento() {
    }

    public Agendamento(LocalDateTime dataHora, String status, String observacoes, Paciente paciente, Profissional profissional, Sala sala, Usuario usuario) {
        this.dataHora = dataHora;
        this.status = status;
        this.observacoes = observacoes;
        this.paciente = paciente;
        this.profissional = profissional;
        this.sala = sala;
        this.usuario = usuario;
    }

    // Getters e Setters

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
