package uz.sportloto.paynet.model;

/**
 * this class stores paynet credentials (login, password, terminal id) to acces paynet
 */
public class PaynetCredentials {
    private String paynetLogin;
    private String paynetPwd;
    private int terminalId;

    public PaynetCredentials() {
    }

    public String getPaynetLogin() {
        return paynetLogin;
    }

    public void setPaynetLogin(String paynetLogin) {
        this.paynetLogin = paynetLogin;
    }

    public String getPaynetPwd() {
        return paynetPwd;
    }

    public void setPaynetPwd(String paynetPwd) {
        this.paynetPwd = paynetPwd;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }
}
