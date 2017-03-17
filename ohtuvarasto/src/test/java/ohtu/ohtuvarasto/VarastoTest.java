package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto varasto3;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
    
    

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLisataLiikaa() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(3);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiPoistetaLiikaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(9);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaTilavuutta() {
        varasto2 = new Varasto(-1);
        
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
   
    
    @Test
    public void eiNegatiivistaTilavuutta2() {
        varasto3 = new Varasto(-1,0);
        
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaAlkusaldoa() {
        varasto3 = new Varasto(10,-1);
        
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void oikeaAlkusaldoa() {
        varasto3 = new Varasto(10,2);
        
        assertEquals(2, varasto3.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaLisaysta() {
        varasto.lisaaVarastoon(-1);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaOttoa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-2);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tulostusOikea() {
        varasto.lisaaVarastoon(4);
        String tulostus = "saldo = 4.0, vielä tilaa 6.0";
        assertEquals(tulostus, varasto.toString());
    }

}