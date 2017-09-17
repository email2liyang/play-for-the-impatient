package model

import play.api.Logger

trait Summarizer {

  def summarise(item: String): String
}

class DefaultSummarizer extends Summarizer {

  val logger: Logger = Logger(this.getClass)

  override def summarise(item: String): String = {
    logger.info(s"item is $item")
    item
  }
}
