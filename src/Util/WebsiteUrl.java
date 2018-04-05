package Util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebsiteUrl {
	public static void shortcutOpen(String url) {
		Desktop desk = Desktop.getDesktop();
		if (desk.isSupported(Desktop.Action.BROWSE)) {
			try {
				desk.browse(new URI(url));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
	}
}
