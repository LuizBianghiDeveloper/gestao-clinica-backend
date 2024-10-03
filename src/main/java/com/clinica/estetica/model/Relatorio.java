package com.clinica.estetica.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "relatorios")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelatorio;

    @NotBlank(message = "O tipo de relatório é obrigatório")
    @Column(nullable = false)
    private String tipoRelatorio; // Ex: "Evolução", "Anamnese", "Paciente"

    @NotNull(message = "A data de geração é obrigatória")
    @PastOrPresent(message = "A data de geração não pode ser futura")
    @Column(nullable = false)
    private LocalDate dataGeracao;

    // Relacionamentos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Construtores

    public Relatorio() {
    }

    public Relatorio(String tipoRelatorio, LocalDate dataGeracao, Paciente paciente) {
        this.tipoRelatorio = tipoRelatorio;
        this.dataGeracao = dataGeracao;
        this.paciente = paciente;
    }

    // Getters e Setters

    public Long getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(Long idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
