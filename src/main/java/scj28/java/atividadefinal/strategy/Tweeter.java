package scj28.java.atividadefinal.strategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.util.converter.LocalDateStringConverter;
import scj28.java.atividadefinal.vo.TweetVO;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Ação que realiza um tweet refenrencianto o professor michel - @michelpf.
 *
 */
public class Tweeter implements Actionable {

	@Override
	public void action(TweetVO tweetVO) throws TwitterException {
		String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		String msg = "Trabalho final de Fundamentos Java  - 28SCJ - @michelpf";
		tweetar(data +" - "+ msg);
	}

	private void tweetar(String msg) throws TwitterException {
		// Recupera a instancia da classe twitter já autenticada.
		Twitter twitter = TwitterFactory.getSingleton();
		Status status = twitter.updateStatus(msg);
		System.out.println("Tweet postado com sucesso! [" + status.getText() + "].");
	}

}
