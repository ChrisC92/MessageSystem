package input.messages;

public class Single implements Messages {

    private Sale sale;

    public Single(Sale sale) {
        this.sale = sale;
    }

    @Override
    public String printMessage() {
        return sale.getType() + "at " + sale.getPrice() + "p";
    }
}
