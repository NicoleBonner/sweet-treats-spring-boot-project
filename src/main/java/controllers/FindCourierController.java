package controllers;

import models.Courier;
import models.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.CourierService;

import java.time.LocalTime;

@RestController
public class FindCourierController {

    @Autowired
    CourierService courierService;

    @RequestMapping (path = "/courier/order/time/{time}/fridgeRequired/{fridgeRequired}/distance/{distance}", method = RequestMethod.GET)
    public Courier getBestCourierForOrder (
            @PathVariable(name = "deliveryTime") LocalTime deliveryTime,
            @PathVariable(name = "fridgeRequired") Boolean fridgeRequired,
            @PathVariable(name = "distanceOfOrder") Double distance) {

        return courierService.findSuitableandCheapestCourier(
                new OrderDetails(
                        deliveryTime, fridgeRequired, distance)).get(0);
    }
}
