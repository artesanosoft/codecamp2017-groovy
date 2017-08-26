class Stage2Test extends GroovyTestCase {
  void testGetInstructionsForOneSimpleRoute(){
    //RouteService service = FakeRouteService.instance
    RouteService service = RouteServiceImpl.instance
    def instructions = service.calculateRouteFor("Copilco", "Eugenia")
    //assert instructions == [ [origin:"Universidad", destiny:"Eugenia",direction:"Indios verdes",stations:7] ]
    assert instructions == [ [origin:"Copilco", destiny:"Eugenia",stations:6] ]
  }

  void testGetInstructionsForOneSimpleRouteInverted(){
    //RouteService service = FakeRouteService.instance
    RouteService service = RouteServiceImpl.instance
    def instructions = service.calculateRouteFor("Eugenia", "Universidad")
    //assert instructions == [ [origin:"Universidad", destiny:"Eugenia",direction:"Indios verdes",stations:7] ]
    assert instructions == [ [origin:"Eugenia", destiny:"Universidad",stations:7] ]
  }

  //void testGetInstructionsForOneRouteWithScales(){
  //  RouteService service = RouteServiceImpl.instance
  //  def instructions = service.calculateRouteFor("Copilco", "Puebla")
  //  assert instructions.size() == 2
  //  assert instructions == [
  //    [origin:"Copilco", destiny:"Centro Médico",direction:"Indios verdes",stations:8],
  //    [origin:"Centro médico", destiny:"Puebla",direction:"Pantitlán",stations:7 ]]
  //}
}
