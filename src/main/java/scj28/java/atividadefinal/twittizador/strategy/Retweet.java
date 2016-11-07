package scj28.java.atividadefinal.twittizador.strategy;

import java.time.LocalDate;

import scj28.java.atividadefinal.twittizador.factory.TwitterFactory;
import scj28.java.atividadefinal.twittizador.vo.TweetVO;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Retweet implements Actionable {

	@Override
	public void action(TweetVO tweetVO) throws TwitterException {
		LocalDate dataAnterior = tweetVO.getDataInicio();

		System.out.println("Quantidade por dia de retweets da última semana:");
		while (dataAnterior.isBefore(tweetVO.getDataAgora())) {
			System.out.printf("%s %d retweets.\n", dataAnterior, buscaTweets(tweetVO.getHashTag(), dataAnterior));
			dataAnterior = dataAnterior.plusDays(1);
		}
	}

	private int buscaTweets(String hashTag, LocalDate dataInicio) throws TwitterException {
		// Recupera a instancia da classe twitter já autenticada.
		Twitter twitter = TwitterFactory.getInstance();
		LocalDate dataAnterior = dataInicio.minusDays(1);

		Query query = new Query(hashTag);
		query.setSince(dataAnterior.toString());
		query.setUntil(dataInicio.toString());

		QueryResult result = twitter.search(query);

		int contador = 0;
		while (result.hasNext()) {
			query = result.nextQuery();
			for (Status status : result.getTweets()) {
				contador += status.getRetweetCount();
			}
			result = twitter.search(query);
		}

		return contador;
	}
}