package projlab;

public class Pontszamlalo {
    private int szabotorPoint, szereloPoint = 0;
    private int currRound = 1;
    private int maxRound = 20;

    protected PontszamlaloView view = new PontszamlaloView();

    public Pontszamlalo(){ }

    public void szabotorPontotNovel(int p){
        szabotorPoint += p;
        view.Notify("sza", p);
    }

    public void szereloPontotNovel(int p){
        szereloPoint += p;
        view.Notify("sze", p);
    }

    public void korNovel(){
        currRound += 1;
        view.Notify("k", 1);
    }
}
