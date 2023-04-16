package projlab;

public class UseCaseNull {
    /**
     * Ez a teszt a Szabotőr{@link Szabotor} kilyukasztja a csövet UseCase alapján készült
     * Ez a Javítás alatta található meg
     */
    public static void use_case_test() {
        Szabotor sz = new Szabotor();
        Cso cs = new Cso();

        sz.setHelyzet(cs);
        sz.Lyukaszt();

    }
}
