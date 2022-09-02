package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Reserved extends AbstractEvent {

    private Long id;
    private Integer roomId;

    public Reserved(Conference aggregate) {
        super(aggregate);
    }

    public Reserved() {
        super();
    }
    // keep

}
