import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.sns.sqbot.groovy.MyCredentialProvider;

WebClient wc = new WebClient(BrowserVersion.INTERNET_EXPLORER_6_0,"msk-isa4.luxoft.com",8080);
wc.setCredentialsProvider( new MyCredentialProvider());
def page = wc.getPage(new URL("http://www.google.com/"), "google");
page.getForm

