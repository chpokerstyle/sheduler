package com.example.demo.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BookingsEntity {

    private String bookRef;
    private OffsetDateTime bookDate;
    private BigDecimal totalAmount;
}
