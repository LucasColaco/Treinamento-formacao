package com.basis.campina.xtarefas.dominio.elasticsearch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Getter
@Setter
@Document(indexName = "xtarefas-tarefa")
@NoArgsConstructor
public class TarefaDocument extends BaseDocument{
    private static final long serialVersionUID = -243928137302411529L;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String nome;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true,
                    format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dtInicio;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true,
                    format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dtConclusao;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String status;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String responsavel;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true), otherFields = {
            @InnerField(suffix = SORT, type = FieldType.Text, store = true, analyzer = TRIM_CASE_INSENSITIVE, fielddata = true)})
    private String anexos;

    public TarefaDocument(Integer id, String nome, LocalDate dtInicio, LocalDate dtConclusao, String status, String responsavel){
        this.id = id;
        this.nome = nome;
        this.dtInicio = dtInicio != null ? dtInicio.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null;
        this.dtConclusao = dtConclusao != null ? dtConclusao.format(DateTimeFormatter.ofPattern(DATE_PATTERN)) : null;
        this.status = status;
        this.responsavel = responsavel;
    }
}
