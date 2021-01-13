package WiresDemo.WiresDemo.model.response;

public enum Errors {
    T26("Invalid Message"),
    T36("Bank Opertaion is not Valid"),
    T50("Date is not valid");

    private final String msg;

    Errors(String msg) {
        this.msg = msg;
    }

    public String getMsg() { return msg; }
}
