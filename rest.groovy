
origin = params.origin
destiny = params.destiny

RouteService service = RouteServiceImpl.instance

data = service.calculateRouteFor origin, destiny

response.contentType = 'application/json'
json(data)
