package cash.andrew.bravotrain

import groovy.transform.CompileStatic
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

@CompileStatic
class MainWindow extends Application {

  static void main(String[] args) {
    launch(args)
  }

  @Override void start(Stage primaryStage) {
    def appConfig = new AppConfig()

    def config = new ConfigurationBuilder().with {
      debugEnabled = appConfig.debug
      setOAuthConsumerKey appConfig.getOAuthConsumerKey()
      setOAuthConsumerSecret appConfig.getOAuthConsumerSecret()
      setOAuthAccessToken appConfig.getOAuthAccessToken()
      setOAuthAccessTokenSecret appConfig.getOAuthAccessTokenSecret()
      build()
    }

    def twitter = new TwitterFactory(config).instance
    def tweeter = new Tweeter(twitter)
    def tweetTextProvider = new TweetTextProvider('/honk.json')


    primaryStage.setTitle('Bravo Train')

    def tweetButton = new Button('HONK HONK!')
    tweetButton.setOnAction {
      tweeter.tweet(tweetTextProvider.provideTweetText())
    }

    def root = new StackPane()
    root.getChildren().add(tweetButton);
    primaryStage.setScene(new Scene(root, 300, 250))
    primaryStage.show()
  }
}
