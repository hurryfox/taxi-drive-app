package systems.vostok.taxi.drive.app.service

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.RidePrice
import systems.vostok.taxi.drive.app.dao.entity.Address
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.operation.PreconditionTestUtil
import systems.vostok.taxi.drive.app.operation.client.ClientFlowTestUtil
import systems.vostok.taxi.drive.app.operation.rate.RateFlowTestUtil
import systems.vostok.taxi.drive.app.operation.ride.RideFlowTestUtil

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Client management test')
class ClientManagementTestIntegration {
    @Autowired
    ClientManagementService clientManagementService

    @Autowired
    RateFlowTestUtil rateFlowTestUtil

    @Autowired
    ClientFlowTestUtil clientUtil

    @Autowired
    RideFlowTestUtil rideUtil

    @Autowired
    PreconditionTestUtil preconditionUtil

    @Test
    @DisplayName('Get client information')
    void getClientInfoTest() {
        preconditionUtil.ridePreconditions()
        clientUtil.createClient('simple_client')

        Client client = clientManagementService.getClientInfo('+79147654321')

        client.with {
            assertEquals(5, it.ridesAmount)
            assertFalse(it.rideFree)
        }
    }

    @Test
    @DisplayName('Evaluate ride for nonexistent client and dtd type')
    void evaluateRideNoClientDtdTest() {
        preconditionUtil.ridePreconditions()
        Ride ride = new Ride(
                rawFromAddress: new Address([city: 'Спасск-Дальний', street: 'Парковая', building: '29']),
                rawToAddress: new Address([city: 'Спасск-Дальний', street: 'Советская', building: '10'])
        )

        RidePrice ridePrice = clientManagementService.evaluateRide(ride)
        assertEquals(90, ridePrice.price as Integer)
    }
}
