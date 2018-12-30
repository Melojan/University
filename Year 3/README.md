First , start all the daemons in the Hadoop and Spark Folder.

use the jps command to check the active daemons starting

Use spark-submit --class com.project.spark.<CLASS_NAME> --master yarn or spark://localhost:port (for the standalone cluster) --deploy-mode client or cluster (for yarn configuration) MyProject.jar

Then check the output directory if your spark has submitted the application correctly.

Also check the cluster to check execution times between each algorithm.
