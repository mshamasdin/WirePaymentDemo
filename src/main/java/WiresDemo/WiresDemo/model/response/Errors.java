package WiresDemo.WiresDemo.model.response;

public enum Errors {

    T26("Invalid Message"),
    T36("Bank Opertaion is not Valid"),
    T50("Date is not valid"),
    D75("Invalid Value"),
    E346("Invalid Amount"),
    E347("Sender Account Number is not Valid"),
    E347B("Sender Name is not Valid"),
    E347C("Sender Address is not Valid"),
    E347D("Insufficient funds"),
    E349("General Exception");

    private final String msg;

    Errors(String msg) {
        this.msg = msg;
    }

    public String getMsg() { return msg; }
}
