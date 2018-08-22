package systems.vostok.taxi.drive.app.operation.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.executor.OperationService

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.*
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections.ENROLL
import static systems.vostok.taxi.drive.app.test.Dataset.getJsonDataset

@Component
class ClientFlowTestUtil {
    @Autowired
    ClientRepository clientRepository

    @Autowired
    OperationService operationService

    void removeAllClients() {
        clientRepository.deleteAll()
    }

    OperationResponse createClient(String detasetName) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: ADD_CLIENT_OPERATION.name,
                direction: ENROLL,
                body: getJsonDataset('client', detasetName)
        )

        operationService.execute(operationRequest)
    }

    OperationResponse editClient(String detasetName) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: EDIT_CLIENT_OPERATION.name,
                direction: ENROLL,
                body: getJsonDataset('client', detasetName)
        )

        operationService.execute(operationRequest)
    }

    OperationResponse deleteClient(String detasetName) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: DELETE_CLIENT_OPERATION.name,
                direction: ENROLL,
                body: getJsonDataset('client', detasetName)
        )

        operationService.execute(operationRequest)
    }

    void checkClient(String detasetName, OperationResponse response) {
        Client expectedClient = getJsonDataset('client', detasetName) as Client
        Client actualResponseClient = response.body as Client
        Client actualClient = clientRepository.findById(expectedClient.login).get()

        expectedClient.with {
            assertEquals(it, actualResponseClient)
            assertEquals(it, actualClient)
        }
    }

    void checkClientNonexistence(String detasetName) {
        Client expectedClient = getJsonDataset('client', detasetName) as Client
        assertFalse(clientRepository.existsById(expectedClient.login))
    }
}