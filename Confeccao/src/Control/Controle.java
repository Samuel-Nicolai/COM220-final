package Control;

import View.Limite;

public class Controle {

    private ControleCliente objCtrCliente = new ControleCliente();
    private ControleMercadoria objCtrMercadoria = new ControleMercadoria();
    private ControleNotaFiscal objCtrNotaFiscal = new ControleNotaFiscal();
    private Limite objLimite;

    public Controle() {
        this.objLimite = new Limite(this);
        try {
            this.objCtrCliente.desserializaCliente();
            this.objCtrMercadoria.desserializaMercadoria();
            this.objCtrNotaFiscal.desserializaNotaFiscal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ControleCliente getObjCtrCliente() {
        return objCtrCliente;
    }

    public ControleMercadoria getObjCtrMercadoria() {
        return objCtrMercadoria;
    }

    public void finaliza() {
        try{
            objCtrCliente.finaliza();
            objCtrMercadoria.finaliza();
            objCtrNotaFiscal.finaliza();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
