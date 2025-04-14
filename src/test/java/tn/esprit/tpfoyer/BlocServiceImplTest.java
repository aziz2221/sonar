@Test
@Order(1)
@Transactional
@Rollback
void testAddBloc() {
    Bloc bloc = new Bloc(0, "Bloc B", 200, null, null); // Changed 0L to 0 and 200L to 200
    Bloc savedBloc = blocService.addBloc(bloc);
    assertNotNull(savedBloc);
    assertTrue(savedBloc.getIdBloc() > 0);
}

@Test
@Order(3)
@Transactional
@Rollback
void testRetrieveBlocById() {
    Bloc bloc = new Bloc(0, "Bloc C", 150, null, null); // Changed 0L to 0 and 150L to 150
    Bloc savedBloc = blocService.addBloc(bloc);

    Bloc retrievedBloc = blocService.retrieveBloc(savedBloc.getIdBloc());
    assertNotNull(retrievedBloc);
    assertEquals("Bloc C", retrievedBloc.getNomBloc());
}

@Test
@Order(4)
@Transactional
@Rollback
void testModifyBloc() {
    Bloc bloc = new Bloc(0, "Bloc D", 300, null, null); // Changed 0L to 0 and 300L to 300
    Bloc savedBloc = blocService.addBloc(bloc);
    savedBloc.setCapaciteBloc(400); // Changed 400L to 400

    Bloc updatedBloc = blocService.modifyBloc(savedBloc);
    assertEquals(400, updatedBloc.getCapaciteBloc()); // Changed 400L to 400
}

@Test
@Order(5)
@Transactional
@Rollback
void testRemoveBloc() {
    Bloc bloc = new Bloc(0, "Bloc E", 250, null, null); // Changed 0L to 0 and 250L to 250
    Bloc savedBloc = blocService.addBloc(bloc);

    blocService.removeBloc(savedBloc.getIdBloc());
    assertFalse(blocRepository.existsById(savedBloc.getIdBloc()));
}
