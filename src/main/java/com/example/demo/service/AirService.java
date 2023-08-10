package com.example.demo.service;

import com.example.demo.pojo.BookingsEntity;
import org.jooq.JSONB;
import org.jooq.Record2;
import org.jooq.Result;

import java.util.List;

public interface AirService {
    List<BookingsEntity> getBookingsAll();

    List<String> complexQuery();
}
