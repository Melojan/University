package com.project.spark

import org.apache.spark.{SparkContext,SparkConf}
import org.apache.log4j._
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.optimization.SquaredL2Updater


object Regression {
  
  def main(args : Array[String])
  {
    Logger.getLogger("org").setLevel(Level.ERROR)
  val conf = new SparkConf().setAppName("Regression").setMaster("local[*]")  
    val sc = new SparkContext(conf)
    
    /*The statistical value file consists pair x and y numbers
     * where x is the label and y is the feature to predict the label.
     */
    //Consider storing two textfiles
    //First textfile reads x (label) and y (features) pairs where x is what we predict and y is which associate the value we use to predict labels
    
    val statsdata = sc.textFile("input/stats.txt");
    
    //Second textfile using to predict our regression model algorithm
    val testdata = sc.textFile("data/BitCoin");
    
    
    //Convert input data to LabeledPoints for MLlib
    val data = statsdata.map(LabeledPoint.parse).cache()
    val test = testdata.map(LabeledPoint.parse)
    
    // Create Regression model
    
    val regModel = new LinearRegressionWithSGD()
    regModel.optimizer
    .setNumIterations(10)
    .setStepSize(1.0)
    .setUpdater(new SquaredL2Updater())
    .setRegParam(0.01)
    
    val model = regModel.run(data)
    
     // Predict values for our test data
    val predictions = model.predict(data.map(_.features))
    
    val predictionAndLabel = predictions.zip(data.map(_.label))
    
    print("Successfully stored in the outdirectory")
 
    // Store the predicted and actual values for each point in the out directory
      
      predictionAndLabel.saveAsTextFile("out/regression_model")
      
  }
  
}





