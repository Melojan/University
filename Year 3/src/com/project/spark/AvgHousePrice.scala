package com.project.spark

import org.apache.spark.{SparkConf,SparkContext}
import org.apache.log4j._
  /* Average price for different type of house problem:
   * 	key : the number of bedrooms
   *  value : the average price of property
   *  
   *  Format of the RealEstate File , the columns are ordered as
   *  1 = unique ID for the house
   *  2 = location of the property
   *  3 = the price of the property in US dollars
   *  4 = the number of bedrooms of the property
   *  5 = the number of bathrooms of the property
   *  6 = the size of the house in square feet
   *  7 = the price of the house per square foot
   *  8 = the state of sale
   */
 
object AvgHousePrice {

  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("AvgHousePrice").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("input/RealEstate.csv");
    
    //filter out the headerline as you do not need it
    val clearFollowingLines = lines.filter(line => !line.contains("Bedrooms"))
    
    //convert string RDD to pair RDD , key - number of bedrooms , value - avgcount object
    val housePriceRDD = clearFollowingLines.map(line => (line.split(",")(3).toInt, AvgCount(1, line.split(",")(2).toDouble)))

    val housePriceTotal = housePriceRDD.reduceByKey((x, y) => AvgCount(x.count + y.count, x.total + y.total))

    val housePriceAvg = housePriceTotal.mapValues(avgCount => avgCount.total / avgCount.count)

    // change bedroom type to Integers to apply sorting easier
    // sorted by the key which is the number of bedrooms
    val sortedHousePriceAvg = housePriceAvg.sortByKey()
    
    //get the Ouput , make sure the result makes sense
    print("Successfully stored in the outdirectory")
    
    sortedHousePriceAvg.saveAsTextFile("out/average_price.csv")
  }
}


