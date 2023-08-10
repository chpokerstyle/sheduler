package com.example.demo.service;

import com.example.demo.pojo.BookingsEntity;
import com_2fexample_2fdemo.Tables;
import com_2fexample_2fdemo.tables.AirportsData;
import com_2fexample_2fdemo.tables.records.BookingsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirServiceImpl implements AirService {
    private final DSLContext context;

    @Override
    public List<BookingsEntity> getBookingsAll() {
        Result<BookingsRecord> bookingsRecordStream = context.selectFrom(Tables.BOOKINGS_).fetch();
        List<BookingsEntity> bookingsEntityList = new ArrayList<>();
        bookingsRecordStream.stream().forEach(
                a -> {
                    BookingsEntity b = BookingsEntity.builder()
                            .totalAmount(a.getTotalAmount())
                            .bookRef(a.getBookRef())
                            .bookDate(a.getBookDate())
                            .build();
                    bookingsEntityList.add(b);
                }
        );
        return bookingsEntityList;
    }

    @Override
    public List<String> complexQuery(){
        AirportsData dep = Tables.AIRPORTS_DATA.as("dep");
        AirportsData arl = Tables.AIRPORTS_DATA.as("arl");

        var query = context.select(dep.AIRPORT_NAME,arl.AIRPORT_NAME)
                .from(Tables.FLIGHTS)
                    .join(dep)
                        .on(Tables.FLIGHTS.DEPARTURE_AIRPORT.eq(dep.AIRPORT_CODE))
                    .join(arl)
                        .on(Tables.FLIGHTS.ARRIVAL_AIRPORT.eq(arl.AIRPORT_CODE))
                .join(Tables.TICKET_FLIGHTS).on(Tables.TICKET_FLIGHTS.FLIGHT_ID.eq(Tables.FLIGHTS.FLIGHT_ID))
                .join(Tables.TICKETS).on(Tables.TICKETS.TICKET_NO.eq(Tables.TICKET_FLIGHTS.TICKET_NO))
                .join(Tables.BOOKINGS_).on(Tables.BOOKINGS_.BOOK_REF.eq(Tables.TICKETS.BOOK_REF))
                .where(Tables.BOOKINGS_.BOOK_REF.eq("00006B")).and(Tables.TICKET_FLIGHTS.FLIGHT_ID.eq(Tables.FLIGHTS.FLIGHT_ID))
                .fetch();
        System.out.println(query);
        return query.stream().map(Objects::toString).collect(Collectors.toList());
    }
}
