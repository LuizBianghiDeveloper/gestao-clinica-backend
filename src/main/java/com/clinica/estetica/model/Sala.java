package com.clinica.estetica.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSala;

    @NotBlank(message = "O número da sala é obrigatório")
    @Column(nullable = false, unique = true)
    private String numero;

    @NotBlank(message = "O tipo da sala é obrigatório")
    @Pattern(regexp = "Tratamento|Consulta|Outro", message = "Tipo de sala inválido")
    @Column(nullable = false)
    private String tipo; // Ex: "Tratamento", "Consulta"

    @Column(columnDefinition = "TEXT")
    private String descricao;

    // Relacionamentos

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos = new ArrayList<>();

    // Construtores

    public Sala() {
    }

    public Sala(String numero, String tipo, String descricao) {
        this.numero = numero;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    // Getters e Setters

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
