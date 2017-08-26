import groovy.transform.*

@ToString
class SubwayLine {
  String name
  String textCoordinates
  List<Station> stations = []
}


