package com.alicode.game.dogedash;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.channel.ChannelFailureException;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

/**
 * This servlet is responsible for sending messages across the channel.
 * 
 * @author
 */
@SuppressWarnings("serial")
public class MessageServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(MessageServlet.class
			.getCanonicalName());

	private static ChannelService channelService = ChannelServiceFactory
			.getChannelService();

	/**
	 * Check the incoming parameters and create the channel message . Send
	 * "OFFLINE" reply in case of an exception such as the user channel do not
	 * exist
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		String user = request.getParameter("to");
		String from = request.getParameter("from");
		if (user != null && !user.equals("") && message != null
				&& !message.equals("")) {
			try {
				String outputMessage = "<data>" + "<type>updateChatBox</type>"
						+ "<message>" + message + "</message>" + "<from>"
						+ from + "</from>" + "</data>";

				logger.log(Level.INFO, "sending message  into the channel");
				sendMessageToChannel(user, outputMessage);
			} catch (ChannelFailureException channelFailure) {
				logger.log(Level.WARNING,
						"Failed in sending message to channel");
				response.getWriter().print("OFFLINE");
			} catch (Exception e) {
				logger.log(Level.WARNING,
						"Unknow error while sending message to the channel");
				response.getWriter().print("OFFLINE");
			}
		}
	}

	/**
	 * Creates the Channel Message and sends to the client
	 * 
	 * @param user
	 *            the user to whom the message is sent
	 * @param message
	 *            the message that needs to pass
	 */
	public void sendMessageToChannel(String user, String message)
			throws ChannelFailureException {
		channelService.sendMessage(new ChannelMessage(user, message));
	}
}