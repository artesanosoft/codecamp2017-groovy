def engine   = new groovy.text.SimpleTemplateEngine()
def source   = getClass().classLoader.getResource('index.html')
def template = engine.createTemplate(source)

def result = [:]

if(params){
  origin = params.origin
  destiny = params.destiny
  RouteService service = RouteServiceImpl.instance
  data = service.calculateRouteFor origin, destiny
  result.data = data
}


out << template.make(result:result)
