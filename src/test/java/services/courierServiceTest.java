package services;

import models.Courier;
import models.OrderDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class courierServiceTest {

        private Courier bobby_bikes;
        private Courier martin_Moves;
        private Courier geoff_Go_Go;
        private CourierService listOfCouriersTest;
        private OrderDetails orderA;
        private OrderDetails orderB;
        private OrderDetails orderC;

        @BeforeEach
        void setUp() {
            bobby_bikes = new Courier("Bobby Bikes", LocalTime.of(9, 00), LocalTime.of(13, 00), 5.00, true, 1.75);
            martin_Moves = new Courier("Martin Moves", LocalTime.of(9, 00), LocalTime.of(17, 00), 3.00, false, 1.50);
            geoff_Go_Go = new Courier("Geoff Go Go", LocalTime.of(10, 00), LocalTime.of(16, 00), 4.00, true, 2.00);

            orderA = new OrderDetails(LocalTime.of(11,00),true, 3.00);
            orderB= new OrderDetails(LocalTime.of(16,30),false, 3.00);
            orderC = new OrderDetails(LocalTime.of(19,30),true, 4.00);
            listOfCouriersTest = new CourierService();
        }

        //Working hours criteria tests

        @Test
        public void When_Order_Is_Ready_For_Delivery_At_09_00_Then_Return_Bobby_And_Martin() {
            List<Courier> expectedCouriers = new ArrayList<>();
            expectedCouriers.add(bobby_bikes);
            expectedCouriers.add(martin_Moves);
            Assertions.assertEquals(expectedCouriers, listOfCouriersTest.getListOfCourierMatchTheDeliveryCriteriaStartEndTime(LocalTime.of(9, 01)));
            Assertions.assertEquals(expectedCouriers, listOfCouriersTest.getListOfCourierMatchTheDeliveryCriteriaStartEndTime(LocalTime.of(9, 50)));
            Assertions.assertNotEquals(expectedCouriers, listOfCouriersTest.getListOfCourierMatchTheDeliveryCriteriaStartEndTime(LocalTime.of(8, 00)));
        }

        @Test
        public void When_Order_Is_Ready_For_Delivery_At_11_45_Then_Return_All_Couriers() {
            List<Courier> expectedCouriers = new ArrayList<>();
            expectedCouriers.add(bobby_bikes);
            expectedCouriers.add(martin_Moves);
            expectedCouriers.add(geoff_Go_Go);
            Assertions.assertEquals(expectedCouriers, listOfCouriersTest.getListOfCourierMatchTheDeliveryCriteriaStartEndTime(LocalTime.of(11, 45)));
            Assertions.assertEquals(expectedCouriers, listOfCouriersTest.getListOfCourierMatchTheDeliveryCriteriaStartEndTime(LocalTime.of(12, 30)));
        }

        @Test
        public void When_Order_Is_Ready_For_Delivery_At_16_30_Then_Return_Martin() {
            List<Courier> expectedCouriers = new ArrayList<>();
            expectedCouriers.add(martin_Moves);
            Assertions.assertEquals(expectedCouriers, listOfCouriersTest.getListOfCourierMatchTheDeliveryCriteriaStartEndTime(LocalTime.of(16, 30)));
        }

        @Test
        public void When_Order_Is_Out_Of_Delivery_Time_Scope_Return_No_Couriers() {
            List<Courier> expectedCouriers = new ArrayList<>();
            Assertions.assertEquals(expectedCouriers, listOfCouriersTest.getListOfCourierMatchTheDeliveryCriteriaStartEndTime(LocalTime.of(20, 30)));
            Assertions.assertTrue(expectedCouriers.isEmpty());
        }

        //Refrigeration criteria tests
        @Test
        public void When_Refrigeration_Is_Required_Then_Return_Bobby_And_Geoff() {
            List<Courier> expected_Refrigerated_Couriers = new ArrayList<>();
            expected_Refrigerated_Couriers.add(bobby_bikes);
            expected_Refrigerated_Couriers.add(geoff_Go_Go);
            Assertions.assertEquals(expected_Refrigerated_Couriers, listOfCouriersTest.IsRefrigerationRequired(true));

        }

        @Test
        public void When_Refrigeration_Is_NOT_Required_Return_Martin() {
            List<Courier> expected_Unrefrigerated_Couriers = new ArrayList<>();
            expected_Unrefrigerated_Couriers.add(martin_Moves);
            Assertions.assertEquals(expected_Unrefrigerated_Couriers, listOfCouriersTest.IsRefrigerationRequired(false));

        }


        //distance criteria tests
        @Test
        public void When_Order_Had_Delivery_Distance_Of_2_5Miles_Then_Return_All_Couriers() {
            List<Courier> expectedlistOfCouriersForDistanceof2_5Miles = new ArrayList<>();
            expectedlistOfCouriersForDistanceof2_5Miles.add(bobby_bikes);
            expectedlistOfCouriersForDistanceof2_5Miles.add(martin_Moves);
            expectedlistOfCouriersForDistanceof2_5Miles.add(geoff_Go_Go);
            Assertions.assertEquals(expectedlistOfCouriersForDistanceof2_5Miles, listOfCouriersTest.distanceCriteria(2.5));
        }

        @Test
        public void When_Order_Has_Delivery_Distance_Of_4Miles_Then_Return_Bobby_And_Geoff() {
            List<Courier> expectedListOfCouriersWhoCanDleiverupTo4Miles = new ArrayList<>();
            expectedListOfCouriersWhoCanDleiverupTo4Miles.add(bobby_bikes);
            expectedListOfCouriersWhoCanDleiverupTo4Miles.add(geoff_Go_Go);
            Assertions.assertEquals(expectedListOfCouriersWhoCanDleiverupTo4Miles, listOfCouriersTest.distanceCriteria(4.0));
        }

        @Test
        public void When_Order_Has_Delivery_Distance_Of_4_5Miles_Then_Return_Bobby() {
            List<Courier> expectedListOfCouriersWhoCanDleiverupTo4_5Miles = new ArrayList<>();
            expectedListOfCouriersWhoCanDleiverupTo4_5Miles.add(bobby_bikes);
            Assertions.assertEquals(expectedListOfCouriersWhoCanDleiverupTo4_5Miles, listOfCouriersTest.distanceCriteria(4.5));
        }

        @Test
        public void When_Order_Has_Delivery_Distance_Of_6_Miles_Then_Return_No_Couriers() {
            List<Courier> expectedListOfCouriersWhoCanDeliverUpTo6Miles = new ArrayList<>();
            Assertions.assertTrue(expectedListOfCouriersWhoCanDeliverUpTo6Miles.isEmpty());
        }

        //Combination of all scenario's test
        @Test
        public void When_Delivery_At_11AM_Requires_RefridgeratedDelivery_And_Has_A_delivery_distance_of_3_miles_Return_Cheapest() {

            List<Courier> expectedList = new ArrayList<>();
            expectedList.add(bobby_bikes);
            Assertions.assertEquals(expectedList, listOfCouriersTest.findSuitableandCheapestCourier(orderA));
        }

    @Test
    public void When_Delivery_At_4_30pm_Requires_RefridgeratedDelivery_And_Has_A_delivery_distance_of_4_miles_Return_Cheapest() {

        List<Courier> expectedList = new ArrayList<>();
        expectedList.add(martin_Moves);
        Assertions.assertEquals(expectedList, listOfCouriersTest.findSuitableandCheapestCourier(orderB));
    }

    @Test
    public void When_Delivery_At_7pm_Requires_RefridgeratedDelivery_And_Has_A_delivery_distance_of_4_miles_Return_Cheapest() {
        List<Courier> expectedList = new ArrayList<>();
        Assertions.assertNull(listOfCouriersTest.findSuitableandCheapestCourier(orderC));
    }

}



