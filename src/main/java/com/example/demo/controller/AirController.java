package com.example.demo.controller;

import com.example.demo.pojo.BookingsEntity;
import com.example.demo.service.AirService;
import com_2fexample_2fdemo.tables.Aircrafts;
import com_2fexample_2fdemo.tables.records.AircraftsRecord;
import com_2fexample_2fdemo.tables.records.BookingsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.JSONB;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controller")
@RequiredArgsConstructor
public class AirController {
    private final AirService service;

    @GetMapping("/service")
    public List<BookingsEntity> getData(){
       return service.getBookingsAll();
    }

    @GetMapping("service2")
    public Result<Record2<JSONB, JSONB>> complexQuery(){
        return service.complexQuery();
    }
}
