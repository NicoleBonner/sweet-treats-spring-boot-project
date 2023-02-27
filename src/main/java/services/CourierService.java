package services;

import models.Courier;
import models.OrderDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourierService {

    List<Courier> listOfCouriers() {
        return List.of(
                new Courier("Bobby Bikes", LocalTime.of(9, 00), LocalTime.of(13, 00), 5.00, true, 1.75),
                new Courier("Martin Moves", LocalTime.of(9, 00), LocalTime.of(17, 00), 3.00, false, 1.50),
                new Courier("Geoff Go Go", LocalTime.of(10, 00), LocalTime.of(16, 00), 4.00, true, 2.00)
        );
    }

    public List<Courier> findSuitableandCheapestCourier(OrderDetails order) {
        List<Courier> courierDatabase = listOfCouriers();

        List<Courier> avaliableCourier = listOfCouriers();
        avaliableCourier = courierDatabase.stream()
                .filter(courier -> order.getDeliveryTime().isAfter(courier.getStartTime()) &&
                        order.getDeliveryTime().isBefore(courier.getEndTime()))
                .filter(courier -> courier.getMaxDistance() >= order.getDistance())
                .filter(courier -> courier.getRefrigeration() == order.isFridgeRequired())
                .toList();

       if (!avaliableCourier.isEmpty()) {

           Comparator<Courier> comparator = Comparator.comparing(Courier::getPricePerMile);
           Courier cheapestCourier = avaliableCourier.stream().min(comparator).get();

           return Collections.singletonList(cheapestCourier);
       }
        else {
            return null;
       }
    }

    //Logic for refrigeration
    public List<Courier> IsRefrigerationRequired(boolean refrigerationRequirement) {
        List<Courier> courierDatabase = listOfCouriers();

        List<Courier> matchingCouriers = listOfCouriers();

        matchingCouriers = courierDatabase.stream()
                .filter(courier -> refrigerationRequirement == courier.getRefrigeration())
                .collect(Collectors.toList());
        return matchingCouriers;
    }

    //logic for distance criteria
    public List<Courier> distanceCriteria(double distanceOfDelivery) {
        List<Courier> courierDatabase = listOfCouriers();

        List<Courier> matchingCouriers = listOfCouriers();

        matchingCouriers = courierDatabase.stream()
                .filter(courier -> distanceOfDelivery <= (courier.getMaxDistance()))
                .collect(Collectors.toList());
        return matchingCouriers;
    }

    public List<Courier> getListOfCourierMatchTheDeliveryCriteriaStartEndTime(LocalTime deliveryTime) {
        List<Courier> courierDatabase = listOfCouriers(); // Bring a copy of the list to a local field

        List<Courier> matchingCouriers; //Init a empty list to store our matching couriers

        // Loading all values from the courierDatabase list, then stream each value
        // Using a filter to iterate over each value in the list and asserting if deliveryTime is after courierStartTime, then add them to a temporary list
        // Using another filter to iterate over the values in the temporary list, then assering if deliveryTime is before courierEndTime, then add them to a *new* temporary list
        matchingCouriers = courierDatabase.stream()
                .filter(courier -> deliveryTime.isAfter(courier.getStartTime()))
                .filter(courier -> deliveryTime.isBefore(courier.getEndTime()))
                .collect(Collectors.toList());

        return matchingCouriers;
    }

    }



