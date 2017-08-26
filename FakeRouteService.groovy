@Singleton
class FakeRouteService implements RouteService {
  def calculateRouteFor(String origin, String destiny){
    if(origin == "Universidad")
      [ [origin:"Universidad", destiny:"Eugenia",direction:"Indios verdes",stations:7] ]
    else
      [ [origin:"Copilco", destiny:"Centro Médico",direction:"Indios verdes",stations:8],
      [origin:"Centro médico", destiny:"Puebla",direction:"Pantitlán",stations:7 ]]

  }
}
