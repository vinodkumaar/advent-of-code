//functions
toInstructions = { numbers -> numbers.collate(4) }

truncateInstructionsToOpcode99 = { instructions -> 
                                        opcode99Index = instructions.findIndexOf {instruction -> 
                                                instruction[0] == 99
                                         }
                                         return instructions.take(opcode99Index)
                                  }


opcode1 = { masterList, instruction -> 
                masterList[instruction[3]] = masterList[instruction[1]] + masterList[instruction[2]] 
                return masterList
          }
          
opcode2 = { masterList, instruction -> 
                masterList[instruction[3]] = masterList[instruction[1]] * masterList[instruction[2]] 
                return masterList
          }
          
operations = [1:opcode1, 2:opcode2]       

//tests
assert toInstructions([1,2,3,4,99]) == [[1,2,3,4],[99]]
assert opcode1([1,0,0,0,99], [1,0,0,0]) == [2,0,0,0,99]
assert opcode2([2,3,0,3,99], [2,3,0,3]) == [2,3,0,6,99]
assert truncateInstructionsToOpcode99([[1,2,3,4],[99]]) == [[1,2,3,4]]

//solution
noun = 12
verb = 2
master = [1,noun,verb,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,10,19,1,19,5,23,2,23,6,27,1,27,5,31,2,6,31,35,1,5,35,39,2,39,9,43,1,43,5,47,1,10,47,51,1,51,6,55,1,55,10,59,1,59,6,63,2,13,63,67,1,9,67,71,2,6,71,75,1,5,75,79,1,9,79,83,2,6,83,87,1,5,87,91,2,6,91,95,2,95,9,99,1,99,6,103,1,103,13,107,2,13,107,111,2,111,10,115,1,115,6,119,1,6,119,123,2,6,123,127,1,127,5,131,2,131,6,135,1,135,2,139,1,139,9,0,99,2,14,0,0]

instructions = toInstructions(master)

truncatedInstructions = truncateInstructionsToOpcode99(instructions)

truncatedInstructions.inject(master) { result, instruction -> operations[instruction[0]](result,instruction)}

//Find upto 100
(1..100).each  {noun -> 
    (1..100).each {verb -> 
        master[1] = noun;
        master[2] = verb;
        address0 = truncatedInstructions.inject(master) { result, instruction -> operations[instruction[0]](result,instruction)}[0]
        if (address0 == 19690720) {
           println "noun=" + noun + "verb=" + verb 
        }
     }
 }