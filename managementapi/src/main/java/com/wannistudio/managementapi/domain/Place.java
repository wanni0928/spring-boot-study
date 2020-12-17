package com.wannistudio.managementapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class Place {
    private String address;
    private String city;
    private String postalCode;
    private String country;

    public Place() {
    }
}
