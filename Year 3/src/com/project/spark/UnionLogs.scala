package com.project.spark

import org.apache.spark._
import org.apache.spark.{SparkContext,SparkConf}
import org.apache.log4j._

object UnionLogs {
  
  def noHeader(line : String): Boolean = !(line.startsWith("host") && line.contains("bytes"))
  
  
  def main(args : Array[String])
  {
      Logger.getLogger("org").setLevel(Level.ERROR)
      
     
      val conf = new SparkConf().setAppName("UnionLogs").setMaster("local[*]")
      val sc = new SparkContext(conf)
      
      // Use two textfile for set operation
      
      val julyLogs = sc.textFile("data/BitCoin")
      val augustLogs = sc.textFile("data/BitCoin")
      
      //Using the union method to return an aggregated RDD that contains items from both RDDs
      val aggregatedLogLines = julyLogs.union(augustLogs)      
      
      //return a newLogfile RDD which does not contain the header lines
      val newLogLines = aggregatedLogLines.filter( line => noHeader(line))
      
      //Take a sample of 0.1 on the RDD
      val sample = newLogLines.sample(withReplacement = true , fraction = 0.1)
      
      print("Successfully stored in the outdirectory")
      
      sample.saveAsTextFile("out/sample_bitcoins.tsv")

  }
}