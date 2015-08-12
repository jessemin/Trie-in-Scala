import scala.io.Source

object Lexicon {
  def generateLexicon(fileName: String): TrieNode ={
    var root = new TrieNode()
    Source.fromFile(fileName).getLines().foreach{
      line =>
      root.append(line)
    }
    root.printTree
    return root
  }
}
