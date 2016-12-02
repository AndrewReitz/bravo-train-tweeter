package cash.andrew.bravotrain

import groovy.json.JsonSlurper
import org.apache.commons.lang3.RandomUtils

import static com.google.common.base.Preconditions.checkNotNull

class TweetTextProvider {

  private static final JsonSlurper JSON_SLURPER = new JsonSlurper()

  private final List<String> quotes

  /**
   * Name of json file containing tweets. This file
   * must be a list of strings.
   */
  TweetTextProvider(String fileName) {
    checkNotNull(fileName, 'fileName == null')

    def stream = getClass().getResourceAsStream(fileName)

    quotes = JSON_SLURPER.parse(stream) as List<String>
  }

  String provideTweetText() {
    def value = RandomUtils.nextInt(0, quotes.size())
    return quotes[value]
  }
}
