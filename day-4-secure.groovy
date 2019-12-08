//functions

isSecure = { sequence -> 
                       return isAscending(sequence).ascending && isWithRepeatNumber(sequence).repeating
           }
           
isAscending = { sequence -> 
                        "$sequence".inject(['previous': '0', 'ascending': true]) { result, it -> 
                                                                                      if (result.ascending) {
                                                                                          result.ascending = result.previous <= it
                                                                                      }
                                                                                      result.previous = it
                                                                                      return result
                                                                                }     
               }
               
isWithRepeatNumber = { sequence ->
                          "$sequence".inject(['previous': '0', 'repeating': false]) { result, it ->
                                                                                         if (!result.repeating) {
                                                                                             result.repeating = result.previous == it
                                                                                         } 
                                                                                         result.previous = it
                                                                                         return result 
                                                                                     }                                                 
                      }                                                                                          
                                                                     

//tests

assert isAscending(123424) == ['previous': '4', 'ascending': false]
assert isAscending(123456) == ['previous': '6', 'ascending': true]

assert isWithRepeatNumber(123456) == ['previous': '6', 'repeating': false]
assert isWithRepeatNumber(113456) == ['previous': '6', 'repeating': true]

assert isSecure(123456) == false
assert isSecure(112382) == false
assert isSecure(112345) == true

//solution

(197487..673251).inject(0) { count, it ->
                               if (isSecure(it)) {
                                   count++
                               } 
                               return count
                           }
