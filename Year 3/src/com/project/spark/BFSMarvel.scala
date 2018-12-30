package com.project.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import org.apache.spark.util.LongAccumulator
import org.apache.log4j._
import scala.collection.mutable.ArrayBuffer

object BFSMarvel {
  
  //Source which is inspired by 
  //http://www.sundog-education.com/spark-scala/
  
  //Information to learn about the Breadth-First-Search-Algorithm (Introduction to Graph with Breadth First Search BF)
  //https://www.codeproject.com/Articles/32212/Introduction-to-Graph-with-Breadth-First-Search-BF
  
  // The characters we want to find the separation between.
  val initialCharacterID = 5000
  val targetCharacterValue = 10
  
  // use global variable as Option so we can reference in the mapper.
  var hitCounter:Option[LongAccumulator] = None
  
  // Create Data and Node containing an array of hero ID connections, the distance, and color.
  type Data = (Array[Int], Int, String)
  // A BFSNode has a heroID and the BFSData associated with it.
  type Node = (Int, Data)
    
  def convertToBFS(line: String): Node = {
    
    // Split up the line into fields
    val fields = line.split("\\s+")
    
    // Fetch this hero ID from the first field
    val heroID = fields(0).toInt
    
    // Extract subsequent hero ID's into the connections array
    var connections: ArrayBuffer[Int] = ArrayBuffer()
    for ( connection <- 1 to (fields.length - 1)) {
      connections += fields(connection).toInt
    }
    
    // Default distance and color is 9999 and white , cannot set the distance to infinity so set it up to 9999.
    var color:String = "WHITE" //white represents the node completely unexplored
    var distance:Int = 9999
    
    // Set up the start character 
    if (heroID == initialCharacterID) {
      color = "GRAY" // gray indicating color needs to explored
      distance = 0
    }
    
    return (heroID, (connections.toArray, distance, color))
  }
  
  // Start with Iteration 0 
  def createStartingRdd(sc:SparkContext): RDD[Node] = {
    val inputFile = sc.textFile("input/Marvel-graph.txt");
    return inputFile.map(convertToBFS)
  }
  

  // Expands the Breadth First Search nodes into this node and its childrens
  def Map(node:Node): Array[Node] = {
    
    // Fetch data from the BFSNode
    val characterID:Int = node._1
    val data:Data = node._2
    
    val connections:Array[Int] = data._1
    val distance:Int = data._2
    var color:String = data._3
    
    // This is called from flatMap, so we return an array
    // of potentially many Nodes in the BFS to add to our new RDD
    var results:ArrayBuffer[Node] = ArrayBuffer()
    
    // gray nodes for each connection
    if (color == "GRAY") {
      for (connection <- connections) {
        val newCharacterID = connection
        val newDistance = distance + 1
        val newColor = "GRAY"
        
        if (targetCharacterValue == connection) {
          if (hitCounter.isDefined) {
            hitCounter.get.add(1)
          }
        }
        
        // Create our new Gray node for this connection and add it to the results
        val newEntry:Node = (newCharacterID, (Array(), newDistance, newColor))
        results += newEntry
      }
      
      // Color this node as black, indicating it has been visited already.
      color = "BLACK"
    }
    
    // Add the original node back in, so its connections can get merged with 
    // the gray nodes in the reducer.
    val thisEntry:Node = (characterID, (connections, distance, color))
    results += thisEntry
    
    return results.toArray
  }
  
  /** Combine nodes for the same heroID, preserving the shortest length and darkest color. */
  def bfsReduce(data1:Data, data2:Data): Data = {
    
    // Extract data that we are combining
    val edges1:Array[Int] = data1._1
    val edges2:Array[Int] = data2._1
    val distance1:Int = data1._2
    val distance2:Int = data2._2
    val color1:String = data1._3
    val color2:String = data2._3
    
    // Default node values
    var distance:Int = 9999
    var color:String = "WHITE"
    var edges:ArrayBuffer[Int] = ArrayBuffer()
    
    // See if one is the original node with its connections.
    if (edges1.length > 0) {
      edges ++= edges1
    }
    if (edges2.length > 0) {
      edges ++= edges2
    }
    
    // Maintain minimum distance , find out the shortest minimum distance
    if (distance1 < distance) {
      distance = distance1
    }
    if (distance2 < distance) {
      distance = distance2
    }
    
    // Maintain only darkest colour
    if (color1 == "WHITE" && (color2 == "GRAY" || color2 == "BLACK")) {
      color = color2
    }
    if (color1 == "GRAY" && color2 == "BLACK") {
      color = color2
    }
    if (color2 == "WHITE" && (color1 == "GRAY" || color1 == "BLACK")) {
      color = color1
    }
    if (color2 == "GRAY" && color1 == "BLACK") {
      color = color1
    }
    
    return (edges.toArray, distance, color)
  }
    
  /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val conf = new SparkConf().setAppName("BFSMarvel").setMaster("local[*]")
   
    val sc = new SparkContext(conf)
    
    // Signal when we found the target
    hitCounter = Some(sc.longAccumulator("Hit Counter"))
    
    var iterationRdd = createStartingRdd(sc)
    
    var iteration:Int = 0
    for (iteration <- 1 to 10) {
      println("Running BFS Iteration# " + iteration)
   
      // Create new vertices as needed to darken or reduce distances in the
      // reduce stage. If we encounter the node we're looking for as a GRAY
      // node, increment our accumulator to signal that we're done.
      val mapped = iterationRdd.flatMap(Map)
      
      // Update the count value
      println("Processing " + mapped.count() + " values.")
      
      if (hitCounter.isDefined) {
        val hitCount = hitCounter.get.value
        if (hitCount > 0) {
          println("Hit the target character! From " + hitCount + 
              " different direction(s).")
          return
        }
      }
      
      // Reducer combines data for each character ID, preserving the darkest
      // color and shortest path.      
      iterationRdd = mapped.reduceByKey(bfsReduce)
      
      print("Successfully stored in the outdirectory")
      
      //Save as a TextFile in the out directory
      iterationRdd.saveAsTextFile("out/shortest_distance.txt")
    }
  }
}
