package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class XmlObject {
    @JsonProperty("ID")
    private Long id;
    @JsonProperty("OBJECTID")
    private Long objectid;
    @JsonProperty("OBJECTGUID")
    private UUID objectguid;
    @JsonProperty("CHANGEID")
    private Long changeid;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("TYPENAME")
    private String typename;
    @JsonProperty("LEVEL")
    private Long level;
    @JsonProperty("OPERTYPEID")
    private Long opertypeid;
    @JsonProperty("PREVID")
    private Long previd;
    @JsonProperty("NEXTID")
    private Long nextid;
    @JsonProperty("UPDATEDATE")
    private LocalDate updatedate;
    @JsonProperty("STARTDATE")
    private LocalDate startdate;
    @JsonProperty("ENDDATE")
    private LocalDate enddate;
    @JsonProperty("ISACTUAL")
    private Byte isactual;
    @JsonProperty("ISACTIVE")
    private Byte isactive;
}
