package clases;

public class Celda {
	private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int minaCerca;

    public Celda() {
        mina = false;
        descubierta = false;
        marcada = false;
        minaCerca = 0;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void setMarcada(boolean marcada) {
        this.marcada = marcada;
    }

    public int getMinaCerca() {
        return minaCerca;
    }

    public void setMinaCerca(int minaCerca) {
        this.minaCerca = minaCerca;
    }
}
