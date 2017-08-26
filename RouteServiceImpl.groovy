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
    int stationsVisited = 0
    def instructions = []
    graphs.each { g ->
      g.each { pair ->
        stationsVisited++
        if(pair[1].name == destiny){
          instructions << [origin:origin, destiny:destiny, stations: stationsVisited]
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

