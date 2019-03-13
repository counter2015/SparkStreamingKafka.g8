import java.util
import java.util.Date

import kafka.serializer.StringDecoder

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

import ConfigInstance.config

object SparkStreamingApp {
  def main(args: Array[String]): Unit = {
    val ssc = StreamingContext.getOrCreate(checkpointPath, () => initSparkStreamingContext())
    ssc.start()
    ssc.awaitTermination()
  }
  
  // this checkpoint Path is set for debug, for real use, please replace it as valid directory.
  val checkpointPath: String = "file:///root/data/checkpoint/test" + new Date().getTime.toString

  def initSparkStreamingContext(): StreamingContext = {

    // Spark config
    val sparkConf = new SparkConf()
      .setAppName(config.getString("spark.myAppName"))

    val ssc = new StreamingContext(sparkConf, Seconds(config.getInt("spark.appInterval")))
    ssc.checkpoint(checkpointPath)


    // Kafka config
    val topics = config.getString("kafka.topics")
    val topicSet = topics.split(",").toSet
    val kafkaParams = Map[String, String](
      "metadata.broker.list" -> config.getString("kafka.brokers")
    )

    val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topicSet
    )

    //     flatMap is a one-to-many DStream operation that creates a new DStream
    // by generating multiple new records from each record in the source DStream.

    //    while flatMap will let Map[String, String] to Map[String, char], so we
    //add a OPTION Some to let String be String.

    val events = kafkaStream.flatMap(line => Some(line._2)).map(record => {
      record
    })
    events.print()
    ssc
  }
}
