package com.project.spark

import org.apache.spark.{SparkContext,SparkConf}
import org.apache.log4j._

object AddPrimeNumbers {
  
  def main (args: Array[String])
  {
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    val conf = new SparkConf().setAppName("PrimeNumbers").setMaster("local[*]")
    val sc = new SparkContext(conf)
    
    val lines = sc.textFile("input/prime_nums.text");
    
    //Split up by  white spaces 
    val numbers = lines.flatMap(line => line.split("\\s+"))
    
    //Split results may contain empty strings so filter out the empty strings
    val validNumbers = numbers.filter(number => !number.isEmpty)

    //Convert string type to integer types
    val intNumbers = validNumbers.map(number => number.toInt)
    
    //Return the sum of those arguments
    val sum = intNumbers.reduce((x, y) => x + y)
    
    println("Sum is: " + sum)

  }
    
}