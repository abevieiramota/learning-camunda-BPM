package foo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class CreateTwitterDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String content = (String) execution.getVariable("content");

		String nome = (String) execution.getVariable("nome");
		
		content = content + " por " + nome;

		AccessToken accessToken = new AccessToken(
				"220324559-jet1dkzhSOeDWdaclI48z5txJRFLCnLOK45qStvo",
				"B28Ze8VDucBdiE38aVQqTxOyPc7eHunxBVv7XgGim4say");
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("lRhS80iIXXQtm6LM03awjvrvk",
				"gabtxwW8lnSL9yQUNdzAfgBOgIMSRqh7MegQs79GlKVWF36qLS");
		twitter.setOAuthAccessToken(accessToken);

		twitter.updateStatus(content);

	}

}
