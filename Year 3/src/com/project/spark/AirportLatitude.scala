package com.project.spark

import org.apache.spark._
import org.apache.spark.{SparkContext,SparkConf}
import org.apache.log4j._

object AirportLatitude {
  
  def main(args : Array[String]) {
    
    Logger.getLogger("org").setLevel(Level.ERROR)
    

    val sc = new SparkContext()
    

    val airports = sc.textFile("input/airports.text");
    
    /* Airport DataSet */
    /* airport ID , name of the airport , city name , IATA code , ICAO code , latitude, longitude , altitude , timezone DST */

    
    val airportsLatitudeGreaterThan50 = airports.filter(line => line.split(Utils.COMMA_DELIMITER)(6).toFloat > 50)
    
     val airportsNamesAndLatitude = airportsLatitudeGreaterThan50.map(line => {
       
      val splits = line.split(Utils.COMMA_DELIMITER)
      splits(1) + ", " + splits(6)
    })
    
    print("Successfully stored in the outdirectory")
    
    airportsNamesAndLatitude.saveAsTextFile("out/airports_by_latitude_greater_than_50.text")

  }
}