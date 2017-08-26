import groovy.util.logging.Log

@Log
@Singleton(strict=false)
class RouteServiceImpl implements RouteService {

  def allGraphsPerLine = []
  def codeCampService = CodeCampService.instance

  RouteServiceImpl(){
    codeCampService.stage1("Metro_CDMX.kml")
    codeCampService.lines.each { l ->
      allGraphsPerLine << graphForLine(l)
    }
  }

  def calculateRouteFor(String origin, String destiny){
    def graphs = allGraphsPerLine.findAll { graph ->
      graph.find { pair ->
        pair.find { station ->
          station.name == origin
        }
      }
    }

    def instructions = []
    graphs.each { g ->
      boolean shouldCount = false
      int stationsVisited = 0
      g.each { pair ->
        if(pair[0]?.name == origin) shouldCount = true
        if(shouldCount) stationsVisited++
        if(pair[1]?.name == destiny && shouldCount){
          instructions << [origin:origin, destiny:destiny, stations: stationsVisited, direction: g[-1][-1].name]
        }
      }
      if(!instructions){
        shouldCount = false
        stationsVisited = 0
        g*.reverse().reverse().each { pair ->
          if(pair[0]?.name == origin) shouldCount = true
          if(shouldCount) stationsVisited++
          if(pair[1]?.name == destiny && shouldCount){
            instructions << [origin:origin, destiny:destiny, stations: stationsVisited, direction:g*.reverse().reverse()[-1][-1].name]
          }
        }
      }
    }
    instructions
  }

  private def graphForLine(SubwayLine line){
    def graph = []
    for(int i = 0; i < line.stations.size() - 1; i++){
      graph << [line.stations[i], line.stations[i+1]]
    }
    graph
  }
}

