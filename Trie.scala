import scala.collection.mutable
import scala.annotation._

sealed trait TrieNodeTrait extends Traversable[String]{
  val morpheme: String
  var word: String
  var is_word: Boolean
  val children: mutable.Map[String, TrieNode]

  def childCount(): Int
  def isEmpty(): Boolean
  def append(key: String)
  def printTree()
  def contains(t_word: String): Boolean
}

class TrieNode(p_morpheme: String = "", p_word: String = "", p_is_word: Boolean = false, p_children: mutable.Map[String, TrieNode] = mutable.Map.empty[String, TrieNode]) extends TrieNodeTrait{
  val morpheme: String = p_morpheme
  var word: String = p_word
  var is_word: Boolean = p_is_word
  val children: mutable.Map[String, TrieNode] = p_children

  def childCount(): Int = children.size

  override def isEmpty(): Boolean = morpheme == "" && word == "" && is_word == false && children.isEmpty
  override def foreach[U](f: String => U): Unit = {
    @tailrec def foreachHelper(nodes: TrieNode*): Unit = {
      if(nodes.size != 0){
        nodes.foreach(node => f(node.word))
        val subnodes = nodes.flatMap(node => node.children.values)
        foreachHelper(subnodes:_*)
      }
    }
    foreachHelper(this)
  }
  override def append(key: String) = {
    @tailrec def appendHelper(node: TrieNode, currentIndex: Int): Unit = {
      if (currentIndex == key.length) {
        node.word = key
        node.is_word = true
      }
      else {
        val char = key.charAt(currentIndex).toString()
        val result = {
          node.children.getOrElseUpdate(char, {
            new TrieNode(char)
          })
        }
        appendHelper(result, currentIndex + 1)
      }
    }
    appendHelper(this, 0)
  }
  override def toString() = this.morpheme
  override def printTree() = {
    def printTreeHelper(blanks: String, node: TrieNode): Unit ={
      if(blanks == "") println("*")
      var nodeInfo = blanks + node
      if(node.is_word) nodeInfo += "  #WORD NODE"
      println(nodeInfo)
      val t_children = node.children.values
      if(!t_children.isEmpty) t_children.foreach(t_child => printTreeHelper(blanks+"  ", t_child))
    }
    printTreeHelper("", this)
  }
  override def contains(t_word: String): Boolean = {
    @tailrec def containsHelper(node: TrieNode, currentIndex: Int): Boolean ={
      if(currentIndex == t_word.length) return node.is_word
      else{
        node.children.get(t_word.charAt(currentIndex).toString) match {
          case Some(child) => containsHelper(child, currentIndex + 1)
          case None => false
        }
      }
    }
    containsHelper(this, 0)
  }
}


