@Singleton
class CodeCampService {

  List<SubwayLine> lines = []

  def stage1(String filepath){
    File file = new File(filepath)
    String text = file.text
    def root = new XmlSlurper().parseText(text)
    obtainingSubwayLinesWithStationsFromParser(root)
    populatingStationsForLinesFromParser(root)
    lines
  }

  private def obtainingSubwayLinesWithStationsFromParser(root){
    root.Document.Folder[0].Placemark.each {
      String textCoordinates = it.LineString.coordinates.text().trim()
      SubwayLine subwayline = new SubwayLine(name: it.name.text().trim(), textCoordinates: textCoordinates)
      textCoordinates.split("\n").eachWithIndex { c, i ->
        Station station = new Station(order: i, textCoordinates: c.trim())
        subwayline.stations << station
      }
      lines.add subwayline
    }
  }

  private def populatingStationsForLinesFromParser(root){
    root.Document.Folder[1].Placemark.each {
      String textCoordinates = it.Point.coordinates.text().trim()
      String stationName = it.name.text().trim()
      lines.each { line ->
        if (line.textCoordinates.contains(textCoordinates)) {
          def stations = line.stations.findAll { s ->
            s.textCoordinates == textCoordinates
          }
          stations*.name = stationName
        }
      }
    }
  }

}
