package me.whiteship.demoinflearnrestapi.events;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

// JUNIT4 사용
@RunWith(JUnitParamsRunner.class)
public class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder()
                // Builder --> 각 필드별로 어떤 값을 넣었는지 보기 쉬움
                .name("Inflearn Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean(){
        // Given
        String name = "Event";
        String description = "Description";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }

    @Test
//    @Parameters({
//            "0, 0, true",
//            "100, 0, false",
//            "0, 100, false"
//    })
    @Parameters
    public void testFree(int basePrice, int maxPrice, boolean isFree){
        // Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
        // When
        event.update();
        // Then
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    private Object[] parametersForTestFree(){
        return new Object[]{
                new Object[] {0, 0, true},
                new Object[] {100, 0, false},
                new Object[] {0, 100, false},
                new Object[] {100, 200, false}
        };
    }

    @Test
    @Parameters
    public void testOffline(String location, boolean isOffline){
        //Given
        Event event = Event.builder()
                .location(location)
                .build();
        //When
        event.update();
        //Then
        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private Object[] parametersForTestOffline(){
        return new Object[] {
                new Object[] {"강남", true},
                new Object[] {null, false},
                new Object[] {" ", false}
        };
    }
}