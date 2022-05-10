package com.dubajandrzej.coronovirus.Tracker.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class CoronaVirusDataService {
    private static String VIRUS_DATA_STRING = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/who_covid_19_situation_reports/who_covid_19_sit_rep_time_series/who_covid_19_sit_rep_time_series.csv";

    @PostConstruct
    public void fetchVirusData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_STRING)).build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(httpResponse.body());
    }
}

