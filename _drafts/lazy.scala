//import java.util.ArrayList
//import java.util.List

class LazyLists {
  lazy val intList: List[Int] = new ArrayList[Int]()
  lazy val floatList: List[Double] = new ArrayList[Double]()
  
  def addItemBest(item: Int): Unit = intList.add(item)
  def addItemBest(item: Double): Unit = floatList.add(item)
  def removeItemBest(item: Int): Unit = intList.remove(item.asInstanceOf[Object])
  def removeItemBest(item: Double): Unit = floatList.remove(item)
}
