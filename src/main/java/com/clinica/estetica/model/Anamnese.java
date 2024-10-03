package com.clinica.estetica.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "anamneses")
public class Anamnese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnamnese;

    @NotNull(message = "A data de preenchimento é obrigatória")
    @PastOrPresent(message = "A data de preenchimento não pode ser futura")
    @Column(nullable = false)
    private LocalDate dataPreenchimento;

    @NotBlank(message = "O histórico médico é obrigatório")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String historicoMedico;

    @NotBlank(message = "Os medicamentos atuais são obrigatórios")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String medicamentosAtuais;

    @NotBlank(message = "As alergias são obrigatórias")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String alergias;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // Relacionamentos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    // Construtores

    public Anamnese() {
    }

    public Anamnese(LocalDate dataPreenchimento, String historicoMedico, String medicamentosAtuais, String alergias, String observacoes, Paciente paciente) {
        this.dataPreenchimento = dataPreenchimento;
        this.historicoMedico = historicoMedico;
        this.medicamentosAtuais = medicamentosAtuais;
        this.alergias = alergias;
        this.observacoes = observacoes;
        this.paciente = paciente;
    }

    // Getters e Setters

    public Long getIdAnamnese() {
        return idAnamnese;
    }

    public void setIdAnamnese(Long idAnamnese) {
        this.idAnamnese = idAnamnese;
    }

    public LocalDate getDataPreenchimento() {
        return dataPreenchimento;
    }

    public void setDataPreenchimento(LocalDate dataPreenchimento) {
        this.dataPreenchimento = dataPreenchimento;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    public String getMedicamentosAtuais() {
        return medicamentosAtuais;
    }

    public void setMedicamentosAtuais(String medicamentosAtuais) {
        this.medicamentosAtuais = medicamentosAtuais;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
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
