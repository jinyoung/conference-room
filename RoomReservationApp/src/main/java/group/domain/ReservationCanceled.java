package group.domain;

import group.domain.*;
import group.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReservationCanceled extends AbstractEvent {

    private Long id;

    public ReservationCanceled(Conference aggregate) {
        super(aggregate);
    }

    public ReservationCanceled() {
        super();
    }
    // keep

}
