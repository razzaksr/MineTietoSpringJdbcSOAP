package elements.spring.explore.jdbc;

public class Bankers {
    private int bankerId;
    private String bankerName;

    public int getBankerId() {
        return bankerId;
    }

    public void setBankerId(int bankerId) {
        this.bankerId = bankerId;
    }

    public String getBankerName() {
        return bankerName;
    }

    public void setBankerName(String bankerName) {
        this.bankerName = bankerName;
    }

    public String getBankerPasscode() {
        return bankerPasscode;
    }

    public void setBankerPasscode(String bankerPasscode) {
        this.bankerPasscode = bankerPasscode;
    }

    private String bankerPasscode;
}