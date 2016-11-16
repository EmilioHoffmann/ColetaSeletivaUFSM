package br.ufsm.coletaseletivaufsm.containers;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import java.io.Serializable;

/**
 * Entity mapped to table "CONTAINERS".
 */
public class Containers implements Serializable {

    private Long id;
    private String nome;
    private Double latitude;
    private Double longitude;
    private String descricao;
    private Boolean segunda;
    private Boolean terca;
    private Boolean quarta;
    private Boolean quinta;
    private Boolean sexta;
    private String linkfoto;

    public Containers() {
    }

    public Containers(Long id) {
        this.id = id;
    }

    public Containers(Long id, String nome, Double latitude, Double longitude, String descricao, Boolean segunda, Boolean terca, Boolean quarta, Boolean quinta, Boolean sexta, String linkfoto) {
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descricao = descricao;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.linkfoto = linkfoto;
    }

    public Containers(String nome, Double latitude, Double longitude, String descricao, Boolean segunda, Boolean terca, Boolean quarta, Boolean quinta, Boolean sexta, String linkfoto) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descricao = descricao;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.linkfoto = linkfoto;
    }

    public Containers (String linha){
        this.nome = linha.split(",")[0];
        this.latitude = Double.parseDouble(linha.split(",")[1]);
        this.longitude = Double.parseDouble(linha.split(",")[2]);
        this.descricao = linha.split(",")[3];
        this.segunda = Boolean.parseBoolean(linha.split(",")[4]);
        this.terca = Boolean.parseBoolean(linha.split(",")[5]);
        this.quarta = Boolean.parseBoolean(linha.split(",")[6]);
        this.quinta = Boolean.parseBoolean(linha.split(",")[7]);
        this.sexta = Boolean.parseBoolean(linha.split(",")[8]);
        this.linkfoto = linha.split(",")[9];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getSegunda() {
        return segunda;
    }

    public void setSegunda(Boolean segunda) {
        this.segunda = segunda;
    }

    public Boolean getTerca() {
        return terca;
    }

    public void setTerca(Boolean terca) {
        this.terca = terca;
    }

    public Boolean getQuarta() {
        return quarta;
    }

    public void setQuarta(Boolean quarta) {
        this.quarta = quarta;
    }

    public Boolean getQuinta() {
        return quinta;
    }

    public void setQuinta(Boolean quinta) {
        this.quinta = quinta;
    }

    public Boolean getSexta() {
        return sexta;
    }

    public void setSexta(Boolean sexta) {
        this.sexta = sexta;
    }

    public String getLinkfoto() {
        return linkfoto;
    }

    public void setLinkfoto(String linkfoto) {
        this.linkfoto = linkfoto;
    }

}
