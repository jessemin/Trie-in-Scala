# Revisiting Trie in Scala
*a trie implementation in Scala*

## Introduction
* Trie Implementation in Scala
* Utilized the concept of "tail recursion"
* Simple but powerful implementation of Leixcon
* Extends Traversable[T] in order to override foreach

## How to Use

### Easy One-block Usage

```Scala
//Get the root TrieNode() of the lexicon and print it
val lexiconNode = Lexicon.generateLexicon("words.txt")

   or

//Just print the constructed trie
Lexicon.generateLexicon("words.txt")

```

### Detailed Usages

```Scala
//Generate one empty node
val root = new TrieNode()

//Append Strings
root.append("he")
root.append("she")
root.append("his")
root.append("hers")

//Check whether the trie contains a specific string
root.contains("he")   //true
root.contains("her")  //false

//Print the entire trie in a console with the predefined format
root.printTree()
```
