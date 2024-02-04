package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

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

    public Long getId() {
        return id;
    }

    public Long getObjectid() {
        return objectid;
    }

    public UUID getObjectguid() {
        return objectguid;
    }

    public Long getChangeid() {
        return changeid;
    }

    public String getName() {
        return name;
    }

    public String getTypename() {
        return typename;
    }

    public Long getLevel() {
        return level;
    }

    public Long getOpertypeid() {
        return opertypeid;
    }

    public Long getPrevid() {
        return previd;
    }

    public Long getNextid() {
        return nextid;
    }

    public LocalDate getUpdatedate() {
        return updatedate;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public Byte getIsactual() {
        return isactual;
    }

    public Byte getIsactive() {
        return isactive;
    }
}
