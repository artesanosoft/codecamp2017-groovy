class Stage2Test extends GroovyTestCase {
  void testGetInstructionsForOneSimpleRoute(){
    //RouteService service = FakeRouteService.instance
    RouteService service = RouteServiceImpl.instance
    def instructions = service.calculateRouteFor("Universidad", "Eugenia")
    //assert instructions == [ [origin:"Universidad", destiny:"Eugenia",direction:"Indios verdes",stations:7] ]
    assert instructions == [ [origin:"Universidad", destiny:"Eugenia",stations:7] ]
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
