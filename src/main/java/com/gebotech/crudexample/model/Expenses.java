package com.gebotech.crudexample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gebotech.crudexample.model.request.ExpenseRequest;
import com.gebotech.crudexample.model.response.ExpenseResponse;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "expenses")
@EntityListeners(AuditingEntityListener.class)
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 35)
    private String name;

    @NotBlank
    private boolean payed;

    @NotBlank
    private int installments;

    @NotBlank
    private LocalDateTime expireAt;

    public Expenses() {

    }

    @NotBlank
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotBlank
    private boolean recurrent;

    @NotBlank
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public static ExpenseResponse toResponse(Expenses expenses) {
        return ExpenseResponse.builder()
                .name(expenses.name)
                .payed(expenses.payed)
                .installments(expenses.installments)
                .expireAt(expenses.expireAt)
                .createdAt(expenses.createdAt)
                .recurrent(expenses.recurrent)
                .value(expenses.value).build();
    }

    public static Expenses toEntity(ExpenseRequest request) {
        //@TODO: maybe this can be changed after we add the front end logic. For now I don't want to deal with dates on postman.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime expireAt = LocalDateTime.parse(request.getExpireAt(), formatter);

        return new ExpensesBuilder()
                .name(request.getName())
                .payed(request.isPayed())
                .installments(request.getInstallments())
                .expireAt(expireAt)
                .recurrent(request.isRecurrent())
                .value(request.getValue())
                .build();
    }

}
