public interface PublicTransport {

    default void openTheDoors() {
        System.out.println("Открыть двери для посадки");
    }

    default void closeTheDoors() {
        System.out.println("Закрыть двери");
    }
}
