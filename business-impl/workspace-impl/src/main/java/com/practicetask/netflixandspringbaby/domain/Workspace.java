package com.practicetask.netflixandspringbaby.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int unit;
    private int seat;
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private OSFamily osFamily;

    public Workspace(int unit, int seat, String serialNumber, OSFamily osFamily) {
        this.unit = unit;
        this.seat = seat;
        this.serialNumber = serialNumber;
        this.osFamily = osFamily;
    }
}
