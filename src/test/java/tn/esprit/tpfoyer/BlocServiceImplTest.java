import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BlocServiceImplTest {

    @Autowired
    private BlocService blocService;

    @Autowired
    private BlocRepository blocRepository;

    @Test
    @Order(1)
    @Transactional
    @Rollback
    void testAddBloc() {
        // Creating a new Bloc with adjusted values (0 instead of 0L, 200 instead of 200L)
        Bloc bloc = new Bloc(0, "Bloc B", 200, null, null);
        Bloc savedBloc = blocService.addBloc(bloc);
        
        assertNotNull(savedBloc);
        assertTrue(savedBloc.getIdBloc() > 0);  // Ensure ID is generated and > 0
    }

    @Test
    @Order(3)
    @Transactional
    @Rollback
    void testRetrieveBlocById() {
        // Adding Bloc to the repository
        Bloc bloc = new Bloc(0, "Bloc C", 150, null, null);
        Bloc savedBloc = blocService.addBloc(bloc);

        // Retrieving Bloc by ID and checking if the values match
        Bloc retrievedBloc = blocService.retrieveBloc(savedBloc.getIdBloc());
        
        assertNotNull(retrievedBloc);
        assertEquals("Bloc C", retrievedBloc.getNomBloc());  // Ensure name matches
    }

    @Test
    @Order(4)
    @Transactional
    @Rollback
    void testModifyBloc() {
        // Adding Bloc to the repository
        Bloc bloc = new Bloc(0, "Bloc D", 300, null, null);
        Bloc savedBloc = blocService.addBloc(bloc);
        
        // Modifying the Bloc's capacity
        savedBloc.setCapaciteBloc(400);  // Changed to 400
        
        // Saving the updated Bloc and verifying the modification
        Bloc updatedBloc = blocService.modifyBloc(savedBloc);
        
        assertEquals(400, updatedBloc.getCapaciteBloc());  // Ensure capacity is updated to 400
    }

    @Test
    @Order(5)
    @Transactional
    @Rollback
    void testRemoveBloc() {
        // Adding a Bloc to remove
        Bloc bloc = new Bloc(0, "Bloc E", 250, null, null);
        Bloc savedBloc = blocService.addBloc(bloc);

        // Removing the Bloc by its ID and ensuring it is deleted
        blocService.removeBloc(savedBloc.getIdBloc());
        assertFalse(blocRepository.existsById(savedBloc.getIdBloc()));  // Ensure it no longer exists
    }
}
