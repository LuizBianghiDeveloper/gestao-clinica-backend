package com.clinica.estetica.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "evolucoes")
public class Evolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvolucao;

    @NotNull(message = "A data da evolução é obrigatória")
    @PastOrPresent(message = "A data da evolução não pode ser futura")
    @Column(nullable = false)
    private LocalDate dataEvolucao;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // Relacionamentos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    // Construtores

    public Evolucao() {
    }

    public Evolucao(LocalDate dataEvolucao, String descricao, String observacoes, Paciente paciente) {
        this.dataEvolucao = dataEvolucao;
        this.descricao = descricao;
        this.observacoes = observacoes;
        this.paciente = paciente;
    }

    // Getters e Setters

    public Long getIdEvolucao() {
        return idEvolucao;
    }

    public void setIdEvolucao(Long idEvolucao) {
        this.idEvolucao = idEvolucao;
    }

    public LocalDate getDataEvolucao() {
        return dataEvolucao;
    }

    public void setDataEvolucao(LocalDate dataEvolucao) {
        this.dataEvolucao = dataEvolucao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}
