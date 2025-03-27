

public class Main {
    public static void main(String[] args) {
        Above boat = new Above("Баркас", "Дизель", 545, 14, 4500, 30);
        System.out.println(boat);
        Under shark = new Under("Акула", "Атомный реактор", 48000000, 22, 23200000, 46, 160, 48000000, 500, 380, 120);
        System.out.println(shark);
        Plane an2 = new Plane("Ан-2", "авиационный керосин", 3600, 190, 5500, 990, 4500, 2, 10, 5520, 1240);
        System.out.println(an2);
        Helicopter mi8 = new Helicopter("МИ-8", "авиационный керосин", 6500, 270, 12500, 715, 6000, 3, 30);
        System.out.println(mi8);
        Vehicle car = new Automobile("ВАЗ-2105", "машина", "бензин", 1000, 150, false,false, true, 400, 39, 500, 5);
        System.out.println(car);
        Railway train = new Railway("Ласточка", "Электричество", 267000, 160, 5, 822);
        System.out.println(train);

        boat.moveBackward();
        shark.moveDown();
        an2.moveDown();
        mi8.moveUp();
        train.openTheDoors();
    }
}