"""A precedence rule is given as “P>E”, 
which means that letter “P” is followed by letter “E”. 
Write a function, given an array of precedence rules, 
that finds the word represented by the given rules. 

Note: Each represented word contains a set of unique characters, 
i.e. the word does not contain duplicate letters.
func findWord(_ rules: [String]) -> String {
  return “”
}
findWord([“P>E”, “E>R”,“R>U”]) // PERU
findWord([“I>N”, “A>I”,“P>A”,“S>P”]) // SPAIN
findWord([“U>N”, “G>A”, “R>Y”, “H>U”, “N>G”, “A>R”]) // HUNGARY
findWord([“I>F”, “W>I”, “S>W”, “F>T”]) // SWIFT
findWord([“R>T”, “A>L”, “P>O”, “O>R”, “G>A”, “T>U”, “U>G”]) // PORTUGAL
findWord([“U>N”, “G>A”, “R>Y”, “H>U”, “N>G”, “A>R”]) // HUNGARY
findWord([“I>F”, “W>I”, “S>W”, “F>T”]) // SWIFT
findWord([“R>T”, “A>L”, “P>O”, “O>R”, “G>A”, “T>U”, “U>G”]) // PORTUGAL
findWord([“W>I”, “R>L”, “T>Z”, “Z>E”, “S>W”, “E>R”, “L>A”, “A>N”, “N>D”, “I>T”]) 
// SWITZERLAND """
