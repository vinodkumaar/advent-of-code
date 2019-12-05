drawWire = { startingPoint, instruction -> 
                   method = instruction[0]
                   "$method"(startingPoint, instruction[1..-1]) 
           }

R = {startingPoint, distance -> 
        startingPoint.y = startingPoint.y + distance.toInteger()
        return startingPoint
    }
L = {startingPoint, distance -> 
        startingPoint.y = startingPoint.y - distance.toInteger()
        return startingPoint
    }
U = {startingPoint, distance -> 
        startingPoint.x = startingPoint.x + distance.toInteger()
        return startingPoint
    }
D = {startingPoint, distance -> 
        startingPoint.x = startingPoint.x - distance.toInteger()
        return startingPoint
    }


assert drawWire([x:1,y:1], "R75") == [x:1,y:76]
assert drawWire([x:1,y:76], "U75") == [x:76,y:76]
assert drawWire([x:76,y:76], "L75") == [x:76,y:1]
assert drawWire([x:76,y:1], "D75") == [x:1,y:1]