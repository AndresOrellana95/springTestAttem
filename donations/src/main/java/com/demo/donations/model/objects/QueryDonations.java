package com.demo.donations.model.objects;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class QueryDonations {
    private Date start;
    private Date end;


    public QueryDonations() {
    }

    public QueryDonations(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return this.start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public QueryDonations start(Date start) {
        this.start = start;
        return this;
    }

    public QueryDonations end(Date end) {
        this.end = end;
        return this;
    }
}
