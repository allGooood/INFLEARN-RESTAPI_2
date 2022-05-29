package me.whiteship.demoinflearnrestapi.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class EventResource extends EntityModel<Event> {
// extends RepresentationModel 사용 시 ↓
//    @JsonUnwrapped
//    private Event event;
//
//    public EventResource(Event event) {
//        this.event = event;
//    }
//
//    public Event getEvent() {
//        return event;
//    }

    public EventResource(Event event, Link... links) {
        super(event, Arrays.asList(links));
        // add(new Link("http://localhost080/api/events" + event.getId())); --> TypeSafe 하지않은 방법
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }
}
