package net.GUIpsp.GuiBot;

import java.lang.reflect.*;
import java.util.regex.*;

import org.jibble.pircbot.*;

public class GuiBot extends PircBot {
	public static String pref = "$";

	public GuiBot() {
		this.setName("GuiBot");
	}

	public void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		if (message.trim().startsWith(pref)) {
			message = message.replaceFirst(Matcher.quoteReplacement(pref), "");
			String[] parts = message.split(" ");
			String first = parts[0];
			if ((first.equalsIgnoreCase(("exit"))
					|| message.equalsIgnoreCase("quit"))&& sender.equalsIgnoreCase("guipsp")) {
				System.exit(0);
			} else if (first.equalsIgnoreCase("reload")) {
				try {
					System.out.println("Reloading...");
					Main.reLoad();
					System.out.println("Reloaded");
					Main.bot.sendMessage(channel, "Plugins reloaded.");
				} catch (Throwable e) {
					e.printStackTrace();
				}
			} else if (Main.cmdmap.containsKey(first)) {
				try {
					Method met = (Method) Main.cmdmap.get(first);
					String result = "";
					for (int i = 1; i < parts.length; i++) {
						if (result.length() != 0)
							result += " ";
						result += parts[i];
					}
					met.invoke((BasePlugin) Main.classmap.get(first), channel,
							sender, login, hostname, result);

				} catch (Throwable e) {
					e.printStackTrace();
				}

			}
		}
	}
}
